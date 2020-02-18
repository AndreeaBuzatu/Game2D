package uk.ac.lancaster.scc210.game.dialogue;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;

public class DialogueBox implements Drawable {
    private final int TEXT_SIZE = 60;

    private final int TEXT_PADDING = 10;

    private final Text text;

    private final Font font;

    private final StringBuffer stringBuffer;

    private final FloatRect viewBounds;

    private final int boxHeight;

    private RectangleShape box;

    private String character;

    private boolean open;

    private int offset;

    public DialogueBox(ViewSize viewSize, FontManager fontManager) {
        box = new RectangleShape();

        viewBounds = viewSize.getViewBounds();

        Vector2f size = new Vector2f(viewBounds.width, viewBounds.height / 3);

        Vector2f boxPos = new Vector2f(0, viewBounds.height - (viewBounds.height / 3));

        stringBuffer = new StringBuffer();

        open = true;

        box.setSize(size);

        box.setPosition(boxPos);

        box.setOutlineThickness(10);

        box.setOutlineColor(Color.BLACK);

        box.setFillColor(Color.WHITE);

        font = fontManager.get("font");

        character = "Ishmael";

        stringBuffer.append(String.format("%s: ", character));

        stringBuffer.append("Call me Ishmael. Some years ago—never mind how long precisely—having little " +
                "or no money in my purse, and nothing particular to interest me on shore, I thought I would sail about " +
                "a little and see the watery part of the world. It is a way I have of driving off the spleen and " +
                "regulating the circulation. Whenever I find myself growing grim about the mouth; whenever it is a damp, " +
                "drizzly November in my soul; whenever I find myself involuntarily pausing before coffin warehouses, and bringing " +
                "up the rear of every funeral I meet; and especially whenever my hypos get such an upper hand of me, " +
                "that it requires a strong moral principle to prevent me from deliberately stepping into the street, " +
                "and methodically knocking people’s hats off—then, I account it high time to get to sea as soon as I can. " +
                "This is my substitute for pistol and ball. With a philosophical flourish Cato throws himself upon his sword; " +
                "I quietly take to the ship. There is nothing surprising in this. If they but knew it, almost all men in their degree, " +
                "some time or other, cherish very nearly the same feelings towards the ocean with me. ");

        text = new Text();

        text.setFont(font);

        text.setCharacterSize(TEXT_SIZE);

        text.setColor(Color.RED);

        text.setPosition(new Vector2f(boxPos.x + TEXT_PADDING, boxPos.y + TEXT_PADDING));

        // Round the height down so we can display the all the characters not just half
        boxHeight = (int) Math.floor(box.getGlobalBounds().height / TEXT_SIZE);

        formatText();
    }

    public void update() {
        if (!open) {
            return;
        }

        // TODO: Use KeyListener here
        if (Keyboard.isKeyPressed(Keyboard.Key.A)) {
            formatText();
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

    private void formatText() {
        // If the offset extends beyond the length of the string (- 1 to account for the offset being 0-indexed), then
        // indicate we should close.
        if (offset >= stringBuffer.length() - 1) {
            open = false;
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

        System.out.println(output);
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
}
