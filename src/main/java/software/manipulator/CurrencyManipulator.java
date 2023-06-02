package software.manipulator;

import software.exception.NotEnoughMoneyException;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author Amelin Roman
 * Класс для хранения информации про выбранную валюту
 */
public class CurrencyManipulator {
    /**
     * Поле типа String хранит код валюты для текущего манипулятора
     */
    private String currencyCode;
    /**
     * Map для хранения суммы в банкомате <Номинал, Количество>
     */
    private Map<Integer, Integer> denominations = new HashMap<>();

    /**
     * Getter для поля currencyCode
     * @return возвращает строку (пример: USD)
     */
    public String getCurrencyCode() { return currencyCode; }

    /**
     * Конструктор класса для инициализации выбранной валюты
     * @param currencyCode код валюты типа String
     */
    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * Метод добавляет номинал и сумму в Map<Integer, Integer> denominations
     * @param denomination номинал купюры типа int
     * @param count кол-во купюр типа int
     */
    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            int newCount = count + denominations.get(denomination);
            denominations.put(denomination, newCount);
        } else {
            denominations.put(denomination, count);
        }
    }

    /**
     * Метод проверяет есть ли деньги в банкомате(Map<Integer, Integer> denominations)
     * @return true если в Map size > 0;
     */
    public boolean hasMoney() { return !denominations.isEmpty(); }

    /**
     * Метод проверяет, есть ли в нашей коллекции общая сумма, большая или равная входному параметру
     * @param expectedAmount сумма к выводу
     * @return true если сумма есть
     */
    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    /**
     * Метод возвращает общую сумму купюр находящихся в банкомате
     * где sum += denomination * count
     * @return общую сумму которая находится в Map<Integer, Integer> denominations
     */
    public int getTotalAmount() {
        return denominations.entrySet().stream()
                .mapToInt(entry -> entry.getKey() * entry.getValue())
                .sum();
    }

    /**
     * В методе реализован жадный алгоритм в два прохода, скорость O(n)
     * @param expectedAmount сумма для вывода средств
     * @return Map<Integer, Integer> возвращает коллекцию снятых купюр
     * @throws NotEnoughMoneyException исключение выбрасывается если не хватает купюр для снятия средств
     */
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        int remainingAmount = expectedAmount;
        Map<Integer, Integer> result = new TreeMap<>(Comparator.reverseOrder());
        Map<Integer, Integer> reversedDenominations = new TreeMap<>(Comparator.reverseOrder());
        reversedDenominations.putAll(denominations);

        System.out.println(denominations.toString());

        for (Map.Entry<Integer, Integer> entry : reversedDenominations.entrySet()) {
            int denomination = entry.getKey();
            int count = entry.getValue();

            int countBanknotesToWithdrawal = Math.min(remainingAmount / denomination, count);
            remainingAmount -= (countBanknotesToWithdrawal * denomination);
            if (countBanknotesToWithdrawal > 0) {
                result.put(denomination, countBanknotesToWithdrawal);
            }

            if (remainingAmount == 0) break;
        }

        if (remainingAmount != 0) throw new NotEnoughMoneyException();

        denominations.entrySet().stream()
                .filter(entry -> result.containsKey(entry.getKey()))
                .forEach(entry -> entry.setValue(entry.getValue() - result.get(entry.getKey())));

        return result;
    }
}
