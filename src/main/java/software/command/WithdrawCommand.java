package software.command;

import software.consoleHelper.ConsoleHelper;
import software.exception.InterruptOperationException;
import software.exception.NotEnoughMoneyException;
import software.manipulator.CurrencyManipulator;
import software.manipulator.CurrencyManipulatorFactory;
/**
 * @author Amelin Roman
 * Реализация паттерна Command
 * Класс имплементирует интерфейс Command, а так же реализует логику работы операции Withdraw
 */
public class WithdrawCommand implements Command {
    /**
     * Метод запрашивает код валюты, по которой пользователь хочет совершить операцию.
     * Затем берется манипулятор для текущей валюты.
     * В бесконечном цикле, программа спрашивает пользователя сумму, если данные валидны и на счету есть деньги
     * то программа списывает деньги
     * Так же обробатывается исключение NotEnoughMoneyException, если в банкомате не достаточно купюр
     * Программа работает до тех пор пока не будет снята сумма или не будет выбрашенно исключение
     * @throws InterruptOperationException выбрасывает исключение если пользователь введет "exit"
     */
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Внесение депозита:");
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currentManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        while (true) {
            try {
                ConsoleHelper.writeMessage("Введите сумму:");
                String withdrawalAmount = ConsoleHelper.readString();
                if (withdrawalAmount.matches("\\d+")) {
                    int expectedAmount = Integer.parseInt(withdrawalAmount);
                    if (currentManipulator.isAmountAvailable(expectedAmount)) {
                        currentManipulator.withdrawAmount(expectedAmount)
                                .forEach((key, value) -> System.out.println(String.format("\t %d - %d", key, value)));
                        break;
                    }
                }
            } catch (NotEnoughMoneyException ignored) {
                ConsoleHelper.writeMessage("В банкомате недостаточно купюр для выдачи такой суммы. \n" +
                        "Попробуйте ввести другую сумму.");
            }
        }
    }
}
