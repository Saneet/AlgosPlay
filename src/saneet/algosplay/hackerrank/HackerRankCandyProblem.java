package saneet.algosplay.hackerrank;

/**
 * HackerRank Candy Problem
 * Solved using two passes one forward pass to assign 1 to every kid and +1 to every better kid.
 * One reverse pass to check the same from the other side.
 * Dynamic programming is used to divide the problem into pairs of children at once and remembering their
 * states in an array. Same array is used and updated on reverse.
 */
public class HackerRankCandyProblem {
    public static int getMinimumCandyCount(int[] score) {
        //Create array to remember values
        int[] values = new int[score.length];

        int curr = 1;
        //Forward pass
        values[0] = curr;
        int prevScore = score[0];
        for (int i = 1; i < score.length; i++) {
            if (score[i] > prevScore) {
                curr++;
            } else {
                curr = 1;
            }
            values[i] = curr;
            prevScore = score[i];
        }

        for (int i = 0; i < score.length; i++) {
            System.out.println(values[i]);
        }

        //Backward pass
        int prevVal = values[score.length - 1];
        prevScore = score[score.length - 1];
        for (int i = score.length - 2; i >= 0; i--) {
            //If dist has increased and value hasn't increased then
            if (score[i] > prevScore && values[i] <= prevVal) {
                prevVal++;
            } else {
                prevVal = values[i];
            }
            values[i] = prevVal;
            prevScore = score[i];
        }


        //Get sum
        int sum = 0;
        for (int i = 0; i < score.length; i++) {
            sum += values[i];
            System.out.println(values[i]);
        }

        return sum;
    }

}
