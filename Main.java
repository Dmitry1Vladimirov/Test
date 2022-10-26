import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));

    }

    public static String calc(String input) {
        String result = "";
        Integer[] numbersRoman = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] numbersArabic = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] inputSplit = input.split(" ");
        String firstNumber = null;
        String secondNumber = null;
        String operation = null;
        try {
            firstNumber = new String(inputSplit[0]);
            secondNumber = new String(inputSplit[2]);
            operation = new String(inputSplit[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("throws Exception //т.к. строка не является математической операцией");
        }

        if (inputSplit.length > 3) {
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            return "";
        }


        if (inArray(firstNumber, numbersArabic) && inArray(secondNumber, numbersRoman)) {
            System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
            return "";
        }

        if (inArray(firstNumber, numbersRoman) && inArray(secondNumber, numbersArabic)) {
            System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
            return "";
        }

        if (inArray(firstNumber, numbersArabic) && inArray(secondNumber, numbersArabic)) {
            int firstNumberAfterConversion = conversation(firstNumber, numbersRoman, numbersArabic);
            int secondNumberAfterConversion = conversation(secondNumber, numbersRoman, numbersArabic);

            if (Objects.equals(operation, "-") || Objects.equals(operation, "/")) {
                if (firstNumberAfterConversion > secondNumberAfterConversion) {
                    result = operation(operation, firstNumberAfterConversion, secondNumberAfterConversion);
                    result = reverseConversation(Integer.parseInt(result), numbersRoman, numbersArabic);
                } else {
                    System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                }
            } else {
                assert operation != null;
                result = operation(operation, firstNumberAfterConversion, secondNumberAfterConversion);
                result = reverseConversation(Integer.parseInt(result), numbersRoman, numbersArabic);
            }
        } else if (inArray(firstNumber, numbersRoman) && inArray(secondNumber, numbersRoman)) {
            assert operation != null;
            result = operation(operation, firstNumber, secondNumber);
        } else {
            System.out.println("throws Exception //т.к. строка не является математической операцией");
            return "";
        }

        return result;
    }

    public static boolean inArray(String number, Integer[] array) {
        for (Integer integer : array) {
            if (String.valueOf(integer).equals(number)) {
                return true;
            }
        }
        return false;
    }

    public static boolean inArray(String number, String[] array) {
        for (String str : array) {
            if (Objects.equals(number, str)) {
                return true;
            }
        }
        return false;
    }

    public static String operation(String opr, String firstNumber, String secondNumber) {
        String result = "";
        switch (opr) {
            case ("*") -> result = String.valueOf(Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber));
            case ("+") -> result = String.valueOf(Integer.parseInt(firstNumber) + Integer.parseInt(secondNumber));
            case ("-") -> result = String.valueOf(Integer.parseInt(firstNumber) - Integer.parseInt(secondNumber));
            case ("/") -> result = String.valueOf(Integer.parseInt(firstNumber) / Integer.parseInt(secondNumber));
            default -> System.out.println("throws Exception //т.к. строка не является математической операцией");
        }

        return result;
    }

    public static String operation(String opr, Integer firstNumber, Integer secondNumber) {
        String result = "";
        switch (opr) {
            case ("*") -> result = String.valueOf(firstNumber * secondNumber);
            case ("+") -> result = String.valueOf(firstNumber + secondNumber);
            case ("-") -> result = String.valueOf(firstNumber - secondNumber);
            case ("/") -> result = String.valueOf(firstNumber / secondNumber);
            default -> System.out.println("throws Exception //т.к. строка не является математической операцией");
        }

        return result;
    }

    public static int conversation(String number, Integer[] array1, String[] array2) {
        int index = 0;
        for (int i = 0; i < array2.length; ++i) {
            if (number.equals(array2[i])) {
                index = i;
            }
        }
        return array1[index];
    }

    public static String reverseConversation(int number, Integer[] array1, String[] array2) {
        int index = 0;
        for (int i = 0; i < array1.length; ++i) {
            if (number == array1[i]) {
                index = i;
            }
        }
        return array2[index];
    }

}

