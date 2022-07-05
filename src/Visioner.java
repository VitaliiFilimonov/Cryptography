import java.util.*;

public class Visioner {
    static void Shifr(String phrase, String key) {

        ArrayList<ArrayList<Character>> table = new ArrayList<>();
        for (int i = 0; i < 33; i++) {
            table.add(new ArrayList<>()); // инициализация ArrayList другим ArrayList-ом
        }

        char letter = 'А';

        for (int i = 0; i < 33; i++) {
            if (i == 0) {
                table.get(i).add(' ');
            }
            for (int j = 0; j < 33; j++) { // заполнение списка буквами
                table.get(i).add(letter);
                letter++;
            }
            //table.get(i).remove(0);
            table.get(i).add(table.get(i).get(i));
            letter = 'А';
            letter += i;
            table.get(i).remove(table.get(i).size() - 1);
        }
        table.get(0).remove(table.get(0).size() - 1);

        for (int i = 0; i < 33; i++) {
            for (int j = 0; j < 33; j++) {
                table.get(i).set(j, Character.toUpperCase(table.get(i).get(j)));
            }
        }

        for (int i = 0; i < 33; i++){
            for (char c: table.get(i)){
                System.out.print(c + " ");
            }
            System.out.println();
        }

        System.out.println();

        String allLetters = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        phrase = phrase.toUpperCase();
        key = key.toUpperCase();
        int length = allLetters.length();

        int[] newWords = new int[phrase.length()];
        int[] keyLettersArray = new int[key.length()]; //массив индексов букв, совпадающих с allLetters

        for (int i = 0; i < key.length(); i++) {
            keyLettersArray[i] = allLetters.indexOf(key.charAt(i));
        }

        for (int i = 0; i < phrase.length(); i++) {
            newWords[i] = (allLetters.indexOf(phrase.charAt(i)) + keyLettersArray[i % key.length()]) % length; // keyLettersArray[i % key.length()] - как только дошел до
            // конца ключа, то начинается отсчет по-новой!!!запомнить
        }
        String Str = "";
        for (int i : newWords) {
            Str += allLetters.charAt(i);
        }
        System.out.println(Str);
    }
}
