package uk.ac.lancaster.scc210.engine.gui;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

/**
 * The type List option.
 */
public class ListOption {
    private final int TEXT_SIZE = 70;

    private final char SELECTED_LEFT = '>';

    private final char SELECTED_RIGHT = '<';

    private final Text text;

    private final String label;

    private SelectedListener selectedListener;

    private boolean enabled;

    /**
     * Instantiates a new List option.
     *
     * @param font  the font
     * @param label the label
     */
    ListOption(Font font, String label) {
        this.label = label;

        text = new Text();

        text.setString(label);

        text.setFont(font);

        text.setCharacterSize(TEXT_SIZE);

        enabled = true;
    }

    /**
     * Draw.
     *
     * @param target the target
     */
    public void draw(RenderTarget target) {
        target.draw(text);
    }

    /**
     * Add selected listener.
     *
     * @param selectedListener the selected listener
     */
    void addSelectedListener(SelectedListener selectedListener) {
        this.selectedListener = selectedListener;
    }

    /**
     * Sets current.
     */
    void setCurrent() {
        text.setString(String.format("%s %s %s", SELECTED_LEFT, label, SELECTED_RIGHT));
    }

    /**
     * Select.
     */
    void select() {
        selectedListener.selected();
    }

    /**
     * Sets not current.
     */
    void setNotCurrent() {
        text.setString(label);
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    void setPosition(Vector2f position) {
        text.setPosition(position);
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public Text getText() {
        return text;
    }

    /**
     * Is enabled boolean.
     *
     * @return the boolean
     */
    boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets enabled.
     *
     * @param enabled the enabled
     */
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
