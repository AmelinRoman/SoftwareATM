package software.command;

import software.consoleHelper.ConsoleHelper;
import software.exception.InterruptOperationException;
import software.manipulator.CurrencyManipulatorFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Amelin Roman
 * Реализация паттерна Command
 * Класс имплементирует интерфейс Command, а так же реализует логику работы операции Info
 */
public class InfoCommand implements Command {
    /**
     * Метод выводит информацию по всем счетам, если не на одном из счетов нет денег то программа выводит сообщение об этом
     */
    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Информация вашем счетам: ");
        AtomicBoolean hasMoney = new AtomicBoolean(false);
        CurrencyManipulatorFactory
                .getAllCurrencyManipulators()
                .forEach(currencyManipulator -> {
                    if (currencyManipulator.hasMoney()) {
                        System.out.println(String.format("%s - %d", currencyManipulator.getCurrencyCode(),
                                                                    currencyManipulator.getTotalAmount()));
                        hasMoney.set(true);
                    }
                });
        if (!hasMoney.get()) {
            System.out.println("На ваших счетах на данный момент денег не обнаруженно");
        }
    }
}
