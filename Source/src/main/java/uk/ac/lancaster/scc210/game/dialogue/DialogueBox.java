package uk.ac.lancaster.scc210.game.dialogue;

import org.jsfml.graphics.*;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.TextEvent;
import uk.ac.lancaster.scc210.engine.InputListener;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;

import java.util.Iterator;
import java.util.List;

/**
 * The type Dialogue box.
 */
public class DialogueBox implements Drawable, InputListener {
    private final Time TIME_GAP = Time.getSeconds(1f);

    private final int TEXT_SIZE = 60;

    private final int TEXT_PADDING = 10;

    private Iterator<Line> lineIterator;

    private final Text text;

    private final Font font;

    private final StringBuffer stringBuffer;

    private final FloatRect viewBounds;

    private final RectangleShape box;

    private final int boxHeight;

    private Keyboard.Key pressedKey;

    private Time elapsedTime;

    private boolean open;

    private int offset;

    /**
     * Instantiates a new Dialogue box.
     *
     * @param viewSize    the view size
     * @param fontManager the font manager
     */
    public DialogueBox(ViewSize viewSize, FontManager fontManager) {
        box = new RectangleShape();

        viewBounds = viewSize.getViewBounds();

        Vector2f size = new Vector2f(viewBounds.width, viewBounds.height / 3);

        Vector2f boxPos = new Vector2f(0, viewBounds.height - (viewBounds.height / 3));

        stringBuffer = new StringBuffer();

        open = true;

        box.setSize(size);

        box.setPosition(boxPos);

        box.setOutlineThickness(2);

        box.setOutlineColor(Color.WHITE);

        box.setFillColor(Color.BLACK);

        box.setOutlineColor(Color.WHITE);

        font = fontManager.get("font");

        text = new Text();

        text.setFont(font);

        text.setCharacterSize(TEXT_SIZE);

        text.setColor(Color.WHITE);

        text.setPosition(new Vector2f(boxPos.x + TEXT_PADDING, boxPos.y + TEXT_PADDING));

        // Round the height down so we can display the all the characters not just half
        boxHeight = (int) Math.floor(box.getGlobalBounds().height / TEXT_SIZE);

        formatText();

        elapsedTime = Time.ZERO;
    }

    /**
     * Update.
     *
     * @param deltaTime the delta time
     */
    public void update(Time deltaTime) {
        if (!open) {
            return;
        }

        elapsedTime = Time.add(elapsedTime, deltaTime);

        if (pressedKey == Keyboard.Key.SPACE && elapsedTime.asSeconds() >= TIME_GAP.asSeconds()) {
            if (formatText()) {
                if (lineIterator != null && lineIterator.hasNext()) {
                    formatDialogue();

                } else {
                    open = false;
                }
            }

            elapsedTime = Time.ZERO;

            pressedKey = null;
        }
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        if (!open) {
            return;
        }

        renderTarget.draw(box);

        renderTarget.draw(text);
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        pressedKey = keyevent.key;
    }

    @Override
    public void keyTyped(TextEvent textevent) {

    }

    private boolean formatText() {
        // If the offset extends beyond the length of the string (- 1 to account for the offset being 0-indexed), then
        // indicate we should close.
        if (offset >= stringBuffer.length() - 1) {
            return true;
        }

        // Save the starting offset so we can use it as the starting point for trimming the text
        int startingOffset = offset;

        int newlines = 0;

        // Height - 2 account for 0 indexing and the first line being automatically included
        while (shouldInsertNewLine(offset) && newlines < boxHeight - 2) {
            // Find the index of the string buffer where a newline should be inserted
            int newLineOffset = findLineEndIndex(offset);

            // Stop if we can't find the new line
            if (newLineOffset == -1) {
                break;
            }

            if (offset != 0) {
                stringBuffer.insert(offset, '\n');

                newlines++;
            }

            // Move the current offset past the newline (as it doesn't count)
            offset = newLineOffset + 1;
        }

        // Display only the cut text
        String output = stringBuffer.substring(startingOffset, offset);

        // Remove any preceding newlines of spaces.
        text.setString(output.trim());

        return false;
    }

    private boolean shouldInsertNewLine(int start) {
        return findLineEndIndex(start) != start;
    }

    private int findLineEndIndex(int start) {
        int i = start;

        // We start at an offset
        float totalWidth = TEXT_PADDING;

        for (; i < stringBuffer.length(); i++) {
            Glyph fontGlyph = font.getGlyph(Character.codePointAt(stringBuffer, i), text.getCharacterSize(), false);

            int advance = fontGlyph.advance;

            if (stringBuffer.charAt(i) == '\n') {
                continue;
            }

            if (totalWidth > viewBounds.width - TEXT_PADDING) {
                return i - 1;
            }

            totalWidth += advance;
        }

        return i - 1;
    }

    /**
     * Sets dialogue.
     *
     * @param lines the lines
     */
    public void setDialogue(List<Line> lines) {
        if (!lines.isEmpty()) {
            lineIterator = lines.iterator();
        }

        if (!lines.isEmpty()) {
            formatDialogue();

        } else {
            open = false;
        }
    }

    private void formatDialogue() {
        text.setString("");

        // Clear the buffer
        stringBuffer.setLength(0);

        offset = 0;

        Line line = lineIterator.next();

        stringBuffer.append(String.format("%s: ", line.getCharacter()));

        stringBuffer.append(line.getLine());

        open = true;

        formatText();
    }

    /**
     * Is open boolean.
     *
     * @return the boolean
     */
    public boolean isOpen() {
        return open;
    }
}
