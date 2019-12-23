package uk.ac.lancaster.scc210.content;

import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.SpriteSheet;

import java.io.IOException;

public class SpriteManager extends ContentManager<Sprite> {
    public static final int SPRITE_HEIGHT = 64;

    public static final int SPRITE_WIDTH = 64;

    private SpriteSheet spriteSheet;

    public SpriteManager() {
        super(new MissingSprite().getSprite());

        try {
            spriteSheet = new SpriteSheet();

            put("example", spriteSheet.get(0, 0));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
