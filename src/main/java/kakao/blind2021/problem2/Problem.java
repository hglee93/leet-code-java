package kakao.blind2021.problem2;

import javax.swing.*;
import java.util.*;

public class Problem {

    class Solution {

        private Map<String, Integer> map = new HashMap<>();

        private Map<Integer, Integer> courseMap = new HashMap<>();

        private int[] maxCountArray;

        public void getPermutation(String str, String temp, int start, int length) {

            if(courseMap.containsKey(temp.length())) {
                int count = map.getOrDefault(temp, 0) + 1;
                map.put(temp, count);

                int index = courseMap.get(temp.length());
                maxCountArray[index] = Math.max(maxCountArray[index], count);
            }

            for(int i = start; i < str.length(); i++) {
                getPermutation(str, temp + str.charAt(i), i + 1, length);
            }

            return;
        }

        public String[] solution(String[] orders, int[] course) {

            // order들을 오름차순으로 정렬
            for(int i = 0; i < orders.length; i++) {
                char[] array = orders[i].toCharArray();
                Arrays.sort(array);
                orders[i] = new String(array);
            }

            maxCountArray = new int[course.length];

            int max_length = 0;
            for(int i = 0; i < course.length; i++) {
                max_length = Math.max(max_length, course[i]);
                courseMap.put(course[i], i);
            }

            for(int j = 0; j < orders.length; j++) {
                getPermutation(orders[j], "", 0, max_length);
            }

            List<String> answerList = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();

                int index = courseMap.get(key.length());
                int maxCount = maxCountArray[index];
                if(value > 1 && value == maxCount) {
                    answerList.add(key);
                }
            }

            String[] answer = new String[answerList.size()];
            answerList.toArray(answer);
            Arrays.sort(answer);
            return answer;
        }
    }

    public Solution solution = new Solution();

    public static void main(String[] args) {
        Solution solution = new Problem().solution;
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
        solution.solution(orders, course);
    }
}
