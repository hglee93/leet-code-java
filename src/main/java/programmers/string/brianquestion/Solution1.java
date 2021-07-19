package programmers.string.brianquestion;

public class Solution1 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] examples = {
                "HaEaLaLaObWORLDbSpIpGpOpNpGJqOqAdGcWcFcDdeGfWfLeoBBoAAAAxAxAxAA",
                "aBcAadDeEdvAvlElmEEEEm", "AcAcABaBaB", "aGbWbFbDakGnWnLk", "XcXbXcX",
                "aCaCa", "AxAxAxAoBoBoB", "xAaAbAaAx", "AsCsWsQsQsEEEEEEEEeEeEe", "ABCaDaEFGbH", "aAaBBBcAeAeAc",
                "ABCbDaEaFbHI", "AacacaA", "AaBcBcBcBcB", "aAAA", "AAAa", "aAbBBbAa", "aAbBBbAa", "aAAbBbAAa", "aAcAbAbAcAcAcAa",
                "acAcAcAa", "aAcAcAca", "AdAeAeAdA", "dAAeAd", "dAeAAd"
        };

        for(String example : examples) {
            System.out.print(solution.solution(example));
            System.out.println(" : " + example);
        }
    }
}

class Solution {

    public boolean isCapital(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public String solution(String sentence) {

        int[] count = new int[26];

        for(int i = 0; i < sentence.length(); ++i) {
            if(sentence.charAt(i) == ' ') { return "invalid"; }
            if(!isCapital(sentence.charAt(i))) { ++count[sentence.charAt(i) - 'a']; }
        }

        StringBuilder sb = new StringBuilder();

        int cursor = 0;

        for(int i = 0; i < sentence.length();) {
            int index = sentence.charAt(i) - 'a';
            StringBuilder chunk = new StringBuilder();

            if(isCapital(sentence.charAt(i)) || count[index] == 0) { ++i; continue; }
            if(count[index] == 1) {
                // Rule 1
                if(i == 0 || cursor > i - 1 || !isCapital(sentence.charAt(i - 1))) { return "invalid"; }
                if(i == sentence.length() - 1 || !isCapital(sentence.charAt(i + 1))) { return "invalid"; }

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

            }
            else if(count[index] == 2) {

                char specialChar = (char)('a' + index);
                int firstIndex = i;
                int lastIndex = sentence.lastIndexOf(specialChar);

                String word = sentence.substring(firstIndex + 1, lastIndex);

                // 대문자 개수 확인
                int capitalCount = 0;
                int smallCount = 0;

                for(int j = 0; j < word.length(); j++) {
                    if(isCapital(word.charAt(j))) { ++capitalCount; }
                    else { ++smallCount; }
                }

                // Rule2
                if(capitalCount >= 2 && smallCount == 0) {

                    String buffer = sentence.substring(cursor, i);
                    for(int j = 0; j < buffer.length(); j++) {
                        chunk.append(buffer.charAt(j));
                        chunk.append(" ");
                    }

                    chunk.append(sentence.substring(firstIndex + 1, lastIndex));
                    chunk.append(" ");
                    i = lastIndex + 1;
                    cursor = i;
                }
                // Rule2 & Rule1
                else if(capitalCount >= 2 && smallCount != 0) {
                    // word : ex) BaB, BaBaB, BaBaBaB
                    // word가 Rule1에 속하는지 확인
                    char temp = 'a';
                    for(char c : word.toCharArray()) {
                        if(!isCapital(c)) { temp = c; break; }
                    }
                    int li = word.indexOf(temp);
                    int ri = word.lastIndexOf(temp);

                    if((li != 0 && isCapital(word.charAt(li - 1)))
                            && (ri != word.length() - 1 && isCapital(word.charAt(ri + 1)))
                            && isRuleOne(word, temp)
                    ) {
                        String buffer = sentence.substring(cursor, firstIndex);
                        for(int j = 0; j < buffer.length(); j++) {
                            chunk.append(buffer.charAt(j));
                            chunk.append(" ");
                        }

                        for(int j = 0; j < word.length(); j += 2) { chunk.append(word.charAt(j)); }
                        chunk.append(" ");
                        i = lastIndex + 1;
                        cursor = i;
                    } else {
                        return "invalid";
                    }

                    /**/
                }
                else if(capitalCount == 1){
                    String buffer = sentence.substring(cursor, firstIndex);
                    for(int j = 0; j < buffer.length(); j++) {
                        chunk.append(buffer.charAt(j));
                        chunk.append(" ");
                    }

                    chunk.append(sentence.charAt(firstIndex + 1));
                    chunk.append(" ");
                    i = lastIndex + 1;
                    cursor = i;
                }
                else {
                    return "invalid";
                }
            }
            else {
                char specialChar = (char)('a' + index);
                int firstIndex = i;
                int lastIndex = sentence.lastIndexOf(specialChar);

                // 양 쪽의 문자가 대문자이고
                // pattern 검사 : 소문자가 사이사이에 있는 패턴이면서, 소문자가 모두 같아야됨
                if((firstIndex != 0 && cursor <= firstIndex - 1 && isCapital(sentence.charAt(firstIndex - 1)))
                    && (lastIndex != sentence.length() - 1 && isCapital(sentence.charAt(lastIndex + 1)))
                    && isRuleThree(sentence.substring(firstIndex, lastIndex + 1), specialChar)
                ) {
                    String buffer = sentence.substring(cursor, firstIndex - 1);
                    for(int j = 0; j < buffer.length(); j++) {
                        chunk.append(buffer.charAt(j));
                        chunk.append(" ");
                    }

                    for(int j = firstIndex - 1; j <= lastIndex + 1; j += 2) { chunk.append(sentence.charAt(j)); }
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
        return sb.toString().trim();
    }

    public boolean isRuleOne(String word, char c) {
        for(int i = 1; i < word.length(); i += 2) {
            if(word.charAt(i) != c) {return false;}
        }
        return true;
    }

    public boolean isRuleThree(String sentence, char c) {
        for(int i = 0; i < sentence.length(); i += 2) {
            if(sentence.charAt(i) != c) { return false; }
        }
        return true;
    }
}
