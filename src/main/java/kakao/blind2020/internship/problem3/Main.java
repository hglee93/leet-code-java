package kakao.blind2020.internship.problem3;


/*
    Problem : 보석 쇼핑
    Description : 카카오 인턴십 2020
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    class Solution {

        private Map<String, Integer> map = new HashMap<>();

        private int getNumberOfType(String[] gems) {
            Set<String> set = new HashSet<>();
            for(String gem : gems) {
                set.add(gem);
            }
            return set.size();
        }

        private void printGems(int start, int end, String[] gems) {
            for(int i = start; i < end; i++) {
                System.out.print(gems[i] + " ");
            }
            System.out.println("");
        }

        public int[] solution(String[] gems) {

            int min_length = Integer.MAX_VALUE;

            int numOfType = getNumberOfType(gems);

            int start = 0, end = 0;
            int[] answer = new int[2];

            while(start <= end) {
                if(map.size() == numOfType) {
                    if(end - start < min_length) {
                        min_length = end - start;
                        answer[0] = start + 1; answer[1] = end;
                    }
                    map.put(gems[start], map.getOrDefault(gems[start], 0) - 1);
                    if(map.getOrDefault(gems[start], 0) == 0){
                        map.remove(gems[start]);
                    }
                    start++;
                } else {
                    if(end < gems.length){
                        map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
                        end++;
                    } else {
                        start++;
                    }
                }
            }

            return answer;
        }
    }

    Solution solution = new Solution();

    public static void main(String[] args) {
        Main main = new Main();
        Solution solution = main.solution;

        //String[] input = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        //String[] input = {"AA", "AB", "AC", "AA", "AC"};
        String[] input = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        int[] answer = solution.solution(input);
        System.out.println(answer[0] + ", " + answer[1]);
    }
}

/*
    이전 답안 :
    class Solution {
        public int[] solution(String[] gems) {
            // 전체 보석 종류의 수
            Set<String> gemSet = new HashSet<String>();
            for(String gem : gems) { gemSet.add(gem); }

            int nKinds = gemSet.size();

            int min = Integer.MAX_VALUE;
            int[] answer = new int[2];

            int start = 0, end = 0;
            Map<String, Integer> map = new HashMap<String, Integer>();
            for(end = 0; end < gems.length; end++) {
                // end가 이동하면서 현재 구간에 수량을 담고 있는 map을 관리
                // end가 put 한 후 map의 size가 전체 보석 종류의 수와 같으면
                map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);

                // 전체 보석 종류의 수가 변하지 않는 범위에서
                // (start가 이동하면서 해당 키의 value를 1씩 감소시키고, value가 1이면 정지)
                // start를 이동시킨다.
                if(map.size() == nKinds) {
                    while(map.get(gems[start]) != 1) {
                        map.put(gems[start], map.get(gems[start]) - 1);
                        start++;
                    }
                    if(min > end - start) {
                        min = end - start;
                        answer[0] = start + 1;
                        answer[1] = end + 1;
                    }
                }
            }
            return answer;
        }
    }
 */
