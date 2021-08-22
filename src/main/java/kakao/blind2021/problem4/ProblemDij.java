package kakao.blind2021.problem4;

import java.util.*;

public class ProblemDij {

    class Solution {

        private int[][] allpath;

        private int[][] allFares;

        private List<List<Integer>> graph = new LinkedList<>();

        public int getMinDistanceIndex(int[] dist, boolean[] visits) {
            int min_index = -1;
            int min_distance = Integer.MAX_VALUE;

            for(int i = 1; i < dist.length; i++) {
                if(!visits[i] && dist[i] < min_distance) {
                    min_distance = dist[i];
                    min_index = i;
                }
            }
            return min_index;
        }

        public void dijkstra2(int start, int n) {

            boolean[] visits = new boolean[n + 1];

            allpath[start][start] = 0;

            for(int i = 0; i < n; i++) {
                int index = getMinDistanceIndex(allpath[start], visits);
                if(index == -1) { break; }

                visits[index] = true;

                List<Integer> out = graph.get(index);

                for(int j = 0; j < out.size(); j++) {
                    int ele = out.get(j);
                    if(!visits[ele] && allpath[start][index] + allFares[index][ele] < allpath[start][ele]) {
                        allpath[start][ele] = allpath[start][index] + allFares[index][ele];
                    }
                }
            }
        }

        public void floydWarshall(int dist[][]) {

            for(int k = 1; k < dist.length; k++) {
                for(int i = 1; i < dist.length; i++) {
                    for(int j = 1; j < dist.length; j++) {
                        if(dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }

        }

        public int solution(int n, int s, int a, int b, int[][] fares) {

            int[][] dist = new int[n + 1][n + 1];

            for(int i = 0; i < n + 1; i++) {
                for(int j = 0; j < n + 1; j++) {
                    if(i == j) { dist[i][j] = 0; }
                    else { dist[i][j] = 1000001; }
                }
            }

            for(int i = 0; i < fares.length; i++) {
                dist[fares[i][0]][fares[i][1]] = fares[i][2];
                dist[fares[i][1]][fares[i][0]] = fares[i][2];
            }

            floydWarshall(dist);

            /*allpath = new int[n + 1][n + 1];
            allFares = new int[n + 1][n + 1];

            for(int i = 0; i < n + 1; i++) {
                for(int j = 0; j < n + 1; j++) {
                    allpath[i][j] = Integer.MAX_VALUE;
                }
            }

            for(int i = 0; i < n + 1; i++) { graph.add(new LinkedList<>()); }

            for(int i = 0; i < fares.length; i++) {
                int start = fares[i][0];
                int end = fares[i][1];
                int fare = fares[i][2];

                allFares[start][end] = fare;
                allFares[end][start] = fare;

                graph.get(start).add(end);
                graph.get(end).add(start);
            }

            for(int node = 1; node < n + 1; node++) {
                dijkstra2(node, n);
            }*/

            int answer = Integer.MAX_VALUE;

            for(int node = 1; node < n + 1; node++) {
                answer = Math.min(answer, dist[s][node] + dist[node][a] + dist[node][b]);
            }

            return answer;
        }
    }

    Solution solution = new Solution();

    public static void main(String[] args) {
        Solution solution = new ProblemDij().solution;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        int answer = solution.solution(6, 4, 6,2, fares);
        System.out.println("answer = " + answer);
    }
}
