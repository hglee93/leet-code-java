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

public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while(left <= right) {
            if(s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
    
    public String longestPalindrome(String s) {
        
        int windowSize = s.length();
        
        while(windowSize > 0) {
            for(int i = 0; i <= s.length() - windowSize; i++) {
                String sub = s.substring(i, i + windowSize);
                if(isPalindrome(sub) == true) {
                    return sub;
                }
            }
            --windowSize;
        }
        
        return "";
    }

    public String convert(String s, int numRows) {
        
        int index = 0;
        int change = 1;
        
        StringBuilder[] sbArray = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i++) {
            sbArray[i] = new StringBuilder();
        }
        
        for(int i = 0; i < s.length(); i++){
            
            if(index == numRows - 1) {
                change = -1;
            } else if(index == 0) {
                change = 1;
            }
            
            sbArray[index].append(s.charAt(i));
            index = index + change;
            if(index < 0) { index = 0; }
            
        }
        
        return String.join("", sbArray);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(0);
        ListNode answer = head;

        while(l1 != null && l2 != null) {
            // 값이 같을 때
            if(l1.val == l2.val) {

                answer.next = new ListNode(l1.val);
                l1 = l1.next;
                answer = answer.next;

                answer.next = new ListNode(l2.val);
                l2 = l2.next;
                answer = answer.next;
            }
            // l1.val < l2.val
            else if(l1.val < l2.val) {
                answer.next = new ListNode(l1.val);
                l1 = l1.next;
                answer = answer.next;
            }
            // l2.val < l1.val
            else {
                answer.next = new ListNode(l2.val);
                l2 = l2.next;
                answer = answer.next;
            }
        }

        if(l1 == null) {
            while(l2 != null) {
                answer.next = new ListNode(l2.val);
                l2 = l2.next;
                answer = answer.next;
            }
        }
        else if(l2 == null) {
            while(l1 != null) {
                answer.next = new ListNode(l1.val);
                l1 = l1.next;
                answer = answer.next;
            }
        }

        return head.next;
    }

    public boolean isPossible(int number, int[][] conditions) {

        String numStr = String.valueOf(number);

        for(int i = 0; i < conditions.length; i++) {

            String targetStr = String.valueOf(conditions[i][0]);
            int strikeTargetCount = conditions[i][1];
            int ballTargetCount = conditions[i][2];

            int strikeCount = 0;
            int ballCount = 0;

            if(numStr.charAt(0) == targetStr.charAt(0)) { strikeCount++; }
            else if(numStr.charAt(0) == targetStr.charAt(1)
                    || numStr.charAt(0) == targetStr.charAt(2)) { ballCount++; }

            if(numStr.charAt(1) == targetStr.charAt(1)) { strikeCount++; }
            else if(numStr.charAt(1) == targetStr.charAt(0)
                    || numStr.charAt(1) == targetStr.charAt(2)) { ballCount++; }

            if(numStr.charAt(2) == targetStr.charAt(2)) { strikeCount++; }
            else if(numStr.charAt(2) == targetStr.charAt(1)
                    || numStr.charAt(2) == targetStr.charAt(0)) { ballCount++; }

            if(ballCount != ballTargetCount
                    || strikeCount != strikeTargetCount) {
                return false;
            }
        }

        return true;
    }

    public boolean isTarget(int num) {
        int first = num % 10;
        num = num / 10;
        int second = num % 10;
        num = num / 10;
        int third = num % 10;

        if(first == 0 || second == 0 || third == 0) { return false; }
        if(first == second || first == third || second == third) {
            return false;
        }

        return true;
    }

    public int solution(int[][] baseball) {

        int answer = 0;

        for(int i = 123; i < 1000; i++) {
            if(isTarget(i) == false) { continue; }
            if(isPossible(i, baseball) == true) { answer++; }
        }

        return answer;
    }

    public String addBinary(String a, String b) {
        char carry = '0';
        int aIdx = a.length() - 1;
        int bIdx = b.length() - 1;

        StringBuilder sb = new StringBuilder();

        while(!(aIdx == -1 || bIdx == -1)) {
            // 덧셈
            if(carry == '1' && a.charAt(aIdx) == '1' && b.charAt(bIdx) == '1') {
                sb.append('1');
                carry = '1';
            }
            else if(carry == '1' && (a.charAt(aIdx) == '1' || b.charAt(bIdx) == '1')) {
                sb.append('0');
                carry = '1';
            }
            else if(carry == '1' && a.charAt(aIdx) == '0' && b.charAt(bIdx) == '0') {
                sb.append('1');
                carry = '0';
            }
            else if(carry == '0' && a.charAt(aIdx) == '1' && b.charAt(bIdx) == '1') {
                sb.append('0');
                carry = '1';
            }
            else if(carry == '0' && (a.charAt(aIdx) == '1' || b.charAt(bIdx) == '1')) {
                sb.append('1');
                carry = '0';
            }
            else if(carry == '0' && a.charAt(aIdx) == '0' && b.charAt(bIdx) == '0') {
                sb.append('0');
                carry = '0';
            }
            --aIdx;
            --bIdx;
        }

        if(a.length() < b.length()) {
            for(int i = bIdx; i >= 0; i--) {
                if(carry == '1' && b.charAt(i) == '1') {
                    sb.append('0');
                    carry = '1';
                }
                else if((carry == '1' && b.charAt(i) == '0')
                        || (carry == '0' && b.charAt(i) == '1')) {
                    sb.append('1');
                    carry = '0';
                }
                else if(carry == '0' && b.charAt(i) == '0') {
                    sb.append('0');
                    carry = '0';
                }
            }
        }
        else if(a.length() > b.length()) {
            for(int i = aIdx; i >= 0; i--) {
                if(carry == '1' && a.charAt(i) == '1') {
                    sb.append('0');
                    carry = '1';
                }
                else if((carry == '1' && a.charAt(i) == '0')
                        || (carry == '0' && a.charAt(i) == '1')) {
                    sb.append('1');
                    carry = '0';
                }
                else if(carry == '0' && a.charAt(i) == '0') {
                    sb.append('0');
                    carry = '0';
                }
            }
        }

        if(carry != '0') {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }

    public boolean solution(String[] phone_book) {
        boolean answer = true;
        for(int i = 0; i < phone_book.length; i++) {
            for(int j = 0; j < phone_book.length; j++) {
                if(i == j) { continue; }
                if(phone_book[j].indexOf(phone_book[i]) == 0) {
                    return false;
                }
            }
        }
        return answer;
    }

    class Job{
        public int location;
        public int priority;
        public Job(int location, int priority) {
            this.location = location;
            this.priority = priority;
        }
    }

    public int printer(int[] priorities, int location) {

        int answer = 0;

        List<Job> queue = new LinkedList<Job>();
        for(int i = 0; i < priorities.length; i++) {
            queue.add(new Job(i, priorities[i]));
        }

        while(true) {
            Job job = queue.get(0);
            queue.remove(0);



            int i = 0;
            int qSize = queue.size();
            for(; i < qSize; i++) {
                if(queue.get(i).priority > job.priority) {
                    queue.add(job);
                    break;
                }
            }

            // 우선순위가 높은 Job이 없는 경우
            if(i == qSize) {
                answer++;
            } else {
                continue;
            }

            // 원하는 Job을 찾은 경우
            if(job.location == location) { break; }
        }

        return answer;
    }

    public int[] building(int[] heights) {
        int[] answer = new int[heights.length];
        for(int i = 0; i < heights.length; i++) {
            int j = i;
            for(; j >= 0; j--) {
                if(heights[j] > heights[i]) { break; }
            }
            answer[i] = j + 1;
        }
        return answer;
    }

    public ListNode removeElements(ListNode head, int val) {

        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode curr = head, prev = sentinel;

        while(curr != null) {
            if(curr.val == val) { prev.next = curr.next; }
            else { prev = curr; }
            curr = curr.next;
        }

        return sentinel.next;
    }

    public boolean dfs(String findStr, String curStr, int x, int y,
                       char[][] board, boolean[][] visited) {

        // 종료 조건
        if(findStr.length() == curStr.length()) {
            if(findStr.equals(curStr) == true) { return true; }
            return false;
        }

        // 각 단계마다 같은 문자인지 체크 후 가지치기
        if(curStr.charAt(curStr.length() - 1) != findStr.charAt(curStr.length() - 1)) {
            return false;
        }

        for(int d = 0; d < 4; d++) {
            int nextX = x + dx[d];
            int nextY = y + dy[d];
            if(nextX >= 0 && nextX < board.length
                    && nextY >= 0 && nextY < board[0].length && visited[nextX][nextY] == false) {
                visited[nextX][nextY] = true;
                if(dfs(findStr, curStr + board[nextX][nextY]
                        , nextX, nextY, board, visited) == true) {
                    return true;
                }
                visited[nextX][nextY] = false;
            }
        }

        return false;
    }

    public boolean exist(char[][] board, String word) {

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                // board[i][j]가 word의 첫 문자일 경우만 탐색 시작
                boolean[][] visited = new boolean[board.length][board[0].length];
                visited[i][j] = true;
                if(dfs(word, "" + board[i][j], i, j, board, visited) == true) {
                    return true;
                }
                visited[i][j] = false;
            }
        }

        return false;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        Queue<TreeNode> leverOrderQueue= new LinkedList<TreeNode>();
        leverOrderQueue.add(root);

        boolean leftToRight = true;

        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        if(root == null) { return answer; }

        while(!leverOrderQueue.isEmpty()) {

            int qSize = leverOrderQueue.size();
            List<Integer> list = new ArrayList<Integer>();

            for(int i = 0; i < qSize; i++) {
                TreeNode node = leverOrderQueue.poll();
                list.add(node.val);
                if(node.left != null) { leverOrderQueue.add(node.left); }
                if(node.right != null) { leverOrderQueue.add(node.right); }
            }

            if(leftToRight == true) {
                answer.add(list);
                leftToRight = false;
            } else {
                Collections.reverse(list);
                answer.add(list);
                leftToRight = true;
            }
        }

        return answer;
    }

    public int[] kstnumber(int[] array, int[][] commands) {

        int[] answer = new int[commands.length];

        for(int i = 0; i < commands.length; i++) {
            int[] arr = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
            Arrays.sort(arr);
            answer[i] = arr[commands[i][2] - 1];
        }

        return answer;
    }

    public String biggestNumber(int[] numbers) {

        String answer = "";
        List<Integer> list = new ArrayList<Integer>();

        for(int number : numbers) {
            list.add(number);
        }

        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                String str1 = num1 + "" + num2;
                String str2 = num2 + "" + num1;
                return Integer.compare(Integer.parseInt(str2), Integer.parseInt(str1));
            }
        });

        StringBuilder sb = new StringBuilder();

        for(Integer ele : list) {
            sb.append(ele);
        }

        answer = sb.toString();

        if(answer.charAt(0) == '0') {
            answer = "0";
        }

        return answer;
    }

    public int HIndex(int[] citations) {

        int answer = 0;
        Arrays.sort(citations);

        int h = 0;
        int index = 0;

        for(; h <= citations.length; h++) {
            while(index < citations.length && citations[index] < h) {
                index++;
            }
            if(citations.length - index < h) { break; }
        }

        return h - 1;
    }

    public int[] doublePriorityQueue(String[] operations) {

        int[] answer = new int[2];

        PriorityQueue<Integer> minQueue = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return Integer.compare(num2, num1);
            }
        });

        for(int i = 0; i < operations.length; i++) {

            String[] commands = operations[i].split(" ");

            if(commands[0].equals("I")) {
                minQueue.add(Integer.parseInt(commands[1]));
                maxQueue.add(Integer.parseInt(commands[1]));
            } else if(commands[0].equals("D")) {
                if(maxQueue.isEmpty()) { continue; }
                if(commands[1].equals("1") == true) {
                    Integer max = maxQueue.poll();
                    minQueue.remove(max);
                } else {
                    Integer min = minQueue.poll();
                    maxQueue.remove(min);
                }
            }
        }

        if(maxQueue.size() >= 1)
        {
            answer[0] = maxQueue.peek();
            answer[1] = minQueue.peek();
        } else {
            answer[0] = 0;
            answer[1] = 0;
        }

        return answer;
    }

    public int findMin(int[] nums) {
        /*
        int min = 2147483647;
        for(int i = 0; i < nums.length; i++) {
            if(min > nums[i]) {
                min = nums[i];
            }
        }
        return min;*/

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] - nums[i - 1] < 0) {
                return nums[i];
            }
        }

        return nums[0];
    }

    public int addDigits(int num) {
        if(num < 10) { return num; }
        num = num % 9;
        return num == 0 ? 9 : num;
    }
}
