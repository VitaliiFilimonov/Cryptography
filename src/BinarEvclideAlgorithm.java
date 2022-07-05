public class BinarEvclideAlgorithm {
    static boolean flag = false;

    public static void binarMethod(int a, int b) {
        int c = NOD.nod(a, b);
        System.out.println(c);
        System.out.println(Integer.toBinaryString(c));
    }

    public static String recurse(int a, int b) {
        if (a == b && flag) {
            System.out.println(2 * a);
            return "2 * (" + Integer.toBinaryString(a) + ")";
        } else if (a == b) {
            System.out.println(a);
            return Integer.toBinaryString(a);
        }

        if (a % 2 == 0 && b % 2 != 0) { //2
            a = a / 2;
            if (flag) {
                System.out.println("2(" + Integer.toBinaryString(a) + ", " + Integer.toBinaryString(b) + ")");
            } else {
                System.out.println("(" + Integer.toBinaryString(a) + ", " + Integer.toBinaryString(b) + ")");
            }
        }
        if (a % 2 != 0 && b % 2 == 0) {
            b = b / 2;
            if (flag) {
                System.out.println("2(" + Integer.toBinaryString(a) + ", " + Integer.toBinaryString(b) + ")");
            } else {
                System.out.println("(" + Integer.toBinaryString(a) + ", " + Integer.toBinaryString(b) + ")");
            }
        }
        if (a % 2 != 0 && b % 2 != 0 && a > b) { //3
            a = a - b;
            if (flag) {
                System.out.println("2(" + Integer.toBinaryString(a) + ", " + Integer.toBinaryString(b) + ")");
            } else {
                System.out.println("(" + Integer.toBinaryString(a) + ", " + Integer.toBinaryString(b) + ")");
            }
        } else if (a < b) {
            b = b - a;
            if (flag) {
                System.out.println("2(" + Integer.toBinaryString(a) + ", " + Integer.toBinaryString(b) + ")");
            } else {
                System.out.println("(" + Integer.toBinaryString(a) + ", " + Integer.toBinaryString(b) + ")");
            }
        }
        if (a % 2 == 0 && b % 2 == 0) { //1
            a = a / 2;
            b = b / 2;
            flag = true;
            System.out.println("2(" + Integer.toBinaryString(a) + ", " + Integer.toBinaryString(b) + ")");
        }
        return recurse(a, b);
    }
}
