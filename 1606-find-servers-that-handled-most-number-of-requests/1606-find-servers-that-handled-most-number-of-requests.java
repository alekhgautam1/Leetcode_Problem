import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;
        
    
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
       TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        int[] requestCount = new int[k];
        
        for (int i = 0; i < n; i++) {
            int startTime = arrival[i];
            int endTime = startTime + load[i];
            
          
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= startTime) {
                availableServers.add(busyServers.poll()[1]);
            }
            
            if (availableServers.isEmpty()) {
                continue;
            }
            
            Integer targetServer = availableServers.ceiling(i % k);
            
          
            if (targetServer == null) {
                targetServer = availableServers.first();
            }
            
            availableServers.remove(targetServer);
            busyServers.offer(new int[]{endTime, targetServer});
            requestCount[targetServer]++;
        }
        
        int maxRequests = 0;
        for (int count : requestCount) {
            maxRequests = Math.max(maxRequests, count);
        }
        
       List<Integer> busiest = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                busiest.add(i);
            }
        }
        
        return busiest;
    }
}