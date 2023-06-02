package software.manipulator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Amelin Roman
 * Фабрика для создания и хранения манипуляторов валют
 */
public class CurrencyManipulatorFactory {
    /**
     * Map хранит манипуляторы для соотвествующих кодов Map<currencyCode, CurrencyManipulator>
     */
    private static Map<String, CurrencyManipulator> currencyManipulators = new HashMap<>();

    /**
     * Метод возвращает манипулятор соотвествующий входному параметру(коду)
     * или создает новый если его не существует
     * @param currencyCode тип String, содержит код валюты
     * @return возвращает манипулятор соответвующий коду валюты
     */
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (!currencyManipulators.containsKey(currencyCode)) {
            currencyManipulators.put(currencyCode, new CurrencyManipulator(currencyCode));
        }
        return currencyManipulators.get(currencyCode);
    }

    /**
     * Метод возвращает коллекцию всех манипуляторов валют,
     * пpиcyтcтвyющиe в Map<String, CurrencyManipulator> currencyManipulators
     * @return Collection<CurrencyManipulator>, коллекция всех созданных манипуляторов
     */
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return currencyManipulators.values();
    }

    /**
     * Приватный конструктор что бы нельзя было создать экземпляр класса
     */
    private CurrencyManipulatorFactory() {}
}
