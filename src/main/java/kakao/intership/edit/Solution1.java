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
    private List<Integer> spreadsheet = new LinkedList<>();

    class DeleteElement {
        int index;
        Element node;
    }

    public void print(Element head) {

        Element cursor = head;

        while(cursor != null) {
            System.out.print("(" + cursor.data + ") ");
            cursor = cursor.next;
        }

        System.out.println("");

        cursor = head;

        while(cursor != null) {
            System.out.println("(" + cursor.data + ") ");

            if(cursor.prev != null){
                System.out.println("cursor.prev : " + "(" + cursor.prev.data + ") ");
            }
            if(cursor.next != null){
                System.out.println("cursor.next : " + "(" + cursor.next.data + ") ");
            }
            cursor = cursor.next;
        }

        System.out.println("");
    }

    public String solution(int n, int k, String[] cmd) {

        // O(n)
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

            String[] temp = command.split(" ");
            String action = temp[0];
            int move = temp.length >= 2 ? Integer.parseInt(temp[1]) : 0;

            switch(action) {
                case "U":
                    cursor = moveToUp(move, cursor);
                    break;
                case "D":
                    cursor = moveToDown(move, cursor);
                    break;
                case "C":
                    deleteStack.push(cursor);
                    // O(1)
                    deleteSet.add(cursor.data);
                    // O(1)
                    cursor = deleteNode(cursor);
                    break;

                case "Z": // 복구
                    //O(1)
                    Element restoreNode = deleteStack.pop();
                    insertNode(restoreNode.prev, restoreNode);
                    //O(1)
                    deleteSet.remove(restoreNode.data);
                    break;
            }
            //System.out.println("cursor : " + "(" + cursor.data + ")");
            //print(head.next);
        }

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < n; i++) {
            if(deleteSet.contains(i)) { builder.append("X"); }
            else {
                builder.append("O");
            }
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
