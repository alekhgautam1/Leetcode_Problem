class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aliceArrival = calculateDayOfYear(arriveAlice);
        int aliceLeave = calculateDayOfYear(leaveAlice);
        int bobArrival = calculateDayOfYear(arriveBob);
        int bobLeave = calculateDayOfYear(leaveBob);
        
        int latestArrival = Math.max(aliceArrival, bobArrival);
        int earliestLeave = Math.min(aliceLeave, bobLeave);
        
       if (latestArrival <= earliestLeave) {
            return earliestLeave - latestArrival + 1;
        }
        
        return 0;
    }
    
    private int calculateDayOfYear(String date) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonths[i];
        }
        
        return totalDays;
    }
}