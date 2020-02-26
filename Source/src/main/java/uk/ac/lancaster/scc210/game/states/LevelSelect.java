package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.*;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;
import uk.ac.lancaster.scc210.engine.InputListener;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceGrid;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.LevelManager;
import uk.ac.lancaster.scc210.game.level.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelSelect implements State, InputListener {
    private StateBasedGame game;

    private FontManager fontManager;

    private LevelManager levelManager;

    private Sprite background;

    private FloatRect viewBounds;

    private InterfaceGrid interfaceGrid;

    private Text exitText;

    private Text menuHeaderTitle1;
    private Text menuHeaderTitle2;

    private Keyboard.Key pressedKey;

    @Override
    public void setup(StateBasedGame game) {
        this.game = game;

        game.addKeyListener(this);

        fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        levelManager = (LevelManager) game.getServiceProvider().get(LevelManager.class);

        viewBounds = ((ViewSize) game.getServiceProvider().get(ViewSize.class)).getViewBounds();

        TextureManager textureManager = (TextureManager) game.getServiceProvider().get(TextureManager.class);

        background = new Sprite(textureManager.get("level-select.jpg:level-select"));

        background.setScale(2, 2);

        createGrid();

        createExitText();

        createHeader();
    }

    private void createGrid() {
        // TODO: Update this with the develop branch to use the new level manager and level access system
        // TODO: Prevent the user (or hide) locked levels from being loaded
        interfaceGrid = new InterfaceGrid(game, new Vector2f(700,700));

        List<Level> levels = new ArrayList<>(levelManager.values());

        InterfaceList interfaceList = null;

        for (int i = 0; i < levels.size(); i++) {
            if (i % 3 == 0) {
                System.out.println("I: " + i);

                // i % 3 == 0, will == true when i = 0, so in that instance don't add a new column (as one hasn't been created yet)
                if (i > 0) {
                    interfaceGrid.addColumn(interfaceList);
                }

                interfaceList = new InterfaceList(game, fontManager.get("font"), Vector2f.ZERO);
            }

            int finalI = i;

            interfaceList.addListOption(String.format("Level %s", i), (() -> System.out.printf("Load level %s here\n", finalI)));
        }

        interfaceGrid.addColumn(interfaceList);
    }

    private void createHeader() {
        menuHeaderTitle1 = new Text();
        menuHeaderTitle2 = new Text();
        menuHeaderTitle1.setString("LEVEL:");
        menuHeaderTitle2.setString("SELECT");


        Vector2f headerPos = new Vector2f(1100,480);
        Vector2f headerPos2 = new Vector2f(1000, 550);

        menuHeaderTitle1.setPosition(headerPos);
        menuHeaderTitle2.setPosition(headerPos2);
        menuHeaderTitle1.setCharacterSize(60);
        menuHeaderTitle2.setCharacterSize(90);
        menuHeaderTitle1.setStyle(3);
        menuHeaderTitle1.setColor(Color.CYAN);
        menuHeaderTitle2.setColor(Color.YELLOW);
        menuHeaderTitle1.setFont(fontManager.get("font"));
        menuHeaderTitle2.setFont(fontManager.get("font"));
    }

    private void createExitText() {
        exitText = new Text();

        exitText.setString("Press ESC to go back");

        exitText.setFont(fontManager.get("font"));

        exitText.setPosition(0, 0);

        exitText.setCharacterSize(50);

        exitText.setColor(Color.WHITE);
    }

    @Override
    public void update(Time deltaTime) {
        interfaceGrid.update();

        if (game != null && pressedKey != null && pressedKey == Keyboard.Key.ESCAPE) {
            game.popState();
        }
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(background);

        target.draw(interfaceGrid);

        target.draw(exitText);

        target.draw(menuHeaderTitle1);
        target.draw(menuHeaderTitle2);
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        pressedKey = keyevent.key;
    }
}
