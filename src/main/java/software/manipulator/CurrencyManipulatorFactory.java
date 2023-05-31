package software.manipulator;

import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика для создания и хранения манипуляторов валют
 */
public class CurrencyManipulatorFactory {
    /**
     * Map хранит манипуляторы для соотвествующих кодов Map<currencyCode, CurrencyManipulator>
     */
    private static Map<String, CurrencyManipulator> currencyManipulators = new HashMap<>();

    /**
     * Метод возвращает манипулятор соотвествущий входному параметру(коду)
     * или создает новый если его не существует
     * @param currencyCode тип String, содержит код валюты
     * @return возвращает манипулятор соответвующий коду валюты
     */
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (currencyManipulators.containsKey(currencyCode)) {
            return currencyManipulators.get(currencyCode);
        } else {
            return currencyManipulators.put(currencyCode, new CurrencyManipulator(currencyCode));
        }
    }

    /**
     * Приватный конструктор что бы нельзя было создать экземпляр класса
     */
    private CurrencyManipulatorFactory() {}
}
