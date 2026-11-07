import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

      
        PriorityQueue<Long> available = new PriorityQueue<>((a, b) -> {
            if (!a.equals(b)) {
                return Long.compare(a, b);
            }
            return 0;
        });

      
        PriorityQueue<Long> busy = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            available.offer(((long) servers[i] << 32) | i);
        }

        long time = 0;

        for (int i = 0; i < m; i++) {
            time = Math.max(time, i);

            while (!busy.isEmpty() && (busy.peek() >>> 32) <= time) {
                long item = busy.poll();
                int index = (int) (item & 0xFFFFFFFFL);
                available.offer(((long) servers[index] << 32) | index);
            }

          
            if (available.isEmpty()) {
                time = busy.peek() >>> 32;
                while (!busy.isEmpty() && (busy.peek() >>> 32) <= time) {
                    long item = busy.poll();
                    int index = (int) (item & 0xFFFFFFFFL);
                    available.offer(((long) servers[index] << 32) | index);
                }
            }

           long bestServer = available.poll();
            int index = (int) (bestServer & 0xFFFFFFFFL);

            ans[i] = index;
            long finishTime = time + tasks[i];
            busy.offer((finishTime << 32) | index);
        }

        return ans;
    }
}