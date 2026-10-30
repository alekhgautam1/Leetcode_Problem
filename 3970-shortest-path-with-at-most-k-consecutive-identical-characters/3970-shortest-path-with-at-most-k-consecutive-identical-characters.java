import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
       List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u].add(new int[]{v, w});
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

      
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

     
        int startCount = 1;
        pq.offer(new long[]{0, 0, startCount});
        dist[0][startCount] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long weight = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

           if (u == n - 1) {
                return (int) weight;
            }

            if (weight > dist[u][count]) {
                continue;
            }

         
            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int w = neighbor[1];

                int nextCount = (labels.charAt(u) == labels.charAt(v)) ? count + 1 : 1;

         
                if (nextCount <= k) {
                    if (weight + w < dist[v][nextCount]) {
                        dist[v][nextCount] = weight + w;
                        pq.offer(new long[]{weight + w, v, nextCount});
                    }
                }
            }
        }

  
        return -1;
    }
}