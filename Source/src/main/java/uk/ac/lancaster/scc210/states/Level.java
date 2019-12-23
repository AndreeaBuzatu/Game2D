package uk.ac.lancaster.scc210.states;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.content.TextureManager;

public class Level implements State {
    private Sprite[] sprites;

    public Level() {
        sprites = new Sprite[7];

        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Sprite(TextureManager.getInstance().get("example"));

            sprites[i].setPosition(i * 66, sprites[i].getPosition().y);

            System.out.println(sprites[i].getPosition());
        }
    }

    @Override
    public void draw(RenderTarget target) {
        for (Sprite sprite : sprites) {
            target.draw(sprite);
        }
    }

    @Override
    public void update() {

    }
}
