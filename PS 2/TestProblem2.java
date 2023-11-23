public class TestProblem2 {
    public static void main(String[] args) {
        String s1 = "don't string";
        String s2 = "me along!";
        System.out.println(s1.substring(6) + " " + s2.substring(0, 2));
        System.out.println(s1.charAt(6) + s1.substring(9) + " " + s2.substring(3,8));
        System.out.println(s1.toUpperCase().charAt(0) + s1.toUpperCase().substring(9) + s2.charAt(s2.length()-1));
        System.out.println(s1.charAt(0) + "" +s1.charAt(9) + s2.substring(0,2));
        System.out.println(s1.charAt(8));
        System.out.println(s1.substring(8,9));
        System.out.println(s1.charAt(0) + "" + s2.charAt(0));
        System.out.println(s1.indexOf(s1.charAt(9)));
        System.out.println(s1.replace(s1.charAt(4), 'u')        );
    }
}