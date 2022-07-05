import java.util.ArrayList;

public class ContinuedFraction {
    public static int fraction(int numerator, int denominator, ArrayList<Integer> arrayList) {
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        if (denominator % numerator == 0) {
            return denominator % numerator;
        }

        //System.out.print(Math.floorDiv(numerator, denominator) + " , ");
        arrayList.add(Math.floorDiv(numerator, denominator));
        return fraction(denominator, numerator % denominator, arrayList);
    }

    public static void table(int a, int b) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ContinuedFraction.fraction(a, b, arrayList);
        System.out.println(arrayList);
        ArrayList<Integer> P = new ArrayList<>();
        ArrayList<Integer> Q = new ArrayList<>();

        P.add(1); //P0
        Q.add(0); //Q0
        P.add(arrayList.get(0)); //P1
        Q.add(1); //Q1
        P.add(arrayList.get(0) * arrayList.get(1) + 1); //P2
        Q.add(arrayList.get(1)); //Q2

        for (int i = 2; i < arrayList.size(); i++) {
            P.add((arrayList.get(i) * P.get(i)) + P.get(i - 1));
            Q.add((arrayList.get(i) * Q.get(i)) + Q.get(i - 1));
        }
        System.out.println(P);
        System.out.println(Q);
        int x0, y0;
        if (P.size() % 2 == 0){
            x0 = -(Q.get(Q.size()-2));
            y0 = P.get(P.size()-2);
            System.out.println("x0 = " + x0 + ", y0 = " + y0);
        }else {
            x0 = Q.get(Q.size()-2);
            y0 = -(P.get(P.size()-2));
            System.out.println("x0 = " + x0 + ", y0 = " + y0);
        }

        if (b > 0) {
            System.out.println("x = " + x0 + " + " + Math.abs(b) + "k");
        } else {
            System.out.println("x = " + x0 + " - " + Math.abs(b) + "k");
        }

        if (a > 0) {
            System.out.println("y = " + y0 + " - " + Math.abs(a) + "k");
        } else {
            System.out.println("y = " + y0 + " + " + Math.abs(a) + "k");
        }
    }
}
