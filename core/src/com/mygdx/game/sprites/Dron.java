package com.mygdx.game.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Dron {
    public static final int MOVEMENT = 100;
    public static final int GRAVITY = -10;
    private Vector3 position;
    private Vector3 velocity;

    private Rectangle bounds;

    private Texture dron;

    public Dron(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        dron = new Texture("dron.png");
        bounds = new Rectangle(x, y, dron.getWidth()-25, dron.getHeight()-10);
    }

    public void update(float dt) {
        if(position.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if(position.y < 0){
            position.y = 0;
        }
        velocity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Texture getDron() {
        return dron;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void jump(){
        velocity.y = 250;
    }

    public void dispose() {
        dron.dispose();
    }
}


