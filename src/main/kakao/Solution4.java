class Solution4 {

    private int minLength = 51;

    private String target;

    public boolean isSimilar(String str1, String str2) {
        int len = str1.length();
        int count = 0;
        for(int i = 0; i < len; i++) {
            if(str1.charAt(i) != str2.charAt(i)) { count++; }
        }
        return count == 1 ? true : false;
    }

    public void search(int depth, int index, boolean[] visited, String[] words) {

        if(words[index].equals(target) == true) {
            minLength = (minLength > depth) ? depth : minLength;
            return;
        }

        for(int i = 0; i < words.length; i++) {
            if(isSimilar(words[index], words[i]) == true && visited[i] == false) {
                visited[i] = true;
                search(depth + 1, i, visited, words);
                visited[i] = false;
            }
        }
    }

    public int solution(String begin, String target, String[] words) {

        this.target = target;

        boolean[] visited = new boolean[words.length];

        for(int i = 0; i < words.length; i++) {
            if(isSimilar(begin, words[i]) == true) {
                visited[i] = true;
                search(1, i, visited, words);
                visited[i] = false;
            }
        }

        return minLength == 51 ? 0 : minLength;
    }
}