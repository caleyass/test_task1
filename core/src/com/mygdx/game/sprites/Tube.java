package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.FlappyDemo;

import java.util.Random;


public class Tube {
    public static final int TUBE_WIDTH = 100;

    public static final int FLUCTUATION = 130;
    public static final int TUBE_GAP = 100;
    public static final int LOWEST_OPENING = 120;

    private final Texture topTube, botTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;
    private Rectangle boundsTop, boundsBot;

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    private boolean isPassed;


    public Tube(float x){
        topTube = new Texture("top_tube.png");
        botTube = new Texture("bot_tube.png");
        rand = new Random();

        posTopTube = new Vector2(x + FlappyDemo.WIDTH/2,rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x + FlappyDemo.WIDTH/2, posTopTube.y - TUBE_GAP - botTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth()-20, topTube.getHeight()-10);
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, botTube.getWidth()-20, botTube.getHeight()-10);
        isPassed = false;
    }

    public void reposition(float x){
        posTopTube.set(x,rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - botTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public boolean isDronPassed(Dron dron) {
        return (dron.getPosition().x > posTopTube.x + topTube.getWidth()) && !isPassed;
    }


    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBotTube() {
        return botTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public boolean isPassed() {
        return isPassed;
    }


    public void dispose() {
        topTube.dispose();
        botTube.dispose();
    }
}
