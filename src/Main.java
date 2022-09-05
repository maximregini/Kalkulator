import java.util.Scanner;
import java.util.TreeMap;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    static int Arithmetic (String operation, int a, int b) throws IOException {
        return switch (operation) {
            case ("+") -> a + b;
            case ("-") -> a - b;
            case ("*") -> a * b;
            case ("/") -> a / b;
            default -> throw new IOException("Неверная операция");
        };
    }
     static int RomanToArabic (String number) {
        String ArabNumber = number.toUpperCase();
        return switch (ArabNumber) {
            case ("I") -> 1;
            case ("II") -> 2;
            case ("III") -> 3;
            case ("IV") -> 4;
            case ("V") -> 5;
            case ("VI") -> 6;
            case ("VII") -> 7;
            case ("VIII") -> 8;
            case ("IX") -> 9;
            case ("X") -> 10;
            default -> -1;
        };
    }
    static String ArabicToRoman (int i) {
        StringBuilder RomeNumber = new StringBuilder();
        var map = new HashMap<Integer, String>();
        map.put(1, "I");
        map.put(2, "II");
        map.put(3, "III");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(6, "VI");
        map.put(7, "VII");
        map.put(8, "VIII");
        map.put(9, "IX");
        map.put(10, "X");
        var sortedMap = new TreeMap<>(map);
        while (i > 0) {
            int in = 0;
            var out = "";
            for (HashMap.Entry<Integer,String> entry: sortedMap.entrySet()){
                Integer tmp = entry.getKey();
                if (tmp > i){
                    i -= in;
                    RomeNumber.append(out);
                    break;
                } else if (tmp == i) {
                    i -= tmp;
                    RomeNumber.append(entry.getValue());
                    break;
                } else {
                    in = entry.getKey();
                    out = entry.getValue();
                }
            }
        }
        return RomeNumber.toString();
    }
     static String calc(String input) throws IOException, NumberFormatException {
        String output;
        int x = 0;
        int y = 0;
        String[] subStr;
        subStr = input.split(" ");
        int n1 = RomanToArabic(subStr[0]);
        if (n1 == -1) {
            try {
                n1 = Integer.parseInt(subStr[0]);
                if (n1 > 10) {
                    throw  new IOException("Неверное значение 1-го аргумента (больше 10)");
                }
                if (n1 < 1) {
                    throw  new IOException("Неверное значение 1-го аргумента (меньше 1)");
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Неверное значение 1-го аргумента (не число)");
            }
            x = 1;
        }
        int n2 = RomanToArabic(subStr[2]);
        if (n2 == -1) {
            try {
                n2 = Integer.parseInt(subStr[2]);
                if (n2 > 10) {
                    throw  new IOException("Неверное значение 2-го аргумента (больше 10)");
                }
                if (n2 < 1) {
                    throw  new IOException("Неверное значение 2-го аргумента (меньше 1)");
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Неверное значение 2-го аргумента (не число)");
            }
            y = 1;
        }
        if (x == y) {
            int result = Arithmetic(subStr[1],n1,n2);
            if (x == 1) {
                output = Integer.toString(result) ;
            }
            else {
                if (result > 10) {
                    throw new NumberFormatException("Ошибка");}
                output = ArabicToRoman(result);
            }
        } else throw new IOException("Ошибка");
        return output;
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(calc(input));
    }
}

