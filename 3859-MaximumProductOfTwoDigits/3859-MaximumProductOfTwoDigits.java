// Last updated: 28/07/2026, 20:22:10
class Solution {

    public int maxProduct(int n) {
        int first = 0,
            second = 0;
        while (n > 0) {
            int x = n % 10;
            if (x > first) {
                second = first;
                first = x;
            } else if (x > second) {
                second = x;
            }
            n /= 10;
        }
        return first * second;
    }
}