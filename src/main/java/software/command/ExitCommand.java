package software.command;

import software.consoleHelper.ConsoleHelper;
import software.exception.InterruptOperationException;

/**
 * @author Amelin Roman
 * Реализация паттерна Command
 * Класс имплементирует интерфейс Command, а так же реализует логику работы операции Exit
 */
class ExitCommand implements Command{
    /**
     * Метод спрашивает у пользователя точно ли он хочет выйти, после ответа, если пользователь подтвердил
     * свои намерения, то программа прощается с пользователем и программа завершает свою работу
     * @throws InterruptOperationException выбрасывает исключения при написании пользователем "exit"
     */
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Вы действительно хотите выйти? \n" +
                "Введите: да или нет");
        String answer = ConsoleHelper.readString();
        if (answer.contains("да")) {
            ConsoleHelper.writeMessage("До свидания, всего доброго!");
        }
    }
}
