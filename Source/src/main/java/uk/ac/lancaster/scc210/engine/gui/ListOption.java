package uk.ac.lancaster.scc210.engine.gui;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class ListOption {
    private final int TEXT_SIZE = 70;

    private final char SELECTED_LEFT = '>';

    private final char SELECTED_RIGHT = '<';

    private final Text text;

    private final String label;

    private SelectedListener selectedListener;

    private boolean enabled;

    ListOption(Font font, String label) {
        this.label = label;

        text = new Text();

        text.setString(label);

        text.setFont(font);

        text.setCharacterSize(TEXT_SIZE);

        enabled = true;
    }

    public void draw(RenderTarget target) {
        target.draw(text);
    }

    void addSelectedListener(SelectedListener selectedListener) {
        this.selectedListener = selectedListener;
    }

    void setCurrent() {
        text.setString(String.format("%s %s %s", SELECTED_LEFT, label, SELECTED_RIGHT));
    }

    void select() {
        selectedListener.selected();
    }

    void setNotCurrent() {
        text.setString(label);
    }

    void setPosition(Vector2f position) {
        text.setPosition(position);
    }

    public Text getText() {
        return text;
    }

    boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if (enabled) {
            text.setColor(Color.BLACK);

            text.setStyle(Text.REGULAR);

        } else {
            // Set to light grey
            text.setColor(new Color(211, 211, 211));

            text.setStyle(Text.ITALIC);
        }
    }
}
