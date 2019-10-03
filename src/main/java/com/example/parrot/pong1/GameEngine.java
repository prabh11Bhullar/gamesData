package com.example.parrot.pong1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameEngine extends SurfaceView implements Runnable {
    public GameEngine(Context context)
    {
        super(context);
    }

    // -----------------------------------
    // ## ANDROID DEBUG VARIABLES
    // -----------------------------------

    // Android debug variables
    final static String TAG="PONG-GAME";

    // -----------------------------------
    // ## SCREEN & DRAWING SETUP VARIABLES
    // -----------------------------------

    // screen size
    int screenHeight;
    int screenWidth;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;

    int Score=0;
    int lives=3;

    // -----------------------------------
    // ## GAME SPECIFIC VARIABLES
    // -----------------------------------

    // ----------------------------
    // ## SPRITES
    // ----------------------------
    int ballXPosition;      // keep track of ball -x
    int ballYPosition;      // keep track of ball -y
    int rectXPosition;;
    int rectYPosition=2000;


    // ----------------------------
    // ## GAME STATS - number of lives, score, etc
    // ----------------------------


    public GameEngine(Context context, int w, int h) {
        super(context);


        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;


        this.printScreenInfo();

        // @TODO: Add your sprites to this section
        // This is optional. Use it to:
        //  - setup or configure your sprites
        //  - set the initial position of your sprites
        this.ballXPosition = this.screenWidth / 2;
        this.ballYPosition = this.screenHeight / 2;


        // @TODO: Any other game setup stuff goes here


    }

    // ------------------------------
    // HELPER FUNCTIONS
    // ------------------------------

    // This funciton prints the screen height & width to the screen.
    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }


    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.updatePositionsofrect();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------


    String directionBallIsMoving = "down";
    String directionRectIsMoving = "right";
    String personTapped="";


    // 1. Tell Android the (x,y) positions of your sprites
    public void updatePositions() {
        // @TODO: Update the position of the sprites
        if (directionBallIsMoving.contentEquals("down")) {
            this.ballYPosition = this.ballYPosition + 30;

            if (this.ballYPosition>= this.screenHeight) {
                directionBallIsMoving = "up";
            }

        } else if (directionBallIsMoving.contentEquals("up")) {
            this.ballYPosition = this.ballYPosition - 30;
            // make ball bounce off left wall
            // 1. detect when you reach the left wall
            if (this.ballYPosition <= 0) {
                // 2. change the direction of the ball
                directionBallIsMoving = "down";
            }
      }
        Log.d(TAG, "XPos: " + this.ballXPosition);
    }
       public void updatePositionsofrect () {
//            // @TODO: Update the position of the sprites
           if (personTapped.contentEquals("right") || directionRectIsMoving.contentEquals("right")) {
               this.rectXPosition = this.rectXPosition + 20;
               if (this.rectXPosition + 200 > this.screenWidth) {
                   directionRectIsMoving = "left";
               }

           } else if (personTapped.contentEquals("left") || directionRectIsMoving.contentEquals("left")) {
               this.rectXPosition = this.rectXPosition - 20;
               if (this.rectXPosition <= 0) {

                   directionRectIsMoving = "right";

               }

           }
           if(ballYPosition+50>=rectYPosition &&
                   ballXPosition>=rectXPosition &&
                   ballXPosition<=rectXPosition+200)
           {
               directionBallIsMoving="up";
               Score=Score+2;
           }
           //to restart the position of ball when it missed the racket
           if(ballYPosition>=screenHeight)
           {
               this.ballXPosition = this.screenWidth / 2;
               this.ballYPosition = this.screenHeight / 2;
               directionBallIsMoving="down";
               lives=lives-1;
           }

       }
//            if (directionRectIsMoving.contentEquals("right")) {
//                this.rectXPosition = this.rectXPosition + 10;
//
//                if (this.rectXPosition+200 > this.screenWidth) {
//                    directionRectIsMoving = "left";
//                }
//
//            } else if (directionRectIsMoving.contentEquals("left")) {
//                this.rectXPosition = this.rectXPosition - 10;
//                // make ball bounce off left wall
//                // 1. detect when you reach the left wall
//                if (this.rectXPosition <= 0) {
//                    // 2. change the direction of the ball
//                    directionRectIsMoving = "right";
//                }
//            }



//
//
//        // 1. calculate a new position for the ball!
//        this.ballXPosition = this.ballXPosition + 10;
//
//        if (this.ballXPosition >= this.screenWidth) {
//            this.ballXPosition = this.ballXPosition - 40;
//        }

        // DEBUG - by outputing current positiong



//        this.ballYPosition = this.ballYPosition - 10;

//        // LEFT:
//        this.ballXPosition = this.ballXPosition - 10;
//        // RIGHT:
//        this.ballXPosition = this.ballXPosition + 10;
//        // DOWN:
//        this.ballYPosition = this.ballYPosition + 10;
//        // UP:
//        this.ballYPosition = this.ballYPosition - 10;


        // @TODO: Collision detection code




    // 2. Tell Android to DRAW the sprites at their positions
    public void redrawSprites()
    {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------
            // Put all your drawing code in this section

            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,0,0,255));
            paintbrush.setColor(Color.WHITE);

            //@TODO: Draw the sprites (rectangle, circle, etc)

            // 1. Draw the ball
            this.canvas.drawRect(
                    ballXPosition,
                    ballYPosition,
                    ballXPosition + 50,
                    ballYPosition + 50,
                    paintbrush);
            //Draw Ractangle
            
            paintbrush.setColor(Color.YELLOW);
             canvas.drawRect(rectXPosition, rectYPosition, rectXPosition+200,rectYPosition+50, paintbrush);



            //@TODO: Draw game statistics (lives, score, etc)
            paintbrush.setTextSize(60);
            canvas.drawText("Score:"+this.Score, 20, 100, paintbrush);
            paintbrush.setTextSize(60);
            canvas.drawText("Lives:"+this.lives, 1000, 100, paintbrush);


            //----------------
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    // Sets the frame rate of the game
    public void setFPS() {
        try {
            gameThread.sleep(50);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?
        if (userAction == MotionEvent.ACTION_DOWN) {

            float fingerXpostion=event.getX();
            float fingerYpostion=event.getY();
            // user pushed down on screen
            Log.d(TAG, "Person's pressed: "
                    + fingerXpostion + ","
                    + fingerYpostion);
            //comp the position of tap to width of screen
            int middleOfScreen=this.screenWidth/2;
            if(fingerXpostion<middleOfScreen)
            {
              personTapped="left";

            }
            else
            {
               personTapped="right";
            }
        }
        else if (userAction == MotionEvent.ACTION_UP) {
            // user lifted their finger
        }
        return true;

    }
}