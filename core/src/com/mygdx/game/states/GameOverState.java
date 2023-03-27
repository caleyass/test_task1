package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
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
    private final Texture playButton;
    private BitmapFont font;


    public GameOverState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playButton = new Texture("play.png");
        font = new BitmapFont();
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2,FlappyDemo.HEIGHT/2);

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
        font.draw(sb, "Game Over. \nClick to restart", camera.position.x - 50, camera.viewportHeight / 2);
        sb.end();
    }


    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}
