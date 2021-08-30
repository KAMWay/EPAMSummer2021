package practice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Part6 {

    private static String getRegex(String str) {
        switch (str.toLowerCase()) {
            case "cyrl":
                return "[а-яА-ЯёЁіІєЄ]+";
            case "latn":
                return "[a-zA-Z]+";
            case "stop":
                return "stop";
            default:
                return str + ": Incorrect input";
        }
    }

    public static void main(String[] args) {
        String input = Demo.getInput("part6.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String regex;
        while (true) {
            regex = getValidRegex(br);
            if (regex.equals("stop")) break;
            System.out.println(Part3.getInput(input, regex));
        }
    }

    private static String getValidRegex(BufferedReader br) {
        while (true) {
            try {
                String regex = getRegex(br.readLine());
                if (regex.contains("Incorrect input")) System.out.println(regex);
                else return regex;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}