package com.punchcyber.splits;

public class BowlingScore {

    public static void main(String[] args) {
        for(String scorecard : args) {
            int score = scoreBowlingRound(scorecard);
            System.out.print(score);
        }
    }

    /**
     * Computes a player's final score of bowling for American 10-pin bowling.
     * The notation 'X' represents a strike, '/' represents a spare, and '-' a
     * gutter ball. All other numbers represent that many pins knocked down.
     *
     * Input is assumed to be correct in the number of frames represented and the
     * notation for scoring described above.
     * @param scoreCard a String representing 10 frames of bowling using the notation described above
     * @throws NullPointerException when scoreCard is null
     * @return The final score of the bowling round
     */
    public static int scoreBowlingRound(String scoreCard) {
        int score = 0;  //rolling tabulation of the score
        int frame = 0;  //current frame of bowling score
        int ball = 0;   //throw within the current frame
        char[] rolls = scoreCard.toCharArray();
        for(int roll=0; frame < 10 && roll < rolls.length; roll++) {
            int pins = scoreOf(rolls[roll], rolls[(roll-1 < 0)? 0 : roll-1]);
            if(rolls[roll] == 'X') {
                score += pins;
                score += scoreOf(rolls[roll+1],rolls[roll]) + scoreOf(rolls[roll+2],rolls[roll+1]);
                frame++;
            } else if(rolls[roll] == '/') {
                score += pins;
                score += scoreOf(rolls[roll+1],rolls[roll]);
                ball=0;
                frame++;
            } else {
                score += pins;
                ball = (ball + 1) % 2;
                if(ball == 0) {
                    frame++;
                }
            }
        }
        return score;
    }

    /**
     * Translates the notation of bowling into an actual number of pins.
     *  x = 10
     *  - = 0
     *  / = 10 minus prior roll
     *  0-9 = 0-9
     *
     * No validation is performed that only the above characters are used. Use of invalid characters will result in
     * inaccurate scoring.
     *
     * @param c The character representing the current roll
     * @param contextualRoll The character representing the prior roll; this is ignored unless <b>c</b> is a spare
     * @return the number of pins knocked down
     */
    private static int scoreOf(char c, char contextualRoll) {
        if(c == 'X')
            return 10;
        if(c == '-')
            return 0;
        if(c == '/')
            return 10 - scoreOf(contextualRoll, '\0'); //cannot have two spares in a row
        else
            return c - '0';
    }
}
