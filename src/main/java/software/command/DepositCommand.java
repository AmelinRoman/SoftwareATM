package software.command;

import software.consoleHelper.ConsoleHelper;
import software.exception.InterruptOperationException;
import software.manipulator.CurrencyManipulatorFactory;

/**
 * @author Amelin Roman
 * Реализация паттерна Command
 * Класс имплементирует интерфейс Command, а так же реализует логику работы операции Deposit
 */
class DepositCommand implements Command {
    /**
     * Метод запрашивает код валюты, затем запрашивает ввести сумму для внесения, затем создает или
     * получает уже созданый манипулятор для текущей валюты, и добавляет сумму в манипулятор
     * @throws InterruptOperationException выбрасывается если пользователь введет "exit"
     */
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Внесение депозита");
        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] twoDigits = ConsoleHelper.getValidTwoDigits(currencyCode);
        CurrencyManipulatorFactory
                .getManipulatorByCurrencyCode(currencyCode)
                .addAmount(Integer.parseInt(twoDigits[0]), Integer.parseInt(twoDigits[1]));
    }
}
