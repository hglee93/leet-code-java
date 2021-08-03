package codinginterview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Main12 {

    public String sort(String str) {
        char[] content = str.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    // T.C : O(nlogn)
    public boolean permutation(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
        return sort(s).equals(sort(t));
    }

    public boolean permutation2(String s, String t) {

        if(s.length() != t.length()) {
            return false;
        }

        int[] letters = new int[128];

        char[] s_array = s.toCharArray();
        for(char c : s_array) {
            letters[c]++;
        }

        for(int i = 0; i < t.length(); i++) {
            int c = (int) t.charAt(i);
            letters[c]--;
            if(letters[c] < 0) {
                return false;
            }
        }

        return true;
    }

    @Test
    public static void main(String[] args) {
        Main12 main12 = new Main12();
        Assertions.assertEquals(true, main12.permutation("god", "dog"));
        Assertions.assertEquals(false, main12.permutation("god", "dogg"));
        Assertions.assertEquals(true, main12.permutation("", ""));

        Assertions.assertEquals(true, main12.permutation2("god", "dog"));
        Assertions.assertEquals(false, main12.permutation2("god", "dogg"));
        Assertions.assertEquals(true, main12.permutation2("", ""));
    }
}
