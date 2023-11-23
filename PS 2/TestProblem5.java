public class TestProblem5 {
    public static void main(String[] args) {
        int[][] twoD = { {1, 2, 3, 4},
                 {5, 6, 7, 8},
                 {9, 10, 11, 12},
                 {13, 14, 15, 16} };
        for (int i = 0; i < twoD.length; i++) {
            for (int j = 0; j < twoD[i].length; j++) {
                if (twoD[i][j] == 10) {
                    twoD[i][j] = 30;
                }
            }
        }

        for (int i = 0; i < twoD.length; i++) {
            System.out.println(twoD[i][twoD[i].length - 1]);
        }

        for (int i = 0; i < twoD.length; i++) {
            System.out.println(twoD[i][i]);
        }
    }
}
