package backjoon.problem_1283;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        List<List<String>> wordList = new ArrayList<>();
        Map<Character, Position> map = new HashMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int word_count = Integer.parseInt(br.readLine());

        for(int i = 0; i < word_count; i++) {
            String[] words = br.readLine().split(" ");
            wordList.add(new ArrayList<String>());
            for(String word : words) {
                wordList.get(i).add(word);
            }
        }

        // 단축키 지정 시작
        for(int i = 0; i < wordList.size(); i++) {
            List<String> words = wordList.get(i);
            boolean condition1 = false;
            // 조건 1.
            for(int j = 0; j < words.size(); j++) {
                char key = Character.toLowerCase(words.get(j).charAt(0));
                if(!map.containsKey(key)) {
                    condition1 = true;
                    map.put(key, new Position(i, j, 0));
                    break;
                }
            }

            // 조건 2.
            boolean condition2 = false;
            if(!condition1) {
                for(int j = 0; j < words.size(); j++) {
                    String word = words.get(j);
                    for(int k = 0; k < word.length(); k++) {
                        char key = Character.toLowerCase(word.charAt(k));
                        if(!map.containsKey(key)) {
                            map.put(key, new Position(i, j, k));
                            condition2 = true;
                            break;
                        }
                    }
                    if(condition2) { break; }
                }
            }
        }

        for(Map.Entry<Character, Position> entry : map.entrySet()) {
            Position position = entry.getValue();
            String word = wordList.get(position.sentence_number).get(position.word_number);
            String newWord = word.substring(0, position.character_number) + "[" + word.charAt(position.character_number) + "]"+ word.substring(position.character_number + 1);
            wordList.get(position.sentence_number).set(position.word_number, newWord);
        }

        for(int i = 0; i < wordList.size(); i++) {
            StringJoiner sj = new StringJoiner(" ");
            for(String word : wordList.get(i)) {
                sj.add(word);
            }
            System.out.println(sj.toString());
        }
    }
}

class Position {
    int sentence_number;
    int word_number;
    int character_number;

    public Position(int sentence_number, int word_number, int character_number) {
        this.sentence_number = sentence_number;
        this.word_number = word_number;
        this.character_number = character_number;
    }
}
