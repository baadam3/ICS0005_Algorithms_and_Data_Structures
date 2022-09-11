import java.util.*;

/**
 * Quaternions. Basic operations.
 */
public class Quaternion {

    // TODO!!! Your fields here!
    private double a;
    private double b;
    private double c;
    private double d;
    private static final double trashold = 0.000001;


    /**
     * Constructor from four double values.
     *
     * @param a real part
     * @param b imaginary part i
     * @param c imaginary part j
     * @param d imaginary part k
     */
    public Quaternion(double a, double b, double c, double d) {
        // TODO!!! Your constructor here!
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Real part of the quaternion.
     *
     * @return real part
     */
    public double getRpart() {
        return this.a; // TODO!!!
    }

    /**
     * Imaginary part i of the quaternion.
     *
     * @return imaginary part i
     */
    public double getIpart() {
        return this.b; // TODO!!!
    }

    /**
     * Imaginary part j of the quaternion.
     *
     * @return imaginary part j
     */
    public double getJpart() {
        return this.c; // TODO!!!
    }

    /**
     * Imaginary part k of the quaternion.
     *
     * @return imaginary part k
     */
    public double getKpart() {
        return this.d; // TODO!!!
    }

    /**
     * Conversion of the quaternion to the string.
     *
     * @return a string form of this quaternion:
     * "a+bi+cj+dk"
     * (without any brackets)
     */
    @Override
    public String toString() {
        // TODO!!!
        StringBuilder result = new StringBuilder();
        result.append(this.a);
        if (this.b >= 0)
            result.append("+");
        result.append(this.b + "i");
        if (this.c >= 0)
            result.append("+");
        result.append(this.c + "j");
        if (this.d >= 0)
            result.append("+");
        result.append(this.d + "k");
        return result.toString();
    }

    /**
     * Conversion from the string to the quaternion.
     * Reverse to <code>toString</code> method.
     *
     * @param s string of form produced by the <code>toString</code> method
     * @return a quaternion represented by string s
     * @throws IllegalArgumentException if string s does not represent
     *                                  a quaternion (defined by the <code>toString</code> method)
     */
    public static Quaternion valueOf(String s) {
        // TODO!!!
        // https://stackoverflow.com/questions/22170944/java-string-split-by-regexp-to-get-only-integers
        int iQ = 0;
        int jQ = 0;
        int kQ = 0;
        if (s.charAt(s.length() - 1) == '+' || s.charAt(s.length() - 1) == '-')
            throw new NumberFormatException("Invalid input in: " + s);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'i') {
                iQ++;
            } else if (s.charAt(i) == 'j') {
                jQ++;
            } else if (s.charAt(i) == 'k') {
                kQ++;
            }
            if (iQ > 1 || jQ > 1 || kQ > 1)
                throw new NumberFormatException("Invalid input in: " + s);
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+')
                throw new NumberFormatException("Invalid input in: " + s);
            else if (s.charAt(i) == '-' && s.charAt(i + 1) == '-')
                throw new NumberFormatException("Invalid input in: " + s);
        }
        s = s.replaceAll("-", "+-");
        s = s.replaceAll("[^--0.-9.,e]", ",");
        String[] numbersNottFormated = s.split(",");
        String[] numbers = new String[4];
        int index = 0;
        if (!s.contains("e")) {
            for (String number : numbersNottFormated) {
                if (!"".equals(number)) {
                    numbers[index] = number;
                    index++;
                }
            }
        } else {
            for (String number : numbersNottFormated) {
                if (!"".equals(number)) {
                    if (number.contains("e")) {
                        numbers[index] = number;
                    } else {
                        numbers[index] = numbers[index] + number;
                        index++;
                    }

                }
            }
        }
        try {
            double a2 = Double.parseDouble(numbers[0]);
            double b2 = Double.parseDouble(numbers[1]);
            double c2 = Double.parseDouble(numbers[2]);
            double d2 = Double.parseDouble(numbers[3]);
            return new Quaternion(a2, b2, c2, d2);
        } catch (Exception e) {
            throw new NumberFormatException("Invalid input in: " + s);
        }

    }

    /**
     * Clone of the quaternion.
     *
     * @return independent clone of <code>this</code>
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO!!!
        double cloneA = this.a;
        double cloneB = this.b;
        double cloneC = this.c;
        double cloneD = this.d;
        Quaternion cloneQuat = new Quaternion(cloneA, cloneB, cloneC, cloneD);
        return cloneQuat;
    }

    /**
     * Test whether the quaternion is zero.
     *
     * @return true, if the real part and all the imaginary parts are (close to) zero
     */
    public boolean isZero() {
        // TODO!!!
        if (Math.abs(this.a) < trashold && Math.abs(this.b) < trashold && Math.abs(this.c) < trashold && Math.abs(this.d) < trashold)
            return true;
        else
            return false;
    }

    /**
     * Conjugate of the quaternion. Expressed by the formula
     * conjugate(a+bi+cj+dk) = a-bi-cj-dk
     *
     * @return conjugate of <code>this</code>
     */
    public Quaternion conjugate() {
        return new Quaternion(this.a, this.b * (-1), this.c * (-1), this.d * (-1)); // TODO!!!
    }

    /**
     * Opposite of the quaternion. Expressed by the formula
     * opposite(a+bi+cj+dk) = -a-bi-cj-dk
     *
     * @return quaternion <code>-this</code>
     */
    public Quaternion opposite() {
        return new Quaternion(-this.a, -this.b, -this.c, -this.d);
        // TODO!!!
    }

    /**
     * Sum of quaternions. Expressed by the formula
     * (a1+b1i+c1j+d1k) + (a2+b2i+c2j+d2k) = (a1+a2) + (b1+b2)i + (c1+c2)j + (d1+d2)k
     *
     * @param q addend
     * @return quaternion <code>this+q</code>
     */
    public Quaternion plus(Quaternion q) {
        // TODO!!!
        Quaternion result = new Quaternion(this.a + q.a, this.b + q.b, this.c + q.c, this.d + q.d);
        return result;
    }

    /**
     * Product of quaternions. Expressed by the formula
     * (a1+b1i+c1j+d1k) * (a2+b2i+c2j+d2k) = (a1a2-b1b2-c1c2-d1d2) + (a1b2+b1a2+c1d2-d1c2)i +
     * (a1c2-b1d2+c1a2+d1b2)j + (a1d2+b1c2-c1b2+d1a2)k
     *
     * @param q factor
     * @return quaternion <code>this*q</code>
     */
    public Quaternion times(Quaternion q) {
        // TODO!!!
        double part1 = this.a * q.a - this.b * q.b - this.c * q.c - this.d * q.d;
        double part2 = this.a * q.b + this.b * q.a + this.c * q.d - this.d * q.c;
        double part3 = this.a * q.c - this.b * q.d + this.c * q.a + this.d * q.b;
        double part4 = this.a * q.d + this.b * q.c - this.c * q.b + this.d * q.a;
        return new Quaternion(part1, part2, part3, part4);
    }

    /**
     * Multiplication by a coefficient.
     *
     * @param r coefficient
     * @return quaternion <code>this*r</code>
     */
    public Quaternion times(double r) {
        return new Quaternion(this.a * r, this.b * r, this.c * r, this.d * r); // TODO!!!
    }

    /**
     * Inverse of the quaternion. Expressed by the formula
     * 1/(a+bi+cj+dk) = a/(a*a+b*b+c*c+d*d) +
     * ((-b)/(a*a+b*b+c*c+d*d))i + ((-c)/(a*a+b*b+c*c+d*d))j + ((-d)/(a*a+b*b+c*c+d*d))k
     *
     * @return quaternion <code>1/this</code>
     */
    public Quaternion inverse() {
        // TODO!!!
        if (this.isZero())
            throw new ArithmeticException("Cannot calculate inverse with these values: " + this.a + this.b + this.c + this.d);
        double part1 = a / (a * a + b * b + c * c + d * d); // here I realised I don't need to write this before the variables
        double part2 = (-b) / (a * a + b * b + c * c + d * d);
        double part3 = (-c) / (a * a + b * b + c * c + d * d);
        double part4 = (-d) / (a * a + b * b + c * c + d * d);
        return new Quaternion(part1, part2, part3, part4);
    }

    /**
     * Difference of quaternions. Expressed as addition to the opposite.
     *
     * @param q subtrahend
     * @return quaternion <code>this-q</code>
     */
    public Quaternion minus(Quaternion q) {
        return new Quaternion(this.a - q.a, this.b - q.b, this.c - q.c, this.d - q.d); // TODO!!!
    }

    /**
     * Right quotient of quaternions. Expressed as multiplication to the inverse.
     *
     * @param q (right) divisor
     * @return quaternion <code>this*inverse(q)</code>
     */
    public Quaternion divideByRight(Quaternion q) {
        if (q.isZero())
            throw new ArithmeticException("Division by 0 is not possible! Input values: " + this.a + this.b + this.c + this.d);
        return this.times(q.inverse()); // TODO!!!
    }

    /**
     * Left quotient of quaternions.
     *
     * @param q (left) divisor
     * @return quaternion <code>inverse(q)*this</code>
     */
    public Quaternion divideByLeft(Quaternion q) {
        if (q.isZero())
            throw new ArithmeticException("Division by 0 is not possible! Input values: " + this.a + this.b + this.c + this.d);
        return q.inverse().times(this); // TODO!!!
    }

    /**
     * Equality test of quaternions. Difference of equal numbers
     * is (close to) zero.
     *
     * @param qo second quaternion
     * @return logical value of the expression <code>this.equals(qo)</code>
     */
    @Override
    public boolean equals(Object qo) {
        // TODO!!!
        if (Math.abs(this.a - ((Quaternion) qo).a) < trashold && this.b - ((Quaternion) qo).b < trashold && this.c - ((Quaternion) qo).c < trashold && this.d - ((Quaternion) qo).d < trashold)
            return true;
        else
            return false;
    }

    /**
     * Dot product of quaternions. (p*conjugate(q) + q*conjugate(p))/2
     *
     * @param q factor
     * @return dot product of this and q
     */
    public Quaternion dotMult(Quaternion q) {
        // TODO!!!
        return (this.times(q.conjugate()).plus(q.times(this.conjugate()))).times(.5);
    }

    /**
     * Integer hashCode has to be the same for equal objects.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d); // TODO!!!
    }

    /**
     * Norm of the quaternion. Expressed by the formula
     * norm(a+bi+cj+dk) = Math.sqrt(a*a+b*b+c*c+d*d)
     *
     * @return norm of <code>this</code> (norm is a real number)
     */
    public double norm() {
        return Math.sqrt(a * a + b * b + c * c + d * d); // TODO!!!
    }

    /**
     * Main method for testing purposes.
     *
     * @param arg command line parameters
     */
    public static void main(String[] arg) {
       /* Quaternion arv1 = new Quaternion(-1., 1, 2., -2.);
        if (arg.length > 0)
            arv1 = valueOf(arg[0]);
        System.out.println("first: " + arv1.toString());
        System.out.println("real: " + arv1.getRpart());
        System.out.println("imagi: " + arv1.getIpart());
        System.out.println("imagj: " + arv1.getJpart());
        System.out.println("imagk: " + arv1.getKpart());
        System.out.println("isZero: " + arv1.isZero());
        System.out.println("conjugate: " + arv1.conjugate());
        System.out.println("opposite: " + arv1.opposite());
        System.out.println("hashCode: " + arv1.hashCode());
        Quaternion res = null;
        try {
            res = (Quaternion) arv1.clone();
        } catch (CloneNotSupportedException e) {
        }
        ;
        System.out.println("clone equals to original: " + res.equals(arv1));
        System.out.println("clone is not the same object: " + (res != arv1));
        System.out.println("hashCode: " + res.hashCode());
        res = valueOf(arv1.toString());
        System.out.println("string conversion equals to original: "
                + res.equals(arv1));
        Quaternion arv2 = new Quaternion(1., -2., -1., 2.);
        if (arg.length > 1)
            arv2 = valueOf(arg[1]);
        System.out.println("second: " + arv2.toString());
        System.out.println("hashCode: " + arv2.hashCode());
        System.out.println("equals: " + arv1.equals(arv2));
        res = arv1.plus(arv2);
        System.out.println("plus: " + res);
        System.out.println("times: " + arv1.times(arv2));
        System.out.println("minus: " + arv1.minus(arv2));
        double mm = arv1.norm();
        System.out.println("norm: " + mm);
        System.out.println("inverse: " + arv1.inverse());
        System.out.println("divideByRight: " + arv1.divideByRight(arv2));
        System.out.println("divideByLeft: " + arv1.divideByLeft(arv2));
        System.out.println("dotMult: " + arv1.dotMult(arv2));*/
        //System.out.println(valueOf("-2.1e-2-3.2e-1i-4.3e+2j-5.4e2k")); //should evaluete to "-0.021-0.32i-430.0j-540.0k"
        //System.out.println(valueOf("-2.1e-2-3.2e-1i-4.3e+2j-5.4e2kk")); //should cause exception.
        System.out.println(valueOf("-2.1e-2-3.2e-1i-4.3e+2j-5.4e2k+")); //should cause exception.
    }
}
// end of file
