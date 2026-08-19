class Solution {
    public double myPow(double x, int n) {
        long N =n;
        if(N<0)
        {
            x=1/x;
            N= -N;
        }
       double r =1.0;
       double currPod = x;
       while(N>0)
       {
        if(N%2==1)
        {
            r *= currPod;
        }
        currPod *= currPod;
        N/=2;
       }
       return r;
        
    }
}