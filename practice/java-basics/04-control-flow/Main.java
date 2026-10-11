import java.util.List;

public class Main {
    static String grade(int score) {
        if (score >= 60) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    public static void main(String[] args) {
        System.out.println(grade(70));

        for (int i = 0; i < 10; i++) {
            if (i == 8) {
                continue;
            }
            System.out.println("i: " + i);
        }

        List<String> strings = List.of("Hello", "你好", "end", "Hola");
        for (String s : strings) {
            if (s.equals("end")) {
                break;
            }
            System.out.println("Str: " + s);
        }

        int day = 6;
        // int, enum, string
        switch (day) {
            case 6:
                System.out.println("Today is Saturday");
                break;
            case 7:
                System.out.println("Today is Sunday");
                break;
            default:
                System.out.println("Looking forward to the Weekend");
        }

        int i = 0;
        while (i < 5) {
            System.out.println(i);
            i++;
        }
    }
}
