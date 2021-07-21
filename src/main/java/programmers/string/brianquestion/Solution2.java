package programmers.string.brianquestion;

public class Solution2 {
    public boolean isCapital(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public String solution(String sentence) {

        int[] count = new int[26];
        boolean check = false;

        for(int i = 0; i < sentence.length(); ++i) {
            if(sentence.charAt(i) == ' ') { return "invalid"; }
            if(check == false && !isCapital(sentence.charAt(i))) { check = true; }
            if(!isCapital(sentence.charAt(i))) { ++count[sentence.charAt(i) - 'a']; }
        }

        if(!check) { return sentence; }

        StringBuilder sb = new StringBuilder();

        int cursor = 0;

        for(int i = 0; i < sentence.length();) {

            int index = sentence.charAt(i) - 'a';
            StringBuilder chunk = new StringBuilder();

            if(isCapital(sentence.charAt(i))) { ++i; continue; }
            if(count[index] == 1) {
                if(i > cursor && i < sentence.length() - 1 && isCapital(sentence.charAt(i - 1)) && isCapital(sentence.charAt(i + 1))) {
                    String buffer = sentence.substring(cursor, i - 1);
                    for(int j = 0; j < buffer.length(); j++) {
                        chunk.append(buffer.charAt(j));
                        chunk.append(" ");
                    }

                    chunk.append(sentence.charAt(i - 1));
                    chunk.append(sentence.charAt(i + 1));
                    chunk.append(" ");
                    i = i + 2;
                    cursor = i;
                } else {
                    return "invalid";
                }
            }
            else if(count[index] == 2) {

                char specialChar = (char)('a' + index);
                int firstIndex = i;
                int lastIndex = sentence.lastIndexOf(specialChar);

                String word = sentence.substring(firstIndex + 1, lastIndex);

                if(word.length() == 0) { return "invalid"; }

                // 소문자가 있는지 확인
                char specialChar2 = 0;
                for(char c : word.toCharArray()) {
                    if(c >= 'a' && c <= 'z') {
                        specialChar2 = c;
                        break;
                    }
                }

                String buffer = sentence.substring(cursor, i);
                // 소문자가 있으면 Rule 1 적용
                if(specialChar2 != 0) {
                    if(isRuleOne(word, specialChar2)) {
                        for(int j = 0; j < buffer.length(); j++) {
                            chunk.append(buffer.charAt(j));
                            chunk.append(" ");
                        }
                        for(int j = 0; j < word.length(); ++j) {
                            if(j % 2 == 0) { chunk.append(word.charAt(j)); }
                        }
                        chunk.append(" ");

                    } else {
                        return "invalid";
                    }
                }
                // Rule2
                else {
                    for(int j = 0; j < buffer.length(); j++) {
                        chunk.append(buffer.charAt(j));
                        chunk.append(" ");
                    }
                    chunk.append(word + " ");
                }

                i = lastIndex + 1;
                cursor = i;
            }
            else if(count[index] >= 3){
                char specialChar = (char)('a' + index);
                int firstIndex = i;
                int lastIndex = sentence.lastIndexOf(specialChar);

                if(firstIndex > cursor && lastIndex < sentence.length() - 1
                        && isRuleOne(sentence.substring(firstIndex - 1, lastIndex + 2), specialChar)) {
                    String buffer = sentence.substring(cursor, i - 1);
                    for(int j = 0; j < buffer.length(); j++) {
                        chunk.append(buffer.charAt(j));
                        chunk.append(" ");
                    }
                    String word = sentence.substring(firstIndex - 1, lastIndex + 2);
                    for(int j = 0; j < word.length(); ++j) {
                        if(j % 2 == 0) { chunk.append(word.charAt(j)); }
                    }
                    chunk.append(" ");
                    i = lastIndex + 2;
                    cursor = i;
                } else {
                    return "invalid";
                }
            }
            sb.append(chunk);
        }

        String buffer = sentence.substring(cursor);
        for(int j = 0; j < buffer.length(); j++) {
            sb.append(buffer.charAt(j));
            sb.append(" ");
        }
        String result = sb.toString().trim();
        for(char c : result.toCharArray()) {
            if(c != ' ' && !isCapital(c)) { return "invalid"; }
        }
        return result;
    }

    boolean isRuleOne(String word, char specialChar) {
        // 모두 같은 c
        // 번갈아서 나오는 패턴이어야 함.
        // 첫문자는 대문자로 시작
        int capital_cnt = 0;
        int small_cnt = 0;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(i % 2 == 0) {
                if(!isCapital(ch)) { return false; }
                ++capital_cnt;
            }
            if(i % 2 == 1) {
                if(specialChar != ch) { return false; }
                ++small_cnt;
            }
        }

        return (capital_cnt - 1) == small_cnt;
    }

}
