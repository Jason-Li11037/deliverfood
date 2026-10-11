public class Main {
    static void foo() {
        for (int i = 0; i <= 10; i += 2) {
            if (i == 2) {
                continue;
            }
            if (i == 8) {
                break;
            }
            System.out.println("i: " + i);
        }
    }

    public static void main(String[] args) {
        foo();
    }
}
