package leetcode.mergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Problem {
    class Solution {
        public int[][] merge(int[][] intervals) {

            if(intervals.length == 0 || intervals.length == 1) { return intervals; }

            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] first, int[] second) {
                    if(first[0] == second[0]) { return Integer.compare(first[1], second[1]); }
                    return Integer.compare(first[0], second[0]);
                }
            });

            List<int[]> answerList = new ArrayList<>();

            int start = 0; int end = start + 1;

            while(end < intervals.length && start < end) {

                if(intervals[start][0] <= intervals[end][0] && intervals[end][0] <= intervals[start][1]) {
                    if(intervals[start][1] < intervals[end][1]) {
                        intervals[start][1] = intervals[end][1];
                    }
                } else {
                    answerList.add(intervals[start]);
                    start = end;
                }
                ++end;
            }
            answerList.add(intervals[start]);
            int[][] answer = new int[answerList.size()][2];
            return answerList.toArray(answer);
        }
    }

    public Solution solution = new Solution();

    public static void main(String[] args) {
        Problem problem = new Problem();
        Solution solution = problem.solution;
    }
}
