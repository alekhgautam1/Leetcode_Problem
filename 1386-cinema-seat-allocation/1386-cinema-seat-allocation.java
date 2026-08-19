import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
       
        Map<Integer, Integer> rowToSeats = new HashMap<>();
        
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
          
            rowToSeats.put(row, rowToSeats.getOrDefault(row, 0) | (1 << col));
        }
        
        int leftMask = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);    

        int rightMask = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);   
       
        int middleMask = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);   
        
      
        int maxGroups = (n - rowToSeats.size()) * 2;
        

        for (int reservedMask : rowToSeats.values()) {
            boolean canFitLeft = (reservedMask & leftMask) == 0;
            boolean canFitRight = (reservedMask & rightMask) == 0;
            
            if (canFitLeft && canFitRight) {
               
                maxGroups += 2;
            } else if (canFitLeft || canFitRight || (reservedMask & middleMask) == 0) {
              
                maxGroups += 1;
            }
        }
        
        return maxGroups;
    }
}