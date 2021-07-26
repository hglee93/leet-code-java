package backjoon.p15922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        List<Point> points = new ArrayList<>();

        for(int i = 0; i < count; i++) {

            String[] input = br.readLine().split(" ");
            int m = Integer.parseInt(input[0]);
            int n = Integer.parseInt(input[1]);

            if(points.size() == 0) {
                points.add(new Point(m, n));
                continue;
            }
            boolean isInserted = false;
            Point p = points.get(points.size() - 1);
            //1
            if((p.start <= m && m <= p.end) && (p.start <= n && n <= p.end)) {
            }
            //3
            else if((p.start <= m && m <= p.end)) {
                p.end = n;
            }
            else{
                points.add(new Point(m, n));
            }
        }

        int answer = 0;
        for(Point p : points) {
            answer += (Math.max(p.start, p.end) - Math.min(p.start, p.end));
        }

        System.out.println(answer);
    }
}

class Point {
    int start;
    int end;

    public Point(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
