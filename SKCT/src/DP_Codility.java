import java.util.*;
public class DP_Codility {

    //codility dynampic programming number_solitaire

//    For example, given the following array:
//
//    A[0] = 1
//    A[1] = -2
//    A[2] = 0
//    A[3] = 9
//    A[4] = -1
//    A[5] = -2
//    one possible game could be as follows:
//
//    the pebble is on square number 0, which is marked;
//    we throw 3; the pebble moves from square number 0 to square number 3; we mark square number 3;
//    we throw 5; the pebble does not move, since there is no square number 8 on the board;
//    we throw 2; the pebble moves to square number 5; we mark this square and the game ends.
//    The marked squares are 0, 3 and 5, so the result of the game is 1 + 9 + (−2) = 8.
// This is the maximal possible result that can be achieved on this board.
}
class Solution {

    public int solution(int[] A) {
        // write your code in Java SE 8
        //A -> array값
        //queue
        int lens = A.length;
        int dp[] = new int[6];
        for (int i = 0; i < 6; i++) {
            dp[i] = A[0];
        }
        for (int i = 1; i < lens; i++) {
            dp[i%6] = getMax6(dp) + A[i];
        }

        return dp[(lens-1)%6];
    }

    private int getMax6(int dp[]){
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}