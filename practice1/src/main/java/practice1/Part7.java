package practice1;

public class Part7 {

    public static void main(String[] args) {
        String number = "A";
        System.out.println(getResultString(number));
        System.out.println(getResultString(rightColumn(number)));
        number = "Z";
        System.out.println(getResultString(number));
        System.out.println(getResultString(rightColumn(number)));
        number = "AZ";
        System.out.println(getResultString(number));
        System.out.println(getResultString(rightColumn(number)));
        number = "ZZ";
        System.out.println(getResultString(number));
        System.out.println(getResultString(rightColumn(number)));
    }

    public static int str2int(String number) {
        int amount = 0;
        int rankNumber = number.length();
        for (char letter : number.toCharArray()) {
            int rank = 1;
            for (int i = rankNumber - 1; i > 0; i--) {
                rank *= 26;
            }
            rankNumber -= 1;
            amount += rank * ((int) letter - (int) 'A' + 1);
        }

        return amount;
    }

    public static String int2str(int number) {
        int firstDigits = (number - 1) / 26;
        int lastDigit = (number - 1) % 26;
        char letter = (char) (lastDigit + (int) 'A');
        if (firstDigits == 0) {
            return "" + letter;
        } else {
            return int2str(firstDigits) + letter;
        }
    }

    public static String rightColumn(String number) {
        return int2str(str2int(number) + 1);
    }

    private static String getResultString(String number) {
        String result = number;
        String separator = " ==> ";
        int columnInt = str2int(number);
        number = int2str(columnInt);

        return result + separator + columnInt + separator + number;
    }

}