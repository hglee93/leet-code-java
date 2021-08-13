package kakao.blind2021.problem3;

import java.util.*;

public class Main {

    class Solution {

        Map<String, List<Integer>> map = new HashMap<>();

        public List<String> getKeyList(String[] items) {
            String[][] array = new String[4][2];
            array[0][0] = "-";
            array[1][0] = "-";
            array[2][0] = "-";
            array[3][0] = "-";

            for(int i = 0; i < 4; i++) {
                array[i][1] = items[i];
            }

            List<String> keyList = new ArrayList<>();

            for(int i1 = 0; i1 < 2; i1++) {
                for(int i2 = 0; i2 < 2; i2++) {
                    for(int i3 = 0; i3 < 2; i3++) {
                        for(int i4 = 0; i4 < 2; i4++) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(array[0][i1] + ",");
                            sb.append(array[1][i2] + ",");
                            sb.append(array[2][i3] + ",");
                            sb.append(array[3][i4]);
                            keyList.add(sb.toString());
                        }
                    }
                }
            }

            return keyList;
        }

        public Map<String, List<Integer>> getAllKeyMap() {
            String[] languages = {"cpp", "java", "python", "-"};
            String[] job = {"backend", "frontend", "-"};
            String[] career = {"junior", "senior", "-"};
            String[] soulfood = {"chicken", "pizza", "-"};

            Map<String, List<Integer>> map = new HashMap<>();

            for(String l : languages) {
                for(String j : job) {
                    for(String c : career) {
                        for(String f : soulfood) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(l + ",");
                            sb.append(j + ",");
                            sb.append(c + ",");
                            sb.append(f);
                            map.put(sb.toString(), new ArrayList<>());
                        }
                    }
                }
            }

            return map;
        }

        public Solution() {
            map = getAllKeyMap();
        }

        public String getKeyFromQuery(String[] unit) {
            StringBuilder sb = new StringBuilder();
            sb.append(unit[0] + ",");
            sb.append(unit[2] + ",");
            sb.append(unit[4] + ",");
            sb.append(unit[6]);
            return sb.toString();
        }

        public int binarySearch(List<Integer> list, int find) {

            int l = 0, r = list.size() - 1;

            while(l <= r) {
                int mid = l + (r - l) / 2;
                if(find <= list.get(mid)) {
                    r = mid - 1;
                } else if(list.get(mid) < find){
                    l = mid + 1;
                }
            }
            return r + 1;
        }

        public int[] solution(String[] info, String[] query) {

            for(String data : info) {
                String[] arr = data.split(" ");
                List<String> keyList = getKeyList(arr);
                for(String key : keyList){
                    map.get(key).add(Integer.parseInt(arr[4]));
                }
            }

            for(Map.Entry<String, List<Integer>> entry : map.entrySet()){
                Collections.sort(entry.getValue());
            }

            int[] answer = new int[query.length];

            for(int i = 0; i < query.length; i++) {
                String[] unit = query[i].split(" ");
                String key = getKeyFromQuery(unit);
                int find = Integer.parseInt(unit[7]);

                List<Integer> valueList = map.get(key);
                int index = binarySearch(valueList, find);
                answer[i] = valueList.size() - index;
            }

            return answer;
        }
    }

    Solution solution = new Solution();

    public static void main(String[] args) {
        Main main = new Main();
        Solution solution = main.solution;
        //String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        //String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        String[] info = {
                "java backend junior pizza 210",
                "java backend junior pizza 150",
                "java backend junior pizza 200",
                "java backend junior pizza 200",
                "java backend junior pizza 200"
                //"cpp backend senior pizza 280"
        };
        String[] query = {"- and - and junior and - 200"};
        solution.solution(info, query);
    }

}
