public class Main {

    public static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0;
        int i = 1;
        lps[0] = 0;

        while(i < m) {
            if(pattern.charAt(len) == pattern.charAt(i)) {
                len++;
                lps[i] = len;
                i++;
            }
            else {
                if(len != 0) {
                    len = lps[len - 1];
                }
                else {
                    lps[i] = len;
                    i++;
                }
            }
        }

        return lps;
    }

    public static void main(String[] args) {
        String text = "ABC ABCDAB ABCDABCDABDE";
        String pattern = "ABCDABD";
        int[] lpsArray = computeLPSArray(pattern);

        for(int i = 0; i < lpsArray.length; i++) {
            System.out.print(lpsArray[i] + " ");
        }
    }
}
