package kakao.blind2021.problem4;

import java.util.*;

public class Problem {
    class Solution {

        int[][] totalFares;

        List<List<Integer>> graph = new ArrayList<>();

        public void dfs(int start, int end, List<Integer> path, boolean[] visits, List<List<Integer>> allpath) {

            if(start == end) {
                List<Integer> path_copy = new ArrayList<>();
                for(int ele : path) { path_copy.add(ele); }
                allpath.add(path_copy);
                return;
            }

            List<Integer> out = graph.get(start);

            for(int i = 0; i < out.size(); i++) {
                if(visits[out.get(i)] == false) {
                    visits[out.get(i)] = true;
                    path.add(out.get(i));

                    dfs(out.get(i), end, path, visits, allpath);

                    visits[out.get(i)] = false;
                    path.remove(out.get(i));
                }
            }

        }

        public int solution(int n, int s, int a, int b, int[][] fares) {

            for(int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            totalFares = new int[n + 1][n + 1];
            for(int i = 0; i < fares.length; i++) {
                int start = fares[i][0];
                int end = fares[i][1];
                int fare = fares[i][2];

                totalFares[start][end] = fare;
                totalFares[end][start] = fare;

                graph.get(start).add(end);
                graph.get(end).add(start);
            }

            List<List<Integer>> allpath_a = new ArrayList<>();
            List<List<Integer>> allpath_b = new ArrayList<>();

            boolean[] visit_a = new boolean[n + 1];
            visit_a[s] = true;
            List<Integer> path = new ArrayList<>();
            path.add(s);
            dfs(s, a, path, visit_a, allpath_a);

            //print(allpath_a);

            boolean[] visit_b = new boolean[n + 1];
            visit_b[s] = true;
            List<Integer> path_b = new ArrayList<>();
            path_b.add(s);
            dfs(s, b, path_b, visit_b, allpath_b);
            //print(allpath_b);

            int min_amount = Integer.MAX_VALUE;

            for(int i = 0; i < allpath_a.size(); i++) {
                for(int j = 0; j < allpath_b.size(); j++) {
                    int amount = calculate(allpath_a.get(i), allpath_b.get(j));
                    //System.out.println("amount = " + amount);
                    min_amount = Math.min(min_amount, amount);
                }
            }

            return min_amount;
        }

        int calculate(List<Integer> pathA, List<Integer> pathB) {
            int i = 1;
            int amount = 0;
            while(i < pathA.size() && i < pathB.size()) {
                if(pathA.get(i) == pathB.get(i)) {
                    amount += totalFares[pathA.get(i)][pathA.get(i - 1)];
                } else {
                    break;
                }
                i++;
            }

            for(int index = i; index < pathA.size(); index++) {
                amount += totalFares[pathA.get(index)][pathA.get(index - 1)];
            }

            for(int index = i; index < pathB.size(); index++) {
                amount += totalFares[pathB.get(index)][pathB.get(index - 1)];
            }

            return amount;
        }



        void print(List<List<Integer>> path) {
            for(int i = 0; i < path.size(); i++) {
                for(int j = 0; j < path.get(i).size(); j++) {
                    System.out.print(path.get(i).get(j) + " ");
                }
                System.out.println("");
            }
        }
    }



    public Solution solution = new Solution();

    public static void main(String[] args) {
        Solution solution = new Problem().solution;

        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        int answer = solution.solution(6, 4, 6,2, fares);
        System.out.println("answer = " + answer);
    }
}
