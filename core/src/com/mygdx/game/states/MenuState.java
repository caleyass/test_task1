package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State{

    private final Texture background;
    private final Texture playButton;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playButton = new Texture("play.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            playButton.dispose();
            gsm.set(new PlayState(gsm));

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(playButton, (Gdx.graphics.getWidth()/2 - playButton.getWidth()/2), Gdx.graphics.getHeight()/2);
        sb.end();
    }


    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}
