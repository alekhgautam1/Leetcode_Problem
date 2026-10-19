class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int countLessThan = 0;
        int countEqual = 0;
        
        for (int num : nums) {
            if (num < target) {
                countLessThan++;
            } else if (num == target) {
                countEqual++;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < countEqual; i++) {
            result.add(countLessThan + i);
        }
        
        return result;
    }
}