public class ScopePuzzle {
    public static void myMethod(int e) {
        int i;
        for (i = 0; i < 10; i++) {
            System.out.println(i);        // first println

            int a = 5;
            for (int j = 0; j < 3; j++) {
                int b = 0;
                System.out.println(b);    // second println
            }
            System.out.println(a);        // third println
        }

        int y = 3;
        System.out.println(y);            // fourth println
    }

    public static void main(String[] args) {
        int c = 0;

        System.out.println(c);            // fifth println

        int d = 1;
        myMethod(c);

        System.out.println(d);            // sixth println
    }
}