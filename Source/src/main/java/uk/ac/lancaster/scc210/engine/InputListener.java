package uk.ac.lancaster.scc210.engine;

import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.TextEvent;

public interface InputListener {
    void keyPressed(KeyEvent keyevent);

    void keyTyped(TextEvent textevent);
}
