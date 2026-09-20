class Solution {
    public int reverseDegree(String s) {
       int len =s.length();
       int sum =0;
       for (int i =0;i<len;i++){ 
        sum += (i + 1) * ('z' - s.charAt(i) + 1);
       }
       return sum; 
    }
}