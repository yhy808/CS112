public class ValuePairClient {
    public static void main(String[] args) {
        ValuePair vp1 = new ValuePair(7, 15.5);
        vp1.setA(25);
        double b1 = vp1.getB();
        ValuePair vp2 = new ValuePair(5, 6.5);
        vp2.setA(vp2.getA() + 2);
        System.out.print(b1);
    }
}
