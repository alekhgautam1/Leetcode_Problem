class Solution {
    public int majorityElement(int[] nums) {
        int a = nums[0];
        int count=0;
        for(int num:  nums)
        {
            if(count == 0)
            {
                a=num;
            }
            count +=(num==a)?1:-1;
        }
        return a;
        
    }
}