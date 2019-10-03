package com.example.parrot.pong1;

import android.content.Context;

public class GameUtils {
    /**
     *
     * @param score1
     * @return
     */
    Context context;
    GameEngine gameEngine=new GameEngine(this.context);

    public boolean isScoreZero(int expectedScore)
    {

        if(expectedScore==gameEngine.Score)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }
    public boolean isLivesThree(int expectedLives)
    {
        if (expectedLives==gameEngine.lives)
        {
            return  true;
        }
        else
        {
            return false;
        }

    }
}
