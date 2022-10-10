import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение");
        String input = scanner.nextLine();
        String answer =  calc(input);
        System.out.println(answer);
    }
    public static String calc(String input) {
        String str1 = input.replaceAll(" ", "");
        int index = -1 ;
        char action1 = getTypeOfAction(str1);
        if (action1 == '_') {
            try {
                throw new IOException();
            } catch (IOException e){
                System.out.println("Ошибка: неизвестная операция");
                System.exit(0);
            }

        } else {
            index = str1.indexOf(action1);
        }

        String[] romanNums = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arabicNums = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int variable1 = 1;
        int variable2 = 1;
        boolean rom = false;//индекс использования римских цифр
        String variable1str = str1.substring(0, index);
        for (int i = 0; i < romanNums.length; i++) {
            String variableForCompare = romanNums[i];
            if (variable1str.equalsIgnoreCase(variableForCompare)) {
                variable1 = i + 1;
                rom = true;
            }
        }
        for (int i = 0; i < arabicNums.length; i++) {
            String variableForCompare = arabicNums[i];
            if (variable1str.equalsIgnoreCase(variableForCompare)) {
                variable1 = i + 1;
                rom = false;
            }
        }
        if (variable1 == -1) {
            try {
                throw new IOException();
            } catch (IOException e){
                System.out.println("Ошибка: Первый операнд больше 10");
                System.exit(0);
            }
        }
        String variable2str = str1.substring(index + 1);
        for (int i = 0; i < romanNums.length; i++) {
            String variableForCompare = romanNums[i];
            if (variable2str.equalsIgnoreCase(variableForCompare)) {
                if (!rom ) {
                    try {
                        throw new IOException();
                    } catch (IOException e){
                        System.out.println("Ошибка: Значения вводимых чисел должны быть однотипными");
                        System.exit(0);
                    }
                }
                variable2 = i + 1;
            }
        }
        for (int i = 0; i < arabicNums.length; i++) {
            String variableForCompare = arabicNums[i];
            if (variable2str.equalsIgnoreCase(variableForCompare)) {
                if (rom) {
                    try {
                        throw new IOException();
                    } catch (IOException e){
                        System.out.println("Ошибка: Значения вводимых чисел должны быть однотипными");
                        System.exit(0);
                    }
                }
                variable2 = i + 1;
            }
        }
        if (variable2 == -1) {
            try {
                throw new IOException();
            } catch (IOException e){
                System.out.println("Ошибка: Второй операнд больше 10");
                System.exit(0);
            }
        }

        int arabResult = getArabResult(variable1, variable2, action1);

        String result = "";
        if (rom && arabResult > 0) {
            String romResult = IntegerToRomanNumeral(arabResult);
            result = "Ответ: " + romResult;
        } else if (!rom) {
            result = "Ответ: " + arabResult;
        } else if (arabResult < 0) {
            try {
                throw new IOException();
            } catch (IOException e){
                System.out.println("Ошибка: В римской системе не существует отрицательных числе");
                System.exit(0);
            }
        }
        return result;

    }
    public static char getTypeOfAction(String str1) {
        int index = str1.indexOf("+");
        char act1 = '_';
        if (index > -1) {
            act1 = '+';
        }
        if (index == -1) {
            index = str1.indexOf("-");
            if (index > -1) {
                act1 = '-';
            }
        }
        if (index == -1) {
            index = str1.indexOf("/");
            if (index > -1) {
                act1 = '/';
            }
        }
        if (index == -1) {
            index = str1.indexOf("*");
            if (index > -1) {
                act1 = '*';
            }
        }
        return act1;
    }
    public static int getArabResult(int variable1, int variable2, char action1) {
        int rsl = 0;
        if (action1 == '+') {
            rsl = variable1 + variable2;
        }
        if (action1 == '-') {
            rsl = variable1 - variable2;
        }
        if (action1 == '*') {
            rsl = variable1 * variable2;
        }
        if (action1 == '/') {
            if (variable2 == 0) {
                System.out.println("Оops! Divide by zero");
                System.exit(0);
            }
            rsl = (variable1 - (variable1 % variable2)) / variable2;
        }
        return rsl;
    }
    public static String IntegerToRomanNumeral(int input) {
        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        StringBuilder s = new StringBuilder(" ");
        while (input >= 1000) {
            s.append("M");
            input -= 1000;
        }
        while (input >= 900) {
            s.append("CM");
            input -= 900;
        }
        while (input >= 500) {
            s.append("D");
            input -= 500;
        }
        while (input >= 400) {
            s.append("CD");
            input -= 400;
        }
        while (input >= 100) {
            s.append("C");
            input -= 100;
        }
        while (input >= 90) {
            s.append("XC");
            input -= 90;
        }
        while (input >= 50) {
            s.append("L");
            input -= 50;
        }
        while (input >= 40) {
            s.append("XL");
            input -= 40;
        }
        while (input >= 10) {
            s.append("X");
            input -= 10;
        }
        while (input == 9) {
            s.append("IX");
            input -= 9;
        }
        while (input >= 5) {
            s.append("V");
            input -= 5;
        }
        while (input == 4) {
            s.append("IV");
            input -= 4;
        }
        while (input >= 1) {
            s.append("I");
            input -= 1;
        }
        return s.toString();
    }
}