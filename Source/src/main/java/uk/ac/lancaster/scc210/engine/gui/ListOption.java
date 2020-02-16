package uk.ac.lancaster.scc210.engine.gui;

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

    ListOption(Font font, String label) {
        this.label = label;

        text = new Text();

        text.setString(label);

        text.setFont(font);

        text.setCharacterSize(TEXT_SIZE);
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

    Text getText() {
        return text;
    }
}
