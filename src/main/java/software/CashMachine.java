package software;

import software.consoleHelper.ConsoleHelper;

import java.io.IOException;
import java.util.Arrays;

public class CashMachine {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(ConsoleHelper.getValidTwoDigits(ConsoleHelper.askCurrencyCode())));
    }
}
