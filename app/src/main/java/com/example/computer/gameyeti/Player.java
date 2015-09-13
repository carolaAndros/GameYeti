package com.example.computer.gameyeti;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player  extends GameObject
{
    private Bitmap spritesheet;
    private int score;
    private double spostamento = 30;
    private int numeroSpostamenti = 0;
    private boolean right;
    private boolean playing;
    private boolean move = false;
    private Animation animation = new Animation();
    private long startTime;

    public boolean getMove()
    {
        return move;
    }

    public void setMove(boolean move)
    {
        this.move = move;
    }

    public Player(Bitmap res, int w, int h, int numFrames)
    {
        x = 200;
        y = GamePanel.HEIGHT/2;
        dx = 0;
        score = 0;
        height = h;
        width = w;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for(int i = 0; i<image.length; i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(300);
        startTime = System.nanoTime();
    }

    public void setRight(boolean b)
    {
        right = b;
    }

    public void update(Player player)
    {
        if(!move) { return; }
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (elapsed > 100) {
            score++;
            startTime = System.nanoTime();
        }
        animation.update();

        if (!right) {
            //System.out.println("Sposto a sinistra: " + player.getX());
            if (player.getX() > 100) {
                dx = (int) (spostamento / 3);

                x -= dx;
                dx = 0;
                //System.out.println("Mi sono spostato a sinistra: " + x);
                numeroSpostamenti++;

                System.out.println("Spostamenti: " + numeroSpostamenti);
            }
        } else {
            //System.out.println("Sposto a destra: " + player.getX());
            if (player.getX() < (GamePanel.WIDTH - 100)) {
                dx = (int) (spostamento / 3);

                x += dx;
                dx = 0;
                //System.out.println("Mi sono spostato a destra: " + x);
                numeroSpostamenti++;

                System.out.println("Spostamenti: " + numeroSpostamenti);
            }
        }
        if(numeroSpostamenti == 3)
        {
            System.out.println("Spostamenti eseguiti: " + numeroSpostamenti);
            move = false;
            numeroSpostamenti = 0;
        }

        //if(dx > 14) dx = 14;
        //if(dx < -14) dx = -14;


    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }
    public int getScore()
    {
        return score;
    }
    public boolean getPlaying()
    {
        return playing;
    }
    public void setPlaying(boolean b)
    {
        playing = b;
    }
    public void resetDYA()
    {
        spostamento = 0;
    }
    public void resetScore()
    {
        score = 0;
    }
}
