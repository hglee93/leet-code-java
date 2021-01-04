package leetcode;

/***
 * T.C = O(N)
 * - 인접한 Element만을 검사해도 되는 이유
 * - Sort Order를 검사하는 함
 */
public class AlienDictionary {
    private int[] orderMap = new int[26];

    public boolean compare(String str1, String str2) {
        // 길이가 짧은 것만큼만 순회
        int len = str1.length() <= str2.length() ? str1.length() : str2.length();

        // 순회 후 길이를 체크해서 리턴
        for(int i = 0; i < len; i++) {
            if(orderMap[str1.charAt(i) - 'a'] < orderMap[str2.charAt(i) - 'a']) {
                return true;
            }
            else if(orderMap[str1.charAt(i) - 'a'] > orderMap[str2.charAt(i) - 'a']) {
                return false;
            }
        }

        if(str1.length() <= str2.length()) {
            return true;
        }

        return false;
    }

    public boolean isAlienSorted(String[] words, String order) {

        // order map 초기화
        for(int i = 0; i < order.length(); i++) {
            orderMap[order.charAt(i) - 'a'] = i;
        }

        if(words.length == 1) { return true; }

        // T.C = O(N)
        for(int i = 0; i < words.length - 1; i++) {
            if(compare(words[i], words[i + 1]) == false) { return false; }
        }

        return true;
    }
}
