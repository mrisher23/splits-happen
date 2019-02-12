package com.punchcyber.splits;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BowlingScoreTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {
                "XXXXXXXXXXXX", 300
            },{
                "9-9-9-9-9-9-9-9-9-9-", 90
            },{
                "5/5/5/5/5/5/5/5/5/5/5", 150
            },{
                "X7/9-X-88/-6XXX81", 167
            }, {
                "--------------------", 0
            }}
        );
    }

    private String scoring;
    private int expected;

    public BowlingScoreTest(String input, int result) {
        scoring = input;
        expected = result;
    }

    @Test
    public void scoreRound() {
        Assume.assumeTrue(expected <= 300 && expected >= 0);
        Assume.assumeTrue(scoring.length()>=10 && scoring.length()<=21);
        assertEquals(expected,BowlingScore.scoreBowlingRound(scoring));
    }

}
