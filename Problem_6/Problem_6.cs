public class Solution {
    public int[] PlusOne(int[] digits) {
        
        int i = digits.Length - 1;
        while (digits[i] == 9){
            if (i == 0){
                int[] res = new int[digits.Length + 1];
                res[0] = 1;
                return res;
            }
            digits[i] = 0;
            i -= 1;
        }
        digits[i] += 1;
        return digits;

    }
}
