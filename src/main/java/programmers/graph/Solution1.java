package programmers.graph;

import java.util.*;

/*
    Problem : 가장 먼 노드
    주요 내용 : BFS 탐색, LevelOrder 탐색 하면서 가장 마지막 레벨의 노드의 개수를 리턴 한다.
 */
public class Solution1 {
    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6},{4, 3},{3, 2},{1, 3},{1, 2},{2, 4},{5, 2}};

        Solution solution = new Solution();
        int result = solution.solution(n, edge);
        System.out.println(result);
    }
}

class Solution {

    public int solution(int n, int[][] edge) {

        // 1. 그래프 만들기
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n + 1; i++) { graph.add(new ArrayList<>()); }

        for(int i = 0; i < edge.length; i++) {
            int start = edge[i][0];
            int end = edge[i][1];
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        // 2. BFS 탐색 시작
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visit = new HashSet<>();

        queue.add(1);
        visit.add(1);

        int answer = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int cur = queue.poll();
                for(int next : graph.get(cur)){
                    if(!visit.contains(next)) {
                        queue.add(next);
                        visit.add(next);
                    }
                }
            }
            answer = size;
        }

        return answer;
    }
}
