package software;

import software.command.CommandExecutor;
import software.consoleHelper.ConsoleHelper;
import software.exception.InterruptOperationException;

/**
 * @author Amelin Roman
 */
public class CashMachine {
    public static void main(String[] args) {
        try {
            CommandExecutor.execute(Operation.LOGIN);
            Operation operation;
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptOperationException ignored) {
            ConsoleHelper.writeMessage("До свидания, всего доброго");
        }
    }
}
