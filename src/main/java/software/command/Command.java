package software.command;

import software.exception.InterruptOperationException;

/**
 * @author Amelin Roman
 * interface для реализации паттерна command, для каждой из возможных операций из enum Operation
 */
interface Command {
    /**
     * Метод для реализии в каждой команде
     * @throws InterruptOperationException выбрасывается если пользователь в любой момент
     * работы программы введет слово "exit"
     */
    void execute() throws InterruptOperationException;
}
