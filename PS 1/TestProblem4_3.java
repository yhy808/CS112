public class TestProblem4_3 {
    public static void main (String[] args) {
        for (int i = 1; i < 4; i++) {
            System.out.println("** " + i + " **");
            for (int j = 3; j > i - 1; j--) {
                System.out.println(i + " " + j);        
            }
        }
    }
}
        