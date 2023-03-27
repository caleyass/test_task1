package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.FlappyDemo;

public class GameOverState extends State{
    private final Texture background;
    private BitmapFont font;
    private int score;


    public GameOverState(GameStateManager gsm, int score) {
        super(gsm);
        background = new Texture("bg.png");
        font = new BitmapFont();
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2,FlappyDemo.HEIGHT/2);
        this.score = score;
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth/2), 0);
        font.setColor(Color.RED);
        Preferences prefs = Gdx.app.getPreferences("MyGamePreferences");
        font.draw(sb, "Game Over. \nClick to restart\nYour score is " + score + "\nYour best score is " + prefs.getInteger("bestScore"), camera.position.x - 50, camera.viewportHeight / 2);
        sb.end();
    }


    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
    }
}
