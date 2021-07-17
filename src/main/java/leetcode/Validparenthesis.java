package leetcode;

import java.util.*;

public class Validparenthesis {
    public boolean isValid(String s) {
        // Open bracket은 스택에 삽입하고
        // Closed bracket일 경우 스택의 top을 확인한다.
        // 왜 스택을 사용하는가?
        /*
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {

            // 이 코드를 조금 더 다듬어 보자
            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
            }
            else if(c == ')' || c == '}' || c == ']') {

                if(stack.size() == 0) { return false; }

                if(c == ')' && stack.peek() != '(') {
                    return false;
                }

                if(c == '}' && stack.peek() != '{') {
                    return false;
                }

                if(c == ']' && stack.peek() != '[') {
                    return false;
                }

                stack.pop();
            }
        }

        if(stack.size() != 0) {
            return false;
        }

        return true;*/
        return true;
    }
}
