package com.example.parrot.pong1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ScoreUnitTest {
    @Test
    public void isScoresTestPasses() throws Exception {

        GameUtils utils=new GameUtils();
        assertTrue(utils.isScoreZero(0));


    }
    @Test

    public void isLivesThree()throws Exception
    {
        GameUtils utils1=new GameUtils();
        assertTrue(utils1.isLivesThree(3));
    }
}