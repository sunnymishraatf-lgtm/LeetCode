class Solution {
    public int lengthOfLastWord(String s) {
        int count = 0 ;
        int c = 0;
        for(int i = s.length()-1; i>=0 ;i--){
            if (s.charAt(i)==' '&& c ==0){
                continue;
            }
            if (s.charAt(i)!=' '){
                c=1;
                count = count +1;
            }else{
                break;
            }
        }
        return count;
    }
}