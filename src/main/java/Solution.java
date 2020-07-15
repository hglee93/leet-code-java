import java.util.*;

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int answer = 0;

        for(int i = 0; i < 32; i++) {

            int temp = n & (1 << i);
            if(i < 16) {
                temp = temp << (31 - (i * 2));
            } else {
                temp = temp >>> ((i * 2) - 31);
            }
            answer = answer | temp;
        }

        return answer;
    }

    public int[] twoSum(int[] nums, int target) {

        /*for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(target == (nums[i] + nums[j])) {
                    int[] answer = new int[2];
                    answer[0] = i;
                    answer[1] = j;
                    return answer;
                }
            }
        }

        return null;*/

        // 해시테이블 이용
        // look-up 속도가 O(n) -> O(1) 으로 줄어든다.
        HashMap<Integer, Integer> hashTable = new HashMap<Integer, Integer>();

        for(int i = 0; i < nums.length; i++) {
            hashTable.put(nums[i], i);
        }

        for(int i = 0; i < nums.length; i++) {
            int findValue = target - nums[i];
            if(hashTable.containsKey(findValue) == true && hashTable.get(findValue) != i) {
                int[] answer = new int[2];
                answer[0] = i;
                answer[1] = hashTable.get(findValue);
                return answer;
            }
        }

        return null;
    }

    class Pair{
        public int x;
        public int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // UP, DOWN, LEFT, RIGHT
    public int[] dx = {-1, 1, 0, 0};
    public int[] dy = {0, 0, -1, 1};

    public int numIslands(char[][] grid) {

        if(grid.length == 0) { return 0; }

        int cnt = 0;
        int xlen = grid.length;
        int ylen = grid[0].length;

        boolean[][] visited = new boolean[xlen][ylen];

        for(int i = 0; i < xlen; i++) {
            for(int j = 0; j < ylen; j++) {
                if(grid[i][j] == '0' || visited[i][j] == true) { continue; }

                // BFS 작업 시작하기 전 cnt 증가
                // grid[i][j] == 1 일때 BFS 수행하면서 marking
                cnt++;
                Queue<Pair> queue = new LinkedList<Pair>();
                queue.add(new Pair(i, j));
                visited[i][j] = true;

                while(!queue.isEmpty()) {

                    Pair p = queue.peek();
                    queue.remove();

                    for(int d = 0; d < 4; d++) {
                        int nextX = p.x + dx[d];
                        int nextY = p.y + dy[d];
                        if(nextX >= 0 && nextX < xlen && nextY >= 0 && nextY < ylen
                                && visited[nextX][nextY] == false && grid[nextX][nextY] == '1') {
                            queue.add(new Pair(nextX, nextY));
                            visited[nextX][nextY] = true;
                        }
                    }
                }
            }
        }

        return cnt;
    }

    public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        /*ListNode cursor = null;
        ListNode prevCursor = null;
        ListNode head = null;

        int carry = 0;

        while(!(l1 == null || l2 == null)) {

            int value = l1.val + l2.val + carry;

            carry = value / 10;

            cursor = new ListNode(value % 10);

            if(prevCursor != null) { prevCursor.next = cursor; }
            if(head == null) { head = cursor; }

            l1 = l1.next;
            l2 = l2.next;

            prevCursor = cursor;
            cursor = cursor.next;

        }

        if(l1 == null) {

            while(l2 != null) {

                int value = l2.val + carry;

                carry = value / 10;
                cursor = new ListNode(value % 10);

                if(prevCursor != null) { prevCursor.next = cursor; }

                if(head == null) { head = cursor; }

                l2 = l2.next;

                prevCursor = cursor;
                cursor = cursor.next;
            }

        } else if(l2 == null) {

            while(l1 != null) {
                int value = l1.val + carry;

                carry = value / 10;
                cursor = new ListNode(value % 10);

                if(prevCursor != null) { prevCursor.next = cursor; }

                if(head == null) { head = cursor; }

                l1 = l1.next;

                prevCursor = cursor;
                cursor = cursor.next;
            }

        }

        if(carry == 1) {
            cursor = new ListNode(1);
            if(prevCursor != null) { prevCursor.next = cursor; }
        }

        return head;*/

        // dummyHead Node를 활용하면 코드가 간결해진다.
        // List의 노드 삽입 시 cursor.next에 노드를 만들어주는게 코드가 간결해지는 듯~
        ListNode dummyHead = new ListNode(0);
        ListNode cursor = dummyHead;

        int carry = 0;

        while(l1 != null || l2 != null) {

            int x = (l1 != null ? l1.val : 0);
            int y = (l2 != null ? l2.val : 0);

            int sum = x + y + carry;
            carry = sum / 10;
            cursor.next = new ListNode(sum % 10);
            cursor = cursor.next;

            if(l1 != null) { l1 = l1.next; }
            if(l2 != null) { l2 = l2.next; }
        }

        if(carry == 1) {
            cursor.next = new ListNode(1);
        }

        return dummyHead.next;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    public boolean checkSameTree(TreeNode p, TreeNode q) {
        if((p == null && q != null) || (p != null && q == null)) { return false; }
        if(p == null && q == null) { return true; }
        if(p.val != q.val) { return false; }
        if(checkSameTree(p.left, q.left) == false) { return false; }
        if(checkSameTree(p.right, q.right) == false) { return false; }
        return true;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return checkSameTree(p, q);
    }

    public String reverseWords(String s) {

        if(s.equals("")) { return ""; }

        Stack st = new Stack();

        String[] words = s.split(" ");
        for(String word : words) {
            st.push(word);
        }

        String answer = "";

        while(!st.isEmpty()) {
            String word = (String) st.pop();
            if(word.equals("")) { continue; }
            else {
                answer += word + " ";
            }
        }

        if(answer.length() >= 1) {
            answer = answer.substring(0, answer.length() - 1);
        }

        return answer;
    }

    public boolean isDigitsLog(String log) {

        String[] words = log.split(" ");

        if(words[1].charAt(0) >= '0' && words[1].charAt(0) <= '9') {
            return true;
        }

        return false;
    }
    public String[] reorderLogFiles(String[] logs) {

        List<String> letterLog = new ArrayList<String>();

        List<String> digitsLog = new ArrayList<String>();

        for(int i = 0; i < logs.length; i++) {
            if(isDigitsLog(logs[i]) == true) { digitsLog.add(logs[i]); }
            else { letterLog.add(logs[i]); }
        }

        Collections.sort(letterLog, new Comparator<String>() {
            public int compare(String s1, String s2) {
                int left1 = 0, left2 = 0;
                while(left1 <= s1.length() - 1 && s1.charAt(left1) != ' ') { left1++; }
                while(left2 <= s2.length() - 1 && s2.charAt(left2) != ' ') { left2++; }

                if(s1.substring(left1).compareTo(s2.substring(left2)) == 0) {
                    return s1.substring(0, left1).compareTo(s2.substring(0, left2));
                }

                return s1.substring(left1).compareTo(s2.substring(left2));
            }
        });

        String[] reorderLog = new String[logs.length];
        int index = 0;

        for(String log : letterLog) {
            reorderLog[index++] = log;
        }

        for(String log : digitsLog) {
            reorderLog[index++] = log;
        }

        return reorderLog;
    }
}
