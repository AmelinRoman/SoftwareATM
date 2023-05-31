package software.consoleHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс предназначен для взаимодействия с консолью и пользователем.
 */
public class ConsoleHelper {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Метод выводит сообщение в консоль
     * @param message type String сообщение для вывода
     */
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    /**
     * Метод для чтения строки из консоли
     * @return String
     * @throws IOException исключение обрабатывает класс, вызвавший этот метод
     */
    public static String readString() throws IOException {
        return reader.readLine();
    }

    /**
     * Метод запрашивает код из консоли, если длина строки: != 3 то в бесконечном цикле спрашиваем код
     * В будущем метод будет доработан и будут предложены валюты на выбор, сейчас в тестовом режиме
     * @return Возрает код полученный от пользователя
     * @throws IOException исключение обрабатывает вызвавший метод класс
     */
    public static String askCurrencyCode() throws IOException {
        while (true) {
            writeMessage("Пожалуйста введите код валюты");
            String currencyCode = readString();
            if (currencyCode.length() == 3) {
                return currencyCode.toUpperCase();
            } else {
                writeMessage("Вы ввели некоректные данные");
            }
        }
    }

    /**
     * Метод для ввода депозита, запрашивает и проверят кооректность данных(суммы)
     * Сумма вводиться через проблем, купюры не ограниченны номиналом,
     * в случае некорректных данных, метод просит ввести суммы заново,
     * пока не будут введены корректные данные
     * @param currencyCode код валюты, деньги которой будут вноситься в банкомат
     * @return возвращает массив из двух элемнтов где [номинал(index = 0), количество[index = 1]]
     * @throws IOException исключение обрабатывает вызвавший метод класс
     */
    public static String[] getValidTwoDigits(String currencyCode) throws IOException {
        while (true) {
            writeMessage(String.format("Введите номинал и количество купюр через пробел, в валюте %s", currencyCode));
            String twoDigits = readString();
            if (twoDigits.matches("(\\d+)\\s(\\d+)")) {
                return twoDigits.split(" ");
            } else {
                writeMessage("Данные некорректны");
            }
        }
    }
}
