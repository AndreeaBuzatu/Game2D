package uk.ac.lancaster.scc210.engine;

import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.TextEvent;

/**
 * The interface Input listener.
 */
public interface InputListener {
    /**
     * Key pressed.
     *
     * @param keyevent the keyevent
     */
    void keyPressed(KeyEvent keyevent);

    /**
     * Key typed.
     *
     * @param textevent the textevent
     */
    void keyTyped(TextEvent textevent);
}
