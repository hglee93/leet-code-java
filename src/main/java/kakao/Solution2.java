package kakao;

import java.util.*;

class Solution2 {
    public int[] solution(String msg) {

        Map<String, Integer> dict = new HashMap<String, Integer>();
        String[] initialKeys = {"A", "B", "C", "D", "E", "F", "G", "H"
                , "I", "J", "K", "L", "M", "N", "O", "P"
                , "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        // 1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
        for(int i = 0; i < 26; i++) {
            dict.put(initialKeys[i], i + 1);
        }

        List<Integer> indexList = new ArrayList<Integer>();

        // 2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
        for(int i = 0; i < msg.length(); i++) {
            int lastIndex = i;
            int length = 0;

            String w = msg.substring(i, i + 1);
            String maxWord = w;

            for(int j = i; j < msg.length(); j++) {
                w = msg.substring(i, j + 1);
                if(dict.containsKey(w) == false) { break; }
                else {
                    maxWord = w;
                    lastIndex = dict.get(w);
                }
            }
            // 3. w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
            indexList.add(lastIndex);

            // 4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
            dict.put(w, dict.size() + 1);
            i += (maxWord.length() - 1);
            // 5. 단계 2로 돌아간다.
        }

        int[] answer = new int[indexList.size()];
        for(int i = 0; i < indexList.size(); i++) {
            answer[i] = indexList.get(i);
        }
        return answer;
    }
}