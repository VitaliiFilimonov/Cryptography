public class LinearDiafantEquations {

//    public static int extendedEvklidMethod(int a, int b, int x, int y) {
//        if (a == 0) {
//            x = 0;
//            y = 1;
//            return b;
//        }
//        int x1 = 1;
//        int y1 = 1;
//        int Nod = extendedEvklidMethod(b % a, a, x1, y1);
//        x = y1 - (b / a) * x1;
//        y = x1;
//        System.out.println(x + ", " + y);
//        return Nod;
//    }

    public static String equations(int a, int b, int c) { // a, b - любые, c = 1
        int a1 = a;
        int b1 = b;
        a = Math.abs(a);
        b = Math.abs(b);
        int x1, y1, x2, y2, tmp;
        {
            x1 = 1;
            y1 = 0;
            x2 = 0;
            y2 = 1;
        }
        int x, y;
        if (c % NOD.nod(a1, b1) != 0) {
            return "c = " + c + ", НОД = " + NOD.nod(a1, b1) + " Решений в целых числах нет!";
        } else {
            while (a != NOD.nod(a, b)) {
                x = x1 - x2 * (Math.floorDiv(a, b));
                y = y1 - y2 * (Math.floorDiv(a, b));
                tmp = b;
                b = a % b;
                a = tmp;
                x1 = x2;
                y1 = y2;
                x2 = x;
                y2 = y;
            }
            if (a1 < 0) {
                x1 *= (-1);
            }
            if (b1 < 0) {
                y1 *= (-1);
            }
            if (c != 1) {
                x1 *= c;
                y1 *= c;
            }
            if (c % NOD.nod(a1, b1) == 0) {
                int del = NOD.nod(a1, b1);
                x1 /= del;
                y1 /= del;
                a1 /= del;
                b1 /= del;
            }
            if (a1 == 0 && b1 == 0) {
                System.out.println("Нет решений");
            } else if (a1 == 0) {
                System.out.println("x - любое " + ", y = " + (double) (c / b1) + ", НОД = " + NOD.nod(a1, b1));
            } else if (b1 == 0) {
                System.out.println("x = " + (double) (c / a1) + ", y - любое" + ", НОД = " + NOD.nod(a1, b1));
            } else {
                //System.out.println("x = " + x1 + ", y = " + y1 + ", НОД = " + NOD.nod(a1, b1)); // первые найденные корни
                System.out.println("x = " + x1 + ", y = " + y1);
            }

            String str1;
            String str2;

            if (b1 > 0) {
                str1 = "x = " + x1 + " + " + Math.abs(b1) + "k";
            } else {
                str1 = "x = " + x1 + " - " + Math.abs(b1) + "k";
            }

            if (a1 > 0) {
                str2 = "y = " + y1 + " - " + Math.abs(a1) + "k";
            } else {
                str2 = "y = " + y1 + " + " + Math.abs(a1) + "k";
            }

            return str1 + "\n" + str2;
        }

    }

    public static void naturalSolution(int a, int b, int c) {
        String str = equations(a, b, c);
        System.out.println(str);
        str = str.replaceAll("k", "");
        String[] arr = str.split("\n");
        String[] arr1 = arr[0].split(" ");
        String[] arr2 = arr[1].split(" ");

        int x0, y0, a1, b1;
        {
            x0 = Integer.parseInt(arr1[2]);
            y0 = Integer.parseInt(arr2[2]);

            if (arr1[3].equals("+")){
                a1 = Integer.parseInt(arr1[4]);
            }else {
                a1 = -1 * Integer.parseInt(arr1[4]);
            }

            if (arr2[3].equals("+")){
                b1 = Integer.parseInt(arr2[4]);
            }else {
                b1 = -1 * Integer.parseInt(arr2[4]);
            }
        }

        System.out.println("x0 = " + x0 + ", y0 = " + y0);
        System.out.println("a1 = " + a1 + ", b1 = " + b1);
        System.out.println();
        int resX = 0;
        int resY = 0;
        for (int i = 0; i <= 200; i++){
            resX = x0 + a1*i;
            resY = y0 + b1*i;
            if (resX > 0 && resY > 0){
                System.out.println("x = " + resX + ", y = " + resY + ", k = " + i);
            }
        }
    }

}
