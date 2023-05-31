package software.manipulator;

import java.util.HashMap;
import java.util.Map;

/**
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
        denominations.put(denomination, count);
    }
}
