import java.util.Scanner;

public class Main {

    static String calc = "";
    public static final String SPLIT_SYMBOLS = "[/*+-]";
    public static final String ARABIC_NUMERALS_CHECK = "[1234567890 /*+-]+";
    public static final String ROMAN_NUMERALS_CHECK = "[IVXLC /*+-]+";


    public static void main(String[] args) throws Exception {

        while (true) {
            String input = inputExpression();
            System.out.println(calc(input));

        }
    }

    public static String calc(String input) throws Exception {

        System.out.println("Input:" + "\n" + input + "\n");

        String[] split = input.split(SPLIT_SYMBOLS);
        char[] chars = input.toCharArray();

        notMathOperationCheck(split);
        stringLengthCheck(split);

        if (input.matches(ARABIC_NUMERALS_CHECK)) {

            int a = Integer.parseInt(split[0].trim());
            int b = Integer.parseInt(split[1].trim());
            rangeInputNumberArabicCheck(a, b);

            for (char symbol : chars) {
                switch (symbol) {
                    case ('+'):
                        calc = ("Output:\n" + (a + b));
                        return calc;
                    case ('-'):
                        calc = ("Output:\n" + (a - b));
                        return calc;
                    case ('*'):
                        calc = ("Output:\n" + (a * b));
                        return calc;
                    case ('/'):
                        calc = ("Output:\n" + (a / b));
                        return calc;
                }
            }
        } else if (input.matches(ROMAN_NUMERALS_CHECK)) {

            RomanNumerals a = RomanNumerals.valueOf(split[0].trim());
            RomanNumerals b = RomanNumerals.valueOf(split[1].trim());
            rangeInputNumberRomanCheck(a, b);

            for (char symbol : chars) {
                switch (symbol) {
                    case ('+'):
                        calc = ("Output:\n" + getByValue(a.getNumber() + b.getNumber()));
                        return calc;
                    case ('-'):
                        calc = ("Output:\n" + getByValue(a.getNumber() - b.getNumber()));
                        return calc;
                    case ('*'):
                        calc = ("Output:\n" + getByValue(a.getNumber() * b.getNumber()));
                        return calc;
                    case ('/'):
                        calc = ("Output:\n" + getByValue(a.getNumber() / b.getNumber()));
                        return calc;
                }
            }
        } else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        return calc;
    }


    private static void rangeInputNumberRomanCheck(RomanNumerals a, RomanNumerals b) throws Exception {
        if ((a.getNumber() < 1 || a.getNumber() > 10) || (b.getNumber() < 1 || b.getNumber() > 10)) {
            throw new Exception("Формат математической операции не удовлетворяет заданию " +
                    "- операнд вне допустимого диапазона (от I до X)");
        }
    }


    private static RomanNumerals getByValue(int value) throws Exception {
        for (RomanNumerals romanNumerals : RomanNumerals.values()) {
            if (romanNumerals.getNumber() == value)
                return romanNumerals;
        }
        throw new Exception("В римской системе нет отрицательных чисел");
    }


    private static String inputExpression() {
        System.out.println("\nEnter expression:");                              // т.к. в примере требований к заданию ввод/вывод были на английском, то и это так же
        return new Scanner(System.in).nextLine().trim();
    }


    private static void stringLengthCheck(String[] split) throws Exception {
        if (split.length != 2) {                                                    // проверка на длинну строки
            throw new Exception("Формат математической операции не удовлетворяет заданию " +
                    "- два операнда и один оператор (+, -, /, *)");
        }
    }


    private static void notMathOperationCheck(String[] split) throws Exception {
        if (split.length == 1) {                                                    // выброс иисключения для ввода одного операнда
            throw new Exception("Cтрока не является математической операцией");
        }
    }

    
    private static void rangeInputNumberArabicCheck(int a, int b) throws Exception {
        if ((a < 1 || a > 10) || (b < 1 || b > 10)) {                   // ограничитель чисел от 1 до 10 включительно

            throw new Exception("Формат математической операции не удовлетворяет заданию " +
                    "- операнд вне допустимого диапазона (от 1 до 10)");
        }
    }
}

