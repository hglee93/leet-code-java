package backjoon.FloydWashall;

public class Main {

    private static int INF = 100000, V = 4;

    void floydWarshall(int graph[][])
    {
        int dist[][] = new int[4][4];
        int i, j, k;
        for(i = 0; i < 4; i++) {
            for(j = 0; j < 4; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for(k = 0; k < 4; k++) {
            for(i = 0; i < 4; i++) {
                for(j = 0; j < 4; j++) {
                    System.out.println("(" + i + ", " + k + ") -> (" + k + ", " + j + ") = (" + i + ", " + j + ")");
                    if(dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        printSolution(dist);
    }

    void printSolution(int dist[][])
    {
        System.out.println("The following matrix shows the shortest "+
                "distances between every pair of vertices");
        for (int i=0; i<V; ++i)
        {
            for (int j=0; j<V; ++j)
            {
                if (dist[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int graph[][] = {
                {0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}};
        Main main = new Main();
        main.floydWarshall(graph);
    }
}
