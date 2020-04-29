public class Dynamic {
    public static void main(String[] args) {
        System.out.println(numberOfCoin(4));
    }

    /**
     * Author: Answer from LeetCode
     * This question is not a normal Dynamic Problem
     * The url of this question is "https://leetcode.com/problems/regular-expression-matching/"
     * */

    enum Result{
        TRUE,
        FALSE
    }

    private static Result[][] memo;

    public static boolean isMatch(String s, String p) {
        memo = new Result[s.length() + 1][p.length() + 1];
        return dp(0,0, s, p);
    }

    public static boolean dp(int i , int j , String text, String pattern){
        if(memo[i][j] != null){
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if(j == pattern.length()){
            ans = i == text.length();
        }else{
            boolean first_match = (i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern) || first_match && dp(i+1, j, text, pattern));
            } else {
                ans = first_match && dp(i + 1, j + 1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    /**
     * This is quiet easy question for dynamic programming
     * This one is to calculate the number of coins for certain value
     * */
    public static int numberOfCoin(int num){
        /**
         * There are three types of coin (1,3,5)
         * */
        int[] result = new int[num + 1];

        if(num == 0){
            return result[0];
        }

        for (int i = 1; i < result.length; i++) {
            if(i < 3 && result[i] == 0){
                result[i] = result[i - 1] + 1;
            }
            if(i >= 3 && i < 5 && result[i] == 0){
                result[i] = Math.min(result[i - 1] + 1, result[i - 3] + 1);
            }
            if(i >=5 && result[i] == 0){
                result[i] = Math.min(result[i - 1]+ 1, Math.min(result[i - 3] + 1, result[i - 5] + 1));
            }
        }

        return result[num];
    }
}