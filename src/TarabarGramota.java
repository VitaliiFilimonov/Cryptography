import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TarabarGramota {
    static String Shifr(String s) {
        char[] chars1 = {'Б', 'В', 'Г', 'Д', 'Ж', 'З', 'К', 'Л', 'М', 'Н'}; // 1 переходит во 2
        char[] chars2 = {'Щ', 'Ш', 'Ч', 'Ц', 'Х', 'Ф', 'Т', 'С', 'Р', 'П'}; // 2 переходит в 1

        String words = s.replace(" ", "");
        words = words.toUpperCase();
        char[] charsWords = words.toCharArray();

        for (int i = 0; i < charsWords.length; i++) {
            for (int j = 0; j < chars1.length; j++) {
                if (charsWords[i] == chars1[j]) {
                    charsWords[i] = chars2[j];
                    break;
                }
                if (charsWords[i] == chars2[j]) {
                    charsWords[i] = chars1[j];
                    break;
                }
            }
        }
        String Str = "";
        for (char c : charsWords) {
            Str += c;
        }
        return Str;
    }

    static void encrypt(String s) {
        s = s.toUpperCase();
        ArrayList<Character> characterArrayList = new ArrayList<>();
        char[] chars1 = {'Б', 'В', 'Г', 'Д', 'Ж', 'З', 'К', 'Л', 'М', 'Н'};
        char[] chars2 = {'Щ', 'Ш', 'Ч', 'Ц', 'Х', 'Ф', 'Т', 'С', 'Р', 'П'};

        for (char c : chars1) {
            characterArrayList.add(c);
        }
        ArrayList<Character> characterArrayList2 = new ArrayList<>();
        for (char c : chars2) {
            characterArrayList2.add(c);
        }

        char[] charsWords;
        String Str = "";

        for (int k = 0; k < characterArrayList2.size(); k++) {
            charsWords = s.toCharArray();
            for (int i = 0; i < charsWords.length; i++) {
                for (int j = 0; j < characterArrayList2.size(); j++) {
                    if (charsWords[i] == characterArrayList2.get(j)) {
                        charsWords[i] = characterArrayList.get(j);
                        break;
                    }
                    if (charsWords[i] == characterArrayList.get(j)) {
                        charsWords[i] = characterArrayList2.get(j);
                        break;
                    }
                }
            }
            for (char c : charsWords) {
                Str += c;
            }
            if (inDictionary(Str)) {
                //break;
                System.out.println(Str);
            }
            Str = "";
            sdvig(characterArrayList2); // сдвиг списка
        }
        //System.out.println(Str);
    }

    static ArrayList sdvig(ArrayList<Character> characterArrayList) {
        characterArrayList.add(0, characterArrayList.get(characterArrayList.size() - 1));
        characterArrayList.remove(characterArrayList.size() - 1);
        return characterArrayList;
    }

    static boolean inDictionary(String Str) { // метод проверки в словаре
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
}
