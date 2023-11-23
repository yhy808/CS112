import java.util.*;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter three numbers: ");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();

        if (a <= c) {
            if (c > b && a < 5) {
                System.out.println("Terriers");
            } else {
                System.out.println("Eagles");
            }
            System.out.println("Crimson");
        } else if (b < a) {
            if (b == c) {
                System.out.println("Huskies");
            } else if (b > c) {
                System.out.println("Engineers");
            } else {
                System.out.println("Bears");
            }
            if (a < c) {
                System.out.println("Lions");
            }
        } else {
            System.out.println("Big Green");
            if (a == b || b > 7) {
                System.out.println("Big Red");
            }
            if (!(b > c)) {
                System.out.println("Quakers");
            }
            if (a != c) {
                System.out.println("Bulldogs");
            }
        }
        System.out.println("Let's go!");
    }
}