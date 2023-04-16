import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Calculator {

    static HashMap<String, Integer> pairs = new HashMap<>();
    static {
        pairs.put("I", 1);
        pairs.put("II", 2);
        pairs.put("III", 3);
        pairs.put("IV", 4);
        pairs.put("V", 5);
        pairs.put("VI", 6);
        pairs.put("VII", 7);
        pairs.put("VIII", 8);
        pairs.put("IX", 9);
        pairs.put("X", 10);
        pairs.put("XX", 20);
        pairs.put("XXX", 30);
        pairs.put("XL", 40);
        pairs.put("L", 50);
        pairs.put("LX", 60);
        pairs.put("LXX", 70);
        pairs.put("LXXX", 80);
        pairs.put("XC", 90);
        pairs.put("C", 100);
    }
    static Set<Map.Entry<String, Integer>> entrySet = pairs.entrySet();
    static List<String> availableOperations = Arrays.asList("+","-","*","/");

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        if (input.length != 3) throw new Exception("Введено некорректное выражение");
        if (!availableOperations.contains(input[1])) throw new Exception("Введена некорректная операция");
        Integer n1 = null;
        Integer n2 = null;
        try {
            n1 = Integer.parseInt(input[0]);
        } catch (NumberFormatException ex) {
            //NOP
        }

        try {
            n2 = Integer.parseInt(input[2]);
        } catch (NumberFormatException ex) {
            //NOP
        }

        if (n1 == null && n2 != null || n1 != null && n2 == null) {
            throw new Exception("Используются одновременно разные системы счисления");
        }

        if (n1 == null) {
            n1 = pairs.get(input[0]);
            n2 = pairs.get(input[2]);
            if (n1 == null || n2 == null) throw new Exception("Введено некорректное число");

            int result = operation(n1, input[1], n2);
            if (result <= 0) {
                throw new Exception("Результат операции над римскими числами должен быть больше 0");
            }

            StringBuilder sb = new StringBuilder();
            if ((result / 10) * 10 > 0){
                merge((result) / 10 * 10, sb);
            }
            if (result % 10 > 0) {
                merge(result % 10, sb);
            }
            System.out.println(sb);
        } else {
            int result = operation(n1, input[1], n2);
            System.out.println(result);
        }
    }

    static int operation(int n1, String operation, int n2) throws Exception {
        if (n1 > 10 || n2 > 10) throw new Exception("Введенные числа должны бать не больше 10");
        int result = 0;
        switch (operation) {
            case ("+"):
                result = n1 + n2;
                break;
            case ("-"):
                result = n1 - n2;
                break;
            case ("*"):
                result = n1 * n2;
                break;
            case ("/"):
                result = n1 / n2;
        }
        return result;
    }

    static void merge(int number, StringBuilder sb){
        for (Map.Entry<String, Integer> pair : entrySet) {
            if (number == pair.getValue()) {
                sb.append(pair.getKey());
                break;
            }
        }
    }
}
