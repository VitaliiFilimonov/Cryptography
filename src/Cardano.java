import java.util.ArrayList;

public class Cardano {
    static void Shifr(String s) {
        s = s.toUpperCase();
        String sReplace = s.replace(" ", ""); // удаление пробела

        int length = sReplace.length();
        length /= (int) Math.sqrt(length);
        System.out.println(length);

        char[] arraySReplace = new char[sReplace.length()]; //char массив для введеной строки
        for (int i = 0; i < sReplace.length(); i++) {
            arraySReplace[i] = sReplace.charAt(i);
            System.out.print(arraySReplace[i] + ", ");
        }

        System.out.println();

        char[][] charsLetter = new char[length][length];
        ArrayList<Integer> integerArrayListI = new ArrayList<>(); // список I
        ArrayList<Integer> integerArrayListJ = new ArrayList<>(); // список J

        int CoordI = (int) (Math.random() * length);
        int CoordJ = (int) (Math.random() * length);
        integerArrayListI.add(CoordI);
        integerArrayListJ.add(CoordJ);

        for (int i = 1; i < length; ) { // рандомные клетки
            for (int j = 0; j < i; ) {
                if ((CoordI == integerArrayListI.get(j) && CoordJ == integerArrayListJ.get(j)) ||
                        (CoordI == 1 && CoordJ == 1) ||
                        (CoordI == 2 && CoordJ == 2) ||
                        (CoordI == 2 && CoordJ == 1) ||
                        (CoordI == 1 && CoordJ == 2)) {
                    CoordI = (int) (Math.random() * length);
                    CoordJ = (int) (Math.random() * length);
                    j = 0;
                } else {
                    j++;
                }
            }
            integerArrayListI.add(CoordI);
            integerArrayListJ.add(CoordJ);
            i++;
        }

        for (int i = 0; i < length; i++) {
            charsLetter[integerArrayListI.get(i)][integerArrayListJ.get(i)] = arraySReplace[i];// заполнение полей буквами(без поворота)
        }

        for (int i : integerArrayListI) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int j : integerArrayListJ) {
            System.out.print(j + " ");
        }

        System.out.println();
        System.out.println();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(charsLetter[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        char[][] chars = matrixRound(charsLetter);

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(chars[i][j] + " ");
            }
            System.out.println();
        }

    }

    static char[][] matrixRound(char[][] chars) {
        char[][] charsArray = new char[chars.length][chars.length];

        for (int i = 0; i < chars.length; i++) { // поворот на 90 по часовой
            for (int j = 0; j < chars.length; j++) {
                charsArray[j][i] = chars[chars.length - i - 1][j];
            }
        }
        return charsArray;
    }

    static void obhod() {
        char[][] chars = new char[4][4];
        char letter = 'А';
        int count = 12;
        int i = 0;
        int j = 0;

        while (count != 0) {
            if (count > 6) {
                if (j < 3) {
                    chars[i][j] = letter;
                    j++;
                } else if (i < 3) {
                    chars[i][j] = letter;
                    i++;
                }
            } else {
                if (j > 0) {
                    chars[i][j] = letter;
                    j--;
                } else if (i > 0) {
                    chars[i][j] = letter;
                    i--;
                }
            }
            count--;
        }
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 4; l++) {
                System.out.print(chars[k][l] + " ");
            }
            System.out.println();
        }

    }

}
