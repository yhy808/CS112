public class ValuePair {
    private int a;
    private double b;

    public double product() {
        return this.a * this.b;
    }

    // add the new methods here
    public int getA() {
        return this.a;
    }

    public double getB() {
        return this.b;
    }
 
    public void setA(int a) {
        if (a % 2 == 0) {
            throw new IllegalArgumentException();
        } 
        this.a = a;
    }

    public void setB(double b) {
        if (b <= 0.0) {
            throw new IllegalArgumentException();
        }
        this.b = b;
    }

    public ValuePair(int a, double b) {
        this.setA(a);
        this.setB(b);
    }
}
