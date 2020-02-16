package uk.ac.lancaster.scc210.engine.gui;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;
import uk.ac.lancaster.scc210.engine.InputListener;
import uk.ac.lancaster.scc210.engine.StateBasedGame;

import java.util.ArrayList;

public class InterfaceGrid implements InputListener {
    private final int LIST_WIDTH_PADDING = 130;

    private ArrayList<InterfaceList> lists;

    private Vector2f position;

    private Keyboard.Key previousKey, pressedKey;

    private int columns, previousColumn, currentColumn;

    private float listWidthSum;

    public InterfaceGrid(StateBasedGame game, Vector2f position, int columns) {
        this.position = position;
        this.columns = columns;

        lists = new ArrayList<>();

        listWidthSum = 0;

        previousColumn = -1;

        currentColumn = 0;

        game.addKeyListener(this);
    }

    public void addColumn(InterfaceList list) {
        lists.add(list);

        if (lists.size() > 1) {
            listWidthSum += (list.getGlobalBounds().width * lists.size()) + LIST_WIDTH_PADDING;
        }

        list.setEnabled(false);

        list.setPosition(new Vector2f(position.x + listWidthSum, position.y));
    }

    public void update() {
        // Don't process if nothing is pressed or not enabled
        if ((pressedKey == null && previousKey != null)) {
            return;
        }

        if (pressedKey == Keyboard.Key.A) {
            previousColumn = currentColumn;

            currentColumn--;
        }

        if (pressedKey == Keyboard.Key.D) {
            previousColumn = currentColumn;

            currentColumn++;
        }

        // Ignore if the previous input is the same as the current
        if (previousColumn != currentColumn) {
            if (currentColumn < 0) {
                currentColumn = lists.size() - 1;
            }

            if (currentColumn >= lists.size()) {
                currentColumn = 0;
            }

            InterfaceList currentList = lists.get(currentColumn);

            if (!currentList.isEnabled()) {
                currentList.setEnabled(true);
            }

            if (previousColumn >= 0) {
                InterfaceList previousList = lists.get(previousColumn);

                int previousIndex = previousList.getSelectedIndex();

                // Find the number of items in the current list. Subtract 1 to account for 0-indexed arrays
                int currentOptionsLength = currentList.getOptions().size() - 1;

                // Only apply column-by-column movement once
                if (previousList.isEnabled()) {
                    // Go from one to column to the other on the same row
                    if (previousIndex < currentOptionsLength) {
                        currentList.setSelectedIndex(previousIndex, previousList.getPreviousSelected());

                    } else if (previousIndex >= currentOptionsLength) {
                        // If an item is on a lower row then go back to the last non-empty row in that column
                        currentList.setSelectedIndex(currentOptionsLength, currentOptionsLength - 1);
                    }

                    previousList.setEnabled(false);
                }
            }

            currentList.update();
        }

        pressedKey = null;
    }

    public void draw(RenderTarget target) {
        lists.forEach(list -> list.draw(target));
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        previousKey = pressedKey;

        pressedKey = keyevent.key;
    }
}
