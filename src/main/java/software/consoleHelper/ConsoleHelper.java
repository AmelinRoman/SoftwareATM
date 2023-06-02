package software.consoleHelper;

import software.Operation;
import software.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Amelin Roman
 * Класс предназначен для взаимодействия с консолью и пользователем.
 */
public class ConsoleHelper {
    /**
     * Статическая константа для считывания входного потока
     */
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

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
     * @throws InterruptOperationException исключение выбрасывается если пользователь введет слово "exit"
     */
    public static String readString() throws  InterruptOperationException {
        String line;
        try {
            line = READER.readLine();
            if ("exit".contains(line.toLowerCase())) {
                throw new InterruptOperationException();
            } else {
                return line;
            }
        } catch (IOException ignored) { }
        return null;
    }

    /**
     * Метод запрашивает код из консоли, если длина строки: != 3 то в бесконечном цикле спрашиваем код
     * В будущем метод будет доработан и будут предложены валюты на выбор, сейчас в тестовом режиме
     * @return Возрает код полученный от пользователя
     * @throws IOException исключение обрабатывает вызвавший метод класс
     */
    public static String askCurrencyCode() throws InterruptOperationException {
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
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
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

    /**
     * Метод запрашивает у пользователя выбор операции
     * @return возвращает одно из значений нашего enum Operation
     * @throws InterruptOperationException выбрасывает исключение если пользователь введет слово "exit"
     */
    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage("Выберете одну из операций: \n" +
                    "1 - Информация о счете\n" +
                    "2 - Внести депозит\n" +
                    "3 - Снятие средств\n" +
                    "4 - Выход");
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            } catch (IllegalArgumentException ignored) {
                writeMessage("Данные некорректны");
            }
        }
    }
}
