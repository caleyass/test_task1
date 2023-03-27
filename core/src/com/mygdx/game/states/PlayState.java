package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.sprites.Dron;
import com.mygdx.game.sprites.Tube;


public class PlayState extends State{

    public static final int TUBE_SPACING = 90;
    public static final int TUBE_COUNT = 4;

    private final Dron dron;
    private final Texture background;
    private final BitmapFont scoreText;

    private Array<Tube> tubes;
    private int score;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        dron = new Dron(50, 150);
        background = new Texture("bg.png");
        scoreText = new BitmapFont();
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2,FlappyDemo.HEIGHT/2);

        tubes = new Array<Tube>();
        for (int i = 0; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
        score = 0;

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            dron.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        dron.update(dt);
        camera.position.x = dron.getPosition().x + 80 ;
        for(int i = 0; i < tubes.size; i++){
            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                tube.setPassed(false);
            }
            if(tube.collides(dron.getBounds())) {
                System.out.println("collide");
                gsm.set(new GameOverState(gsm));
            }
            else if(tube.isDronPassed(dron)) {
                tube.setPassed(true);
                score++;
                System.out.println("Tube counter: " + score);
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth/2), 0);
        sb.draw(dron.getDron(), dron.getPosition().x,dron.getPosition().y);
        scoreText.setColor(Color.WHITE);
        scoreText.draw(sb, "Score: " + score, camera.position.x - 40, camera.viewportHeight - 20);
        for(Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBotTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        dron.dispose();
        for(Tube tube : tubes){
            tube.dispose();
        }
        scoreText.dispose();
    }
}
