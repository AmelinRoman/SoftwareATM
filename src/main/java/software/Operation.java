package software;


/**
 * @author Amelin Roman
 * Enum возможных операций с банкоматом.
 */
public enum Operation {
    LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT;

    /**
     * Статический метод, который проверяет какую операцию выбрал пользователь
     * @param i типа int, обозначающий номер операции
     * @return возвращает одно значение из enum Operation
     * @throws IllegalArgumentException выбрасывается исключение если цифра не соответсвует номерам enum, а так же если
     * пользователь пытается сам выбрать операцию LOGIN
     */
    public static Operation getAllowableOperationByOrdinal(Integer i) throws IllegalArgumentException {
        if (i == LOGIN.ordinal()) {
            throw new IllegalArgumentException();
        }
        for (Operation operation : Operation.values()) {
            if (operation.ordinal() == i) {
                return operation;
            }
        }
        throw new IllegalArgumentException();
    }
}
