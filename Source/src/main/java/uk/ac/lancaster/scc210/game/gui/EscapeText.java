package uk.ac.lancaster.scc210.game.gui;

import org.jsfml.graphics.*;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.TextEvent;
import uk.ac.lancaster.scc210.engine.InputListener;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.content.FontManager;

/**
 * The type Escape text.
 */
public class EscapeText implements InputListener, Drawable {
    private final StateBasedGame game;

    private final Text exitText;

    private Keyboard.Key pressedKey;

    /**
     * Instantiates a new Escape text.
     *
     * @param fontManager the font manager
     * @param game        the game
     */
    public EscapeText(FontManager fontManager, StateBasedGame game) {
        this.game = game;

        game.addKeyListener(this);

        exitText = new Text();

        exitText.setString("Press ESC to go back");

        exitText.setFont(fontManager.get("font"));

        exitText.setPosition(0, 0);

        exitText.setCharacterSize(50);

        exitText.setColor(Color.WHITE);
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        renderTarget.draw(exitText);
    }

    /**
     * Update.
     */
    public void update() {
        if (game != null && pressedKey != null && pressedKey == Keyboard.Key.ESCAPE) {
            game.popState();
        }
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        pressedKey = keyevent.key;
    }

    @Override
    public void keyTyped(TextEvent textevent) {

    }
}
