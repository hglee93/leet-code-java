package kakao.blind2021.problem1;

public class Problem {

    class Solution {

        public String convertLowerCase(String str) {
            return str.toLowerCase();
        }

        public String removeSpecialChar(String str) {
            StringBuilder sb = new StringBuilder();
            for(char c : str.toCharArray()) {
                if(Character.isLowerCase(c) || Character.isDigit(c) || c == '-' || c == '_' || c == '.') {
                    sb.append(c);
                }
            }
            return sb.toString();
        }

        public String removeDuplicate(String str) {

            StringBuilder sb = new StringBuilder();
            sb.append(str.charAt(0));
            for(int i = 1; i < str.length(); i++){
                if(str.charAt(i) == '.' && str.charAt(i - 1) == '.') {
                    continue;
                } else {
                    sb.append(str.charAt(i));
                }
            }
            return sb.toString();
        }

        public String removeFirstLast(String str) {

            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < str.length(); i++) {
                if((i == 0 && str.charAt(0) == '.') || (i == str.length() - 1 && str.charAt(str.length() - 1) == '.')) {
                    continue;
                }
                else {
                    sb.append(str.charAt(i));
                }
            }

            return sb.toString();
        }

        public String addDefaultChar(String str) {
            if(str.length() == 0) {
                return "a";
            }
            return str;
        }

        public String convertDefaultSizeString(String str) {
            if(str.length() >= 16) {
                int splitLength = 15;
                if(str.charAt(14) == '.') {
                    splitLength = 14;
                }
                str = str.substring(0, splitLength);
            }

            return str;
        }

        public String repeatString(String str) {
            char c = str.charAt(str.length() - 1);
            while(str.length() <= 2) {
                str += c;
            }
            return str;
        }

        public String solution(String new_id) {

            String answer = new_id;

            // 1단계
            answer = convertLowerCase(answer);

            // 2단계
            answer = removeSpecialChar(answer);

            // 3단계
            answer = removeDuplicate(answer);

            // 4단계
            answer = removeFirstLast(answer);

            // 5단계
            answer = addDefaultChar(answer);

            // 6단계
            answer = convertDefaultSizeString(answer);

            // 7단계
            answer = repeatString(answer);

            return answer;
        }
    }

    public Solution solution = new Solution();

    public static void main(String[] args) {
        Solution solution = new Problem().solution;
        String answer = solution.solution("...!@BaT#*..y.abcdefghijklm");
        System.out.println("answer = " + answer);
    }
}
