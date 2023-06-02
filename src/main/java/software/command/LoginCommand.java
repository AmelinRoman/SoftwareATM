package software.command;

import software.consoleHelper.ConsoleHelper;
import software.exception.InterruptOperationException;
/**
 * @author Amelin Roman
 * Реализация паттерна Command
 * Класс имплементирует интерфейс Command, а так же реализует логику работы операции Login
 */
class LoginCommand implements Command {
    /**
     * 2 приватные захардкоженные данные карты и пин кода, временная заглушка
     */
    private static final long NUMBER_CARD = 123_456_789_012L;
    private static final int PIN_NUMBER_CARD = 1234;
    /**
     * Метод приветсвтует пользователя, затем в бесконечном цикле запрашивает у пользователя данные карты и пин кода.
     * Затем проверяет валидность данных, и соотвествуют ли они константам, если да
     * то сообщает пользователю что он идентифицирован и прерывает бесконечный цикл
     * Если данные некорректны то запрашивает данные снова
     * @throws InterruptOperationException выбрасывается если пользователь введет "exit"
     */
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Здравствуйте!");
        while (true) {
            ConsoleHelper.writeMessage("Пожалуйста введите номер карты: ");
            String numberCard = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Пожалуйста введите пин-код: ");
            String pinNumber = ConsoleHelper.readString();
            if (!numberCard.matches("\\d{12}") && !pinNumber.matches("\\d{4}")) {
                ConsoleHelper.writeMessage("Данные некорректны");
            } else if (Long.parseLong(numberCard) != NUMBER_CARD && Integer.parseInt(pinNumber) != PIN_NUMBER_CARD) {
                ConsoleHelper.writeMessage("Данные некорректны");
            } else {
                ConsoleHelper.writeMessage("Вы идентифицированы");
                break;
            }
        }
    }
}
