public class Main {
    static String foo(int score) {
        if (score >= 90) {
            return "High Pass";
        }
        if (score >= 60) {
            return "Pass";
        } else {
            return "fail";
        }
    }

    public static void main(String[] args) {
        String s = foo(70);
        System.out.println(s);
    }
}
