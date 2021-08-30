package practice3;

public class Part5 {

    public static void main(String[] args) {
        for (int i = 1; i < 101; i++) {
            System.out.print(i + " --> ");
            String str = decimal2Roman(i);
            System.out.println(str + " --> " + roman2Decimal(str));
        }
    }

    public static String decimal2Roman(int dec) {
        StringBuilder roman = new StringBuilder();
        int[] decimal = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLiterals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < decimal.length; i++) {
            while (dec >= decimal[i]) {
                dec -= decimal[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }

    private static int charRoman2Decimal(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return -1;
        }
    }

    public static int roman2Decimal(String roman) {
        int res = 0;
        int index = 0;
        while (index < roman.length()) {
            int digit = charRoman2Decimal(roman.charAt(index));
            if (index + 1 < roman.length()) {
                int nexDigit = charRoman2Decimal(roman.charAt(index + 1));
                if (digit >= nexDigit) res += digit;
                else {
                    res += nexDigit - digit;
                    index++;
                }
            } else res += digit;
            index++;
        }
        return res;
    }

}
