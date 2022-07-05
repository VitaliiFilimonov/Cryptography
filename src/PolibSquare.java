import java.io.*;
import java.util.*;

public class PolibSquare {
    static ArrayList Shifr(String s) { // 1упражнение
        String S1 = s.toUpperCase();
        char[][] arrLetter = new char[5][6];
        char letter = 'А';

        for (int i = 0; i < 5; i++) { //двумерный буквенный массив
            for (int j = 0; j < 6; j++) {
                if (letter == 'Й' || letter == 'Ъ') {
                    letter++;
                }
                arrLetter[i][j] = letter;
                letter++;
            }
        }

        char[] arrString = S1.toCharArray();
        ArrayList<Integer> arrIntList = new ArrayList<Integer>();

        for (char c : arrString) {
            if (c == ' ') {
                arrIntList.add(-1);
            }
            if (c == 'Й') {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (arrLetter[i][j] == 'И') {
                            arrIntList.add(i);
                            arrIntList.add(j);
                        }
                    }
                }
            }
            if (c == 'Ъ') {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (arrLetter[i][j] == 'Ь') {
                            arrIntList.add(i);
                            arrIntList.add(j);
                        }
                    }
                }
            }
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    if (c == arrLetter[i][j]) {
                        arrIntList.add(i);
                        arrIntList.add(j);
                    }
                }
            }
        }
        return arrIntList;
    }

    static String DeShifr(ArrayList<Integer> ArrL) { // 2упражнение
        char[][] arrLetter = new char[5][6];
        char letter = 'А';

        for (int i = 0; i < 5; i++) { //двумерный буквенный массив
            for (int j = 0; j < 6; j++) {
                if (letter == 'Й' || letter == 'Ъ') {
                    letter++;
                }
                arrLetter[i][j] = letter;
                letter++;
            }
        }

        ArrayList<Character> ChArrL = new ArrayList<Character>();

        for (int x = 0; x < ArrL.size(); ) {
            if (ArrL.get(x) == -1) { // пробел
                ChArrL.add(' ');
                x++;
            } else {
                ChArrL.add(arrLetter[ArrL.get(x)][ArrL.get(x + 1)]);
                x += 2;
            }
        }
        String Str = "";
        for (char s : ChArrL) { // конкатенация символов в строку
            Str += s;
        }

        return Str;
    }

    static String DeShifr(int[] arr) {
        char[][] arrLetter = new char[5][6];
        char letter = 'А';

        for (int i = 0; i < 5; i++) { //двумерный буквенный массив
            for (int j = 0; j < 6; j++) {
                if (letter == 'Й' || letter == 'Ъ') {
                    letter++;
                }
                arrLetter[i][j] = letter;
                letter++;
            }
        }

        ArrayList<Character> ChArrL = new ArrayList<Character>();
        for (int x = 0; x < arr.length; ) {
            if (arr[x] == -1) {
                ChArrL.add(' ');
                x++;
            } else {
                ChArrL.add(arrLetter[arr[x]][arr[x + 1]]);
                x += 2;
            }
        }
        String Str = "";
        for (char s : ChArrL) { // конкатенация символов в строку
            Str += s;
        }

        ArrayList<Boolean> checkArrL = isExist(Str);

        boolean flag = false;

        for (int x = 0; x < checkArrL.size(); x++) {
            if (!checkArrL.get(x)) {
                System.out.println(x + 1 + " слово - не найдено в словаре");
                flag = true;
            }
        }
        if (!flag) {
            return Str;
        } else {
            System.out.println(Str);
            Str = loop(translateArrayToInt(arr, checkArrL), arrLetter);
            return Str;
        }
    }

    static ArrayList isExist(String Str) {
        String StrMas[] = splitString(Str);
        for (int i = 0; i < StrMas.length; i++) {
            StrMas[i] = StrMas[i].toLowerCase();
        }

        ArrayList<Boolean> checkArrL = new ArrayList<Boolean>();

        for (String s : StrMas) {
            try {
                File file = new File("C:\\Users\\Виталий\\Desktop\\russian.txt");
                FileReader freader = new FileReader(file);
                BufferedReader breader = new BufferedReader(freader);

                String line = breader.readLine();
                while (line != null) {
                    //System.out.println(line);
                    if (line.equals(s)) {
                        //System.out.println(true);
                        checkArrL.add(true);
                        break;
                    }
                    line = breader.readLine();
                }
                if (line == null) {
                    //System.out.println(false);
                    checkArrL.add(false);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return checkArrL;
    }// есть ли слово в словаре

    static String[] splitString(String s) {
        String[] words = s.split(" ");
        return words;
    }// разделение предложения на массив

    static int[] translateArrayToInt(int[] arr, ArrayList<Boolean> checkArrL) {
        String TranslateInt = Arrays.toString(arr);
        String[] DefectWords = TranslateInt.split("-1");
        for (int i = 0; i < DefectWords.length; i++) {
            DefectWords[i] = DefectWords[i].replaceAll(",", "");
            DefectWords[i] = DefectWords[i].replace("[", "");
            DefectWords[i] = DefectWords[i].replace("]", "");
            DefectWords[i] = DefectWords[i].replaceFirst(" ", "");
        }

        for (int i = 0; i < checkArrL.size(); i++) {
            if (checkArrL.get(i) == false) {
                DefectWords[i] = swapElements(DefectWords[i]);
            }
        }
        for (int i = 0; i < DefectWords.length; i++) {
            DefectWords[i] = DefectWords[i].replaceAll(",", "");
            DefectWords[i] = DefectWords[i].replace("[", "");
            DefectWords[i] = DefectWords[i].replace("]", "");
            DefectWords[i] = DefectWords[i].replaceAll(" ", "");
        }

        StringJoiner joiner = new StringJoiner("-");//добавление разделителя
        for (String s : DefectWords) {
            joiner.add(s);
        }
        //System.out.println(joiner);

        String joined = joiner.toString();
        //System.out.println(joined.charAt(3)); //значение char по индексу
        int[] ints = new int[joined.length()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = Character.getNumericValue(joined.charAt(i)); // возвращает численное значение от char
        }
        //System.out.println(Character.getNumericValue('-')); // возвращает -1
        return ints;
    } //меняет в словах(false) эл-ты местами

    static String swapElements(String Str) {
        String[] arr = Str.split(" ");
        int[] mas = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            mas[i] = Integer.parseInt(arr[i]);
        }
        int temp;
        for (int i = 0; i < mas.length - 1; i++) {
            temp = mas[i];
            mas[i] = mas[i + 1];
            mas[i + 1] = temp;
            i++;
        }
        String Word = Arrays.toString(mas);
        return Word;
    }//смена соседних элемнтов

    static String loop(int[] arr, char[][] arrLetter) {
        ArrayList<Character> ChArrL = new ArrayList<Character>();
        for (int x = 0; x < arr.length; ) {
            if (arr[x] == -1) {
                ChArrL.add(' ');
                x++;
            } else {
                ChArrL.add(arrLetter[arr[x]][arr[x + 1]]);
                x += 2;
            }
        }
        String Str = "";
        for (char s : ChArrL) { // конкатенация символов в строку
            Str += s;
        }

        return Str;
    }

    static char[][] randomAlphabeth() {
        ArrayList<Integer> integerArrayListI = new ArrayList<>(); //список I-тых координат
        ArrayList<Integer> integerArrayListJ = new ArrayList<>(); //список J-тых координат

        char[][] arrayLetters = new char[5][6]; // массив букв
        char[] allLetters = new char[30];
        char letter = 'А';

        for (int i = 0; i < 30; i++) { // заполнение промежуточного массива с буквами
            if (letter == 'Й' || letter == 'Ъ') {
                letter++;
            }
            allLetters[i] = letter;
            letter++;
        }


        int RandI = (int) (Math.random() * 5); // рандомайзеры координат
        int RandJ = (int) (Math.random() * 6);
        integerArrayListI.add(RandI);
        integerArrayListJ.add(RandJ);

        for (int i = 1; i < 30; ) { // проверка не совпадают ли новые координаты со старыми
            RandI = (int) (Math.random() * 5);
            RandJ = (int) (Math.random() * 6);
            for (int j = 0; j < i; ) {
                if (RandI == integerArrayListI.get(j) && RandJ == integerArrayListJ.get(j)) {
                    RandI = (int) (Math.random() * 5);
                    RandJ = (int) (Math.random() * 6);
                    j = 0;
                } else {
                    j++;
                }
            }
            integerArrayListI.add(RandI);
            integerArrayListJ.add(RandJ);
            i++;
        }

        for (int i = 0; i < 30; i++) {
            arrayLetters[integerArrayListI.get(i)][integerArrayListJ.get(i)] = allLetters[i]; // заполнение двумерного
            //массива буквами из промежуточного
        }

        return arrayLetters;
    }

    static void deShifrPereborom(int[] arr, boolean isTurn) {
        // если isTurn = true , значит строку-столбец меняем местами, если false все огонь

        char[][] alphabeth;
        String Str;

        while (true) {
            alphabeth = randomAlphabeth();
            ArrayList<Character> characterArrayList = new ArrayList<Character>();
            for (int x = 0; x < arr.length; ) {
                characterArrayList.add(alphabeth[arr[x]][arr[x + 1]]);
                x += 2;
            }

            Str = "";
            for (char c : characterArrayList) { // конкатенация символов в строку
                Str += c;
            }
            //System.out.println(Str);

            ArrayList<Boolean> booleanArrayList = isExist(Str);
            if (booleanArrayList.get(0)) {
                System.out.println(Str);
                //break;
            }
            Str = "";
            alphabeth = null;
        }
    }

}
