package software.command;

import software.Operation;
import software.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Amelin Roman
 * Реализация паттерна Command
 * Класс управляет всеми возможными командами
 */
public class CommandExecutor {
    /**
     * Map всех возможных операций и комманд
     */
    public static Map<Operation, Command> commandMap = new HashMap<>();

    static {
        commandMap.put(Operation.LOGIN, new LoginCommand());
        commandMap.put(Operation.INFO, new InfoCommand());
        commandMap.put(Operation.DEPOSIT, new DepositCommand());
        commandMap.put(Operation.WITHDRAW, new WithdrawCommand());
        commandMap.put(Operation.EXIT, new ExitCommand());
    }

    /**
     * Метод вызывает опеределенную команду в зависимости от входного параметра
     * @param operation значение enum Operation
     * @throws InterruptOperationException исключение если пользователь введет "exit"
     */
    public static void execute(Operation operation) throws InterruptOperationException {
        commandMap.get(operation).execute();
    }


}
