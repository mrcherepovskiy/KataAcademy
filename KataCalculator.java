import java.util.Scanner;

public class KataCalculator {
    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {


        Scanner scan = new Scanner(System.in);

        String separator = scan.nextLine();

        scan.close();

        //Делим строку на элементы
        String[] array = separator.split(" ");

        if (ArabicOrRoman(array)) {

            int firstVar = Integer.parseInt(array[0]);
            int secondVar = Integer.parseInt(array[2]);
            String sign = array[1];

            ErrorDetected(array, firstVar, secondVar);

            AnswerArabic(sign, firstVar, secondVar);
        } else {

            int firstVar = toArabicNumber(array[0]);
            int secondVar = toArabicNumber(array[2]);

            String sign = array[1];

            ErrorDetected(array, firstVar, secondVar);

            AnswerRoman(sign, firstVar, secondVar);
        }
    }
    //Работаем с римскими цифрами, исключение (деление на ноль)
    private static void AnswerRoman(String significance, int a, int b) throws ArithmeticException {
        double tmpAnswer = 0;
        String answer = "";
        try {
            switch (significance) {
                case "+":
                    tmpAnswer = a + b;
                    break;
                case "/":
                    if (b == 0)
                        throw new ArithmeticException();
                    tmpAnswer = (double) a / b;
                    break;
                case "-":
                    tmpAnswer = a - b;
                    break;
                case "*":
                    tmpAnswer = a * b;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (ArithmeticException aex) {
            System.err.println("Делить на ноль нельзя!");
            System.exit(0);
        } catch (IllegalArgumentException iaex) {
            System.err.println("Такой знак не поддерживается!");
            System.exit(0);
        }
        while (tmpAnswer >= 100) {
            answer += "C";
            tmpAnswer -= 100;
        }
        while (tmpAnswer >= 90) {
            answer += "XC";
            tmpAnswer -= 90;
        }
        while (tmpAnswer >= 50) {
            answer += "L";
            tmpAnswer -= 50;
        }
        while (tmpAnswer >= 40) {
            answer += "XL";
            tmpAnswer -= 40;
        }
        while (tmpAnswer >= 10) {
            answer += "X";
            tmpAnswer -= 10;
        }
        while (tmpAnswer >= 9) {
            answer += "IX";
            tmpAnswer -= 9;
        }
        while (tmpAnswer >= 5) {
            answer += "V";
            tmpAnswer -= 5;
        }
        while (tmpAnswer >= 4) {
            answer += "IV";
            tmpAnswer -= 4;
        }
        while (tmpAnswer >= 1) {
            answer += "I";
            tmpAnswer -= 1;
        }
        System.out.println(answer);
    }

    //  Обрабатываем исключения
    private static void ErrorDetected(String[] k, int a, int b) {
        try {
            if ((a > 10 || a < 0) || (b > 10 || b < 0) || !(k.length == 3)) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException iaex) {
            System.err.println("Неверный формат ввода данных!");
            System.exit(0);
        }
    }

    //  Определяем какие введены числа: арабские или римские
    private static boolean ArabicOrRoman(String[] k) throws NumberFormatException {
        try {
            Integer.parseInt(k[0]);
            Integer.parseInt(k[2]);
            return true;
        } catch (NumberFormatException nfex) {
            return false;
        }
    }
    //  Конвертируем арабские числа в римские
    private static int toArabicNumber(String roman) {
        switch (roman) {
            case "0":
                return 0;
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                return -1;
        }
    }

    //  Выводим ответ по выражениям с арабскими числами, исключение (деление на ноль)
    private static void AnswerArabic(String sign, int a, int b) throws ArithmeticException {
        try {
            switch (sign) {
                case "+":
                    System.out.println(a + b);
                    break;
                case "/":
                    System.out.println(a / b);
                    break;
                case "-":
                    System.out.println(a - b);
                    break;
                case "*":
                    System.out.println(a * b);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (ArithmeticException aex) {
            System.err.println("Делить на ноль нельзя!");
            System.exit(0);
        } catch (IllegalArgumentException iaex) {
            System.err.println("Такой знак не поддерживается!");
            System.exit(0);
        }
    }
}
