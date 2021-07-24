package backjoon.problem_2002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());

        List<String> carList = new ArrayList<>();
        Map<String, Integer> order = new HashMap<>();
        for(int i = 0; i < count; i++) {
            String carName = br.readLine();
            order.put(carName, i);
            carList.add(carName);
        }

        List<String> outputList = new ArrayList<>();
        for(int i = 0; i < count; ++i) {
            outputList.add(br.readLine());
        }

        Set<String> answer = new HashSet<>();
        int[][] d = new int[count][count];
        for(int i = 0; i < outputList.size(); ++i) {
            for(int j = 0; j < outputList.size(); ++j) {
                int order1 = order.get(outputList.get(i));
                int order2 = order.get(outputList.get(j));
                d[order1][order2] = j - i;
                if((order2 - order1 < 0) && (j - i > 0)) {
                    answer.add(carList.get(order1));
                }
            }
        }

        System.out.println(answer.size());
        /*for(String a : answer) {
            System.out.println("a = " + a);
        }

        for(int[] arr1 : d) {
            for(int e : arr1) {
                System.out.print(e + " ");
            }
            System.out.println("");
        }*/
    }
}
