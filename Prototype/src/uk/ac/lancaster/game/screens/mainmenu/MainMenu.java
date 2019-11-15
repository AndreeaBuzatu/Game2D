package uk.ac.lancaster.game.screens.mainmenu;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;
import uk.ac.lancaster.game.screens.Level;
import uk.ac.lancaster.game.screens.Screen;

import java.io.IOException;
import java.nio.file.Paths;

public class MainMenu extends Screen {
    private final float OPTION_DOWN_START = 200;

    private final float OPTION_DOWN_PADDING = 50;

    private MenuOption[] menuOptions;

    private MenuOption selectedOption, previousOption;

    private Level nextScreen, level;

    private Font font;

    private int selectedOptionIndex;

    public MainMenu() {
        super();

        font = new Font();

        try {
            font.loadFromFile(Paths.get("res", "courier.ttf"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        menuOptions = new MenuOption[2];

        menuOptions[0] = new MenuOption(new Text("Play", font), OPTION_DOWN_START);

        menuOptions[1] = new MenuOption(new Text("Exit", font), OPTION_DOWN_START + OPTION_DOWN_PADDING);

        nextScreen = null;

        level = new Level(this);

        selectedOptionIndex = 0;

        selectedOption = menuOptions[selectedOptionIndex];

        previousOption = null;

        setTextSelected();
    }

    @Override
    public void update() {
        // TODO: Improve entire section
        int i = 0;

        for (MenuOption option : menuOptions) {
            if (Keyboard.isKeyPressed(Keyboard.Key.RETURN) && option.isSelected()) {
                switch (i) {
                    case 0:
                        if (level.isGameOver()) {
                            level = new Level(this);
                        }

                        nextScreen = level;

                        level.setActive(true);

                        active = false;

                        break;

                    case 1:
                        // TODO: Improve this, add some kind of callback to Game
                        active = false;

                        nextScreen = null;

                        break;

                    default:
                        break;
                }
            }

            i++;
        }
    }

    @Override
    public void draw(RenderTarget target) {
        for (MenuOption option : menuOptions) {
            option.draw(target);
        }
    }

    /*
    An event listener is used because direct keyboard input resulted in the keyboard being polled too often.
    This resulted in menu options being selected too quickly (instantly) making selecting an option impossible
     */
    @Override
    public void handleEvents(Event event) {
        if (event.type == Event.Type.KEY_PRESSED) {
            Keyboard.Key key = event.asKeyEvent().key;

            previousOption = selectedOption;

            if (key == Keyboard.Key.W) {
                // Loop back around to the bottom option if at the top option
                if (selectedOptionIndex <= 0) {
                    selectedOptionIndex = menuOptions.length - 1;

                } else {
                    selectedOptionIndex--;
                }

                selectOption();

            } else if (key == Keyboard.Key.S) {
                // Loop back around to the top option if at the bottom option
                if (selectedOptionIndex >= menuOptions.length - 1) {
                    selectedOptionIndex = 0;

                } else {
                    selectedOptionIndex++;
                }

                selectOption();
            }
        }
    }

    private void selectOption() {
        previousOption = selectedOption;

        selectedOption = menuOptions[selectedOptionIndex];

        setTextSelected();
    }

    private void setTextSelected() {
        if (selectedOption != previousOption) {
            selectedOption.setTextSelected();

            selectedOption.centreText();

            selectedOption.setSelected();

            if (previousOption != null) {
                previousOption.setUnselected();

                previousOption.clearText();

                previousOption.centreText();
            }
        }
    }

    @Override
    public Screen nextScreen() {
        return nextScreen;
    }
}
