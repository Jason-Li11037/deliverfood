public class Main {
    public static void main(String[] args) {
        // Primitives
        int i = 10;
        long big = 9223372036854775807L;
        float f = 3.14f;
        double d = 100.123;
        boolean succeeded = true;
        // char, short, byte

        // Boxed values
        Integer integer = 10;
        Long aLong = 9223372036854775807L;
        Float aFloat = 3.14f;
        Double aDouble = 100.123;
        Boolean b = true;

        System.out.println(i + " " + big + " " + f + " " + d + " " + succeeded);
        System.out.println(integer + " " + aLong + " " + aFloat + " " + aDouble + " " + b);
    }
}
