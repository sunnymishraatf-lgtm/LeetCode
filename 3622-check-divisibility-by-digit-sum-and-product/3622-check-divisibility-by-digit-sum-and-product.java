class Solution {
    public boolean checkDivisibility(int n) {
        int num = n ;
        int sum = 0;
        int product= 1;
        while(num >0){
            int r = num %10;
            sum = sum + r;
            product = product * r;
            num = num /10;
            if (n%10 == 0){
                product = 0;
            }
        }
        // int value = sum + product;
        return n % (sum + product) == 0;
    }
}