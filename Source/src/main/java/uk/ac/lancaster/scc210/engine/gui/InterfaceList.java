package uk.ac.lancaster.scc210.engine.gui;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;
import uk.ac.lancaster.scc210.engine.InputListener;
import uk.ac.lancaster.scc210.engine.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

public class InterfaceList implements InputListener {
    private final int OPTION_HEIGHT_PADDING = 50;

    private List<ListOption> options;

    private Font font;

    private Vector2f position;

    private Keyboard.Key previousKey, pressedKey;

    private int previousSelected, selectedIndex;

    public InterfaceList(StateBasedGame game, Font font, Vector2f position) {
        this.font = font;
        this.position = position;

        options = new ArrayList<>();

        previousSelected = -1;

        selectedIndex = 0;

        game.addKeyListener(this);
    }

    public void addListOption(String label, SelectedListener listener) {
        Vector2f optionPos = position;

        for (ListOption option : options) {
            FloatRect bounds = option.getText().getGlobalBounds();

            Vector2f textSize = new Vector2f(0f, bounds.height + OPTION_HEIGHT_PADDING);

            optionPos = Vector2f.add(optionPos, textSize);
        }

        ListOption listOption = new ListOption(font, label, optionPos);

        listOption.addSelectedListener(listener);

        options.add(listOption);
    }

    public void update() {
        // Don't process if nothing is pressed
        if (pressedKey == null && previousKey != null) {
            return;
        }

        if (pressedKey == Keyboard.Key.W || pressedKey == Keyboard.Key.UP) {
            previousSelected = selectedIndex;

            selectedIndex--;
        }

        if (pressedKey == Keyboard.Key.S || pressedKey == Keyboard.Key.DOWN) {
            previousSelected = selectedIndex;

            selectedIndex++;
        }

        // Don't handle repeat actions
        if (previousSelected != selectedIndex) {
            // Loop back around from the top to the bottom
            if (selectedIndex < 0) {
                selectedIndex = options.size() - 1;
            }

            // Loop back around from the bottom to the top
            if (selectedIndex >= options.size()) {
                selectedIndex = 0;
            }

            options.get(selectedIndex).setCurrent();

            // Deselect the previous item
            if (previousSelected >= 0) {
                options.get(previousSelected).setNotCurrent();
            }

            if (pressedKey == Keyboard.Key.SPACE || pressedKey == Keyboard.Key.RETURN) {
                options.get(selectedIndex).select();
            }
        }

        pressedKey = null;
    }

    public void draw(RenderTarget target) {
        options.forEach(option -> option.draw(target));
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        previousKey = pressedKey;

        pressedKey = keyevent.key;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }
}
