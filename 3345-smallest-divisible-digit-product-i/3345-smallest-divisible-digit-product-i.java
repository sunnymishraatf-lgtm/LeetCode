class Solution {
    public int smallestNumber(int n, int t) {
        while(true){
            int prod = 1;
            int m = n;
            while(m>0){
                int r = m%10;
                prod= r*prod;
                m=m/10;
            }
            if (prod%t == 0){
                break;
            }
            else{
                n =n+1;
            }
        }
        return n;
    }
}