package kakao.intership.edit;

import java.util.*;

public class Solution1 {

    public static void main(String[] args) {
        int n = 8, k = 2;
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};

        Solution solution = new Solution();
        String result = solution.solution(n, k, cmd);

        System.out.println(result);
    }
}

class Solution {

    private Set<Integer> deleteSet = new HashSet<>();
    private Stack<Element> deleteStack = new Stack<>();

    public String solution(int n, int k, String[] cmd) {

        Element head = new Element(-1, null, null);
        Element tail = head;
        Element cursor = null;

        for(int i = 0; i < n; i++) {
            Element newNode = new Element(i, null, null);
            insertNode(tail, newNode);
            tail = newNode;
            if(i == k) { cursor = newNode; }
        }

        for(String command : cmd) {
            char action = command.charAt(0);
            int move = 0;

            switch(action) {
                case 'U':
                    move = Integer.parseInt(command.substring(2));
                    cursor = moveToUp(move, cursor);
                    break;

                case 'D':
                    move = Integer.parseInt(command.substring(2));
                    cursor = moveToDown(move, cursor);
                    break;

                case 'C':
                    deleteStack.push(cursor);
                    deleteSet.add(cursor.data);
                    cursor = deleteNode(cursor);
                    break;

                case 'Z': // 복구
                    Element restoreNode = deleteStack.pop();
                    insertNode(restoreNode.prev, restoreNode);
                    deleteSet.remove(restoreNode.data);
                    break;
            }
        }

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < n; i++) {
            if(deleteSet.contains(i)) {
                builder.append("X");
                continue;
            }
            builder.append("O");
        }

        return builder.toString();
    }

    class Element {
        public int data;
        public Element prev;
        public Element next;
        public Element(int data, Element prev, Element next) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public void insertNode(Element curNode, Element insertedNode) {
        insertedNode.next = curNode.next;
        insertedNode.prev = curNode;
        if(curNode.next != null) {
            curNode.next.prev = insertedNode;
        }
        curNode.next = insertedNode;
    }

    public Element deleteNode(Element curNode) {
        if(curNode.next != null) {
            curNode.next.prev = curNode.prev;
        }
        curNode.prev.next = curNode.next;
        return curNode.next == null ? curNode.prev : curNode.next;
    }

    public Element moveToUp(int move, Element cursor) {
        for(int i = 0; i < move; i++) {
            if(cursor.prev.data == -1 ){ break; }
            cursor = cursor.prev;
        }
        return cursor;
    }

    public Element moveToDown(int move, Element cursor) {
        for(int i = 0; i < move; i++) {
            if(cursor.next == null ){ break; }
            cursor = cursor.next;
        }
        return cursor;
    }

}
