import java.io.*;
import java.util.*;

public class Caesar {
    static ArrayList Shifr(String s, int sdvig) {

        String[] words = s.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toUpperCase();
        }

        char letter = 'А';

        List<String> stringList = new ArrayList<>(Arrays.asList(words));// список из слов + пробел
        ArrayList<Character> characterArrayList = new ArrayList<>(); //буквенный список

        for (int i = 0; i < 32; i++) { // заполнение списка буквами
            characterArrayList.add(letter);
            if (letter == 'Е') {
                characterArrayList.add('Ё');
            }
            letter++;
        }
//        for (char c : characterArrayList) {
//            System.out.print(c + " ");
//        }

        ArrayList<Character> sdvigCharacterArrayList = new ArrayList<>();

        int end = 32;
        for (int i = 0; i < 33; i++) { // смещенный алфавитный список
            if (i < 33 - sdvig) {
                sdvigCharacterArrayList.add(characterArrayList.get(sdvig + i));
            } else {
                sdvigCharacterArrayList.add(characterArrayList.get(32 - end));
                end--;
            }
        }

        ArrayList<Character> newCharacterArrayList = new ArrayList<>();
        int count = 0;
        for (String Str : stringList) {
            for (int i = 0; i < Str.length(); i++) {
                for (int j = 0; j < characterArrayList.size(); j++) {
                    if (Str.charAt(i) == characterArrayList.get(j)) {
                        newCharacterArrayList.add(sdvigCharacterArrayList.get(j)); // шифровка
                    }
                }
            }
            count++;
            if (count < stringList.size()) {
                newCharacterArrayList.add('.');
            }
        }
        System.out.println();

//        for (char c : sdvigCharacterArrayList) {
//            System.out.print(c + " ");
//        }
        System.out.println();
        return newCharacterArrayList;
    }

    static void Deshifr(ArrayList arrayList) {
        char letter = 'А';
        ArrayList<Character> characterArrayList = new ArrayList<>(); //буквенный список

        for (int i = 0; i < 32; i++) { // заполнение списка буквами
            characterArrayList.add(letter);
            if (letter == 'Е') {
                characterArrayList.add('Ё');
            }
            letter++;
        }
        System.out.println();
        String s1 = "РОЁДР";

        //String stringArrayList = arrayList.toString();
        String stringArrayList = s1;
        stringArrayList = stringArrayList
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
                .replace(" ", "")
                .replace(".", " ");
        //System.out.println(stringArrayList);

        String[] splitArrayList = stringArrayList.split(" "); //разделеное предложение на массив строк
        //System.out.println(splitArrayList[0]);
        String Str = "";

        char[] chars;
        int count = 0;
        ArrayList<Character> sdvigArrayList;
        //System.out.println(characterArrayList.size());

        ArrayList<Character> pereborArrayList;
        for (int z = 1; z <= 15; z++) {
            pereborArrayList = combinationOfLetters(z);
            for (int k = 0; k < splitArrayList.length; k++) {
                chars = new char[splitArrayList[k].length()];
                for (int x = 0; x < pereborArrayList.size(); x++) {
                    sdvigArrayList = sArrayList(x, pereborArrayList); // дешифровка перебором
                    for (int i = 0; i < splitArrayList[k].length(); i++) {
                        for (int j = 0; j < sdvigArrayList.size(); j++) {
                            if (splitArrayList[k].charAt(i) == sdvigArrayList.get(j)) {
                                chars[i] = pereborArrayList.get(j);
                            }
                        }
                    }
                    if (inDictionary(chars)) {
                        count = x;
                        for (char c : chars) {
                            Str += c;
                        }
                        System.out.println(Str + ", " + count);
                    }
                    Str = "";
                }
                System.out.println("_________________________________");
            }
            System.out.println("******************************");
            System.out.println("z = " + z);
            System.out.println("*******************************");
        }


    }

    static boolean inDictionary(char[] chars) { // метод проверки в словаре
        String Str = "";
        for (char c : chars) {
            Str += c; // сборка конкатенацией слова
        }
        Str = Str.toLowerCase();
        try {
            File file = new File("C:\\Users\\Виталий\\Desktop\\russian.txt");
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);

            String line = bReader.readLine();
            while (line != null) {
                //System.out.println(line);
                if (line.equals(Str)) {
                    return true;
                }
                line = bReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    static ArrayList sArrayList(int sdvig, ArrayList<Character> characterArrayList) {
//          char letter = 'А';
//        ArrayList<Character> characterArrayList = new ArrayList<>(); //буквенный список
//
//        for (int i = 0; i < 32; i++) { // заполнение списка буквами
//            characterArrayList.add(letter);
//            if (letter == 'Е') {
//                characterArrayList.add('Ё');
//            }
//            letter++;
//        }
        int length = characterArrayList.size() - 1; // вместо end
        ArrayList<Character> sdvigCharacterArrayList = new ArrayList<>();

        int end = length;
        for (int i = 0; i < characterArrayList.size(); i++) { // смещенный алфавитный список
            if (i < characterArrayList.size() - sdvig) {
                sdvigCharacterArrayList.add(characterArrayList.get(sdvig + i));
            } else {
                sdvigCharacterArrayList.add(characterArrayList.get(length - end));
                end--;
            }
        }

        return sdvigCharacterArrayList;
    }

    static ArrayList<Character> combinationOfLetters(int randomCombo) { // комбинации алфавита (с Ё, с Ъ, без Ь и тд)
        //int randomCombo = (int) ((Math.random() * 14) + 1); // рандомное число для выбора кейса(switch-case)
        ArrayList<Character> characterArrayList = updateArrayList();

        switch (randomCombo) {
            case 1:
                break;
            case 2:
                // всех нету
                characterArrayList.remove(characterArrayList.indexOf('Й'));
                characterArrayList.remove(characterArrayList.indexOf('Ё'));
                characterArrayList.remove(characterArrayList.indexOf('Ь'));
                characterArrayList.remove(characterArrayList.indexOf('Ъ'));
                break;
            case 3:
                // ь, ё, й
                characterArrayList.remove(characterArrayList.indexOf('Ъ'));
                break;
            case 4:
                // ё, й
                characterArrayList.remove(characterArrayList.indexOf('Ь'));
                characterArrayList.remove(characterArrayList.indexOf('Ъ'));
                break;
            case 5:
                // й
                characterArrayList.remove(characterArrayList.indexOf('Ё'));
                characterArrayList.remove(characterArrayList.indexOf('Ь'));
                characterArrayList.remove(characterArrayList.indexOf('Ъ'));
                break;
            case 6:
                // ь, й
                characterArrayList.remove(characterArrayList.indexOf('Ё'));
                characterArrayList.remove(characterArrayList.indexOf('Ъ'));
                break;
            case 7:
                // ь, ё
                characterArrayList.remove(characterArrayList.indexOf('Й'));
                characterArrayList.remove(characterArrayList.indexOf('Ъ'));
                break;
            case 8:
                // ъ, ё, й
                characterArrayList.remove(characterArrayList.indexOf('Ь'));
                break;
            case 9:
                // ъ, й
                characterArrayList.remove(characterArrayList.indexOf('Ё'));
                characterArrayList.remove(characterArrayList.indexOf('Ь'));
                break;
            case 10:
                // ъ
                characterArrayList.remove(characterArrayList.indexOf('Й'));
                characterArrayList.remove(characterArrayList.indexOf('Ё'));
                characterArrayList.remove(characterArrayList.indexOf('Ь'));
                break;
            case 11:
                // ъ, ь, й
                characterArrayList.remove(characterArrayList.indexOf('Ё'));
                break;
            case 12:
                // ъ, ь
                characterArrayList.remove(characterArrayList.indexOf('Й'));
                characterArrayList.remove(characterArrayList.indexOf('Ё'));
                break;
            case 13:
                // ъ, ь, ё
                characterArrayList.remove(characterArrayList.indexOf('Й'));
                break;
            case 14:
                // ъ, ё
                characterArrayList.remove(characterArrayList.indexOf('Й'));
                characterArrayList.remove(characterArrayList.indexOf('Ь'));
                break;
            case 15:
                // ь
                characterArrayList.remove(characterArrayList.indexOf('Й'));
                characterArrayList.remove(characterArrayList.indexOf('Ё'));
                characterArrayList.remove(characterArrayList.indexOf('Ъ'));
                break;
            default:
                break;
        }
        return characterArrayList;
    }

    static ArrayList updateArrayList() {
        ArrayList<Character> characterArrayList = new ArrayList<>(); //буквенный список
        char letter = 'А';
        for (int i = 0; i < 32; i++) { // заполнение списка буквами
            characterArrayList.add(letter);
            if (letter == 'Е') {
                characterArrayList.add('Ё');
            }
            letter++;
        }
        return characterArrayList;
    }


   


}
