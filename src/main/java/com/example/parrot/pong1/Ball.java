package com.example.parrot.pong1;

import android.content.Context;
import android.graphics.Rect;

public class Ball {
    private Rect hitbox;

    private int xPosition;
    private int yPosition;

    public Ball(Context context, int x, int y) {
        // 1. set up the initial position of the Enemy
        this.xPosition = x;
        this.yPosition = y;

        this.hitbox = new Rect(
                this.xPosition,
                this.yPosition,
                this.xPosition + 50 ,
                this.yPosition +50
        );

    }

    public Rect getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rect hitbox) {
        this.hitbox = hitbox;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public Ball() {
    }
}
