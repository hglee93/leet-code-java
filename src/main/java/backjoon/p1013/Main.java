package backjoon.p1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private final int PATTERN_1 = 1;
    private final int PATTERN_2 = 2;
    private final int PATTERN_3 = 3;

    private int current_status = PATTERN_3;

    public boolean solution(String input) {

        int index = 0;

        if(input.charAt(0) == '0') {
            current_status = PATTERN_2;
        } else {
            current_status = PATTERN_1;
        }
        index++;

        for(int i = 1; i < input.length();){

            char c = input.charAt(i);

            if(current_status == PATTERN_3) {
                if(i < input.length() - 1) {
                    if(c == '0' && input.charAt(i + 1) == '0') {
                        current_status = PATTERN_1;
                        index = 2;
                    } else if(c == '0' && input.charAt(i + 1) == '1') {
                        current_status = PATTERN_2;
                        index = 1;
                    }
                    ++i;
                } else {
                    return false;
                }
            }
            else if(current_status == PATTERN_1) {
                if(index == 0) {

                }
                else if(index == 1) {
                    if(c == '0') {
                        ++index;
                        ++i;
                    }
                    else { return false; }
                }
                else if(index == 2) {
                    if(input.charAt(i) == '0') {
                        if(i < input.length() - 1 && input.charAt(i + 1) == '1') {
                            ++index;
                        }
                        else if(input.length() <= i) {
                            return false;
                        }
                        ++i;
                    } else {
                        return false;
                    }
                }
                else if(index == 3) {
                    if(i < input.length() - 1) {
                        if(input.charAt(i - 1) == '0' && input.charAt(i + 1) == '0') {
                            current_status = PATTERN_2;
                            index = 0;
                        } else if(input.charAt(i - 1) == '1' && input.charAt(i + 1) == '0') {
                            current_status = PATTERN_3;
                            index = 0;
                        }
                    }
                    ++i;
                }
            }
            else if(current_status == PATTERN_2) {

            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Main main = new Main();
        System.out.println(main.solution(input));
    }
}
