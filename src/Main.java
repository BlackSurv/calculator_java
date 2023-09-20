import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическое выражение с использованием двух арабских (2+3) или двух римских чисел (II+VI) от I до X и нажмите Enter: ");
        String userInput = scanner.nextLine();
        System.out.println(calc(userInput));
    }

    public static String calc(String input) throws Exception  {
        String [] actions = {"+","-","/","*"};
        int num_1, num_2;
        String operation = null;
        String result;
        boolean isRoman;

        //Ищем оператор в выражении
        for (String action : actions) {
            if (input.contains(action)) {
                operation = action;
                break;
            }
        }
        if (operation == null) {
            throw new Exception( "Неподдерживаемая математическая операция");
        }
        //Ищем операнды
        //String[] numbers = input.split(operation);
        String[] numbers = input.split(" [\\+-/\\*] ");
        //String[] numbers = input.split("[^XVILC\\d]");
        if (numbers.length != 2) {
            throw new Exception( "Должно быть два операнда");
        }

        if(RomanNumbers.isRoman(numbers[0]) && RomanNumbers.isRoman(numbers[1])){
            num_1 = RomanNumbers.convToArab(numbers[0]);
            num_2 = RomanNumbers.convToArab(numbers[1]);
            isRoman = true;
        } else if (!RomanNumbers.isRoman(numbers[0]) && !RomanNumbers.isRoman(numbers[1])) {
            num_1 = Integer.parseInt(numbers[0]);
            num_2 = Integer.parseInt(numbers[1]);
            isRoman = false;
        }else{
            throw new Exception("Числа должны быть в одном формате");
        }

        if (num_1 > 10 || num_2 > 10){
            throw new Exception( "Числа должны быть от 1 до 10");
        }

        int arabianResult = switch (operation) {
            case "+" -> num_1 + num_2;
            case "-" -> num_1 - num_2;
            case "/" -> num_1 / num_2;
            case "*" -> num_1 * num_2;
            default -> 0;
        };

        if (isRoman) {
            if (arabianResult<=0) {
                throw new Exception( "Римское число должно быть больше нуля");
            }
            result= RomanNumbers.convertToRoman(arabianResult);
        } else {
            result=String.valueOf(arabianResult);
        }
    return result;
    }

    static class RomanNumbers {
        static String[] arrRomanNum = new String[]{
                "0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                "XCVIII", "XCIX", "C"
        };
        static boolean isRoman(String number){
            for (String s : arrRomanNum) {
                if (number.equals(s)) {
                    return true;
                }
            }
            return false;
        }
        static int convToArab(String romanNumber) {
            for (int i = 0; i < arrRomanNum.length; i++) {
                if (romanNumber.equals(arrRomanNum[i])){
                    return i;
                }
            }
            return -1;
        }
        static String convertToRoman(int arabian) {
            return arrRomanNum[arabian];
        }
    }
}
