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

public class HelpSelect implements State, InputListener {
    private StateBasedGame game;
    private Keyboard.Key pressedKey;

    private FontManager fontManager;

    private LevelManager levelManager;

    private Sprite background;

    private FloatRect viewBounds;

    private InterfaceGrid interfaceGrid;

    private Text exitText;

    private Sprite item1;

    private Text menuHeaderTitle1;

    private Text textInfo1;
    private Text textInfo2;
    private Text textInfo3;



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

        item1 = new Sprite(textureManager.get("enemy.png:enemy"));

        item1.setScale(2,2);

        item1.setPosition(500,500);

        createTextInfo();

        createExitText();

        createHeader();
    }

    @Override
    public void update(Time deltaTime) {

    }


    private void createExitText() {
        exitText = new Text();

        exitText.setString("Press ESC to go back");

        exitText.setFont(fontManager.get("font"));

        exitText.setPosition(0, 0);
        exitText.setCharacterSize(50);

        exitText.setColor(Color.WHITE);
    }


    private void createTextInfo() {
        textInfo1 = new Text();
        textInfo2 = new Text();
        textInfo3 = new Text();

        textInfo1.setString("Hello young survivor");
        textInfo2.setString("I understand that you have some questions");
        textInfo3.setString("about mission:SURVIVE. You can find the answers below ↓");

        Vector2f textInfo1Pos1 = new Vector2f(200,480);
        Vector2f textInfo1Pos2 = new Vector2f(200,520);
        Vector2f textInfo1Pos3 = new Vector2f(200,560);

        textInfo1.setPosition(textInfo1Pos1);
        textInfo2.setPosition(textInfo1Pos2);
        textInfo3.setPosition(textInfo1Pos3);

        textInfo1.setCharacterSize(35);
        textInfo2.setCharacterSize(36);
        textInfo3.setCharacterSize(37);

        textInfo1.setFont(fontManager.get("font"));
        textInfo2.setFont(fontManager.get("font"));
        textInfo3.setFont(fontManager.get("font"));
    }

    private void createHeader() {
        menuHeaderTitle1 = new Text();
        menuHeaderTitle1.setString("HELP:");
        Vector2f headerPos = new Vector2f(1155,480);
        menuHeaderTitle1.setPosition(headerPos);
        menuHeaderTitle1.setCharacterSize(60);
        //menuHeaderTitle1.setStyle(3);
        menuHeaderTitle1.setColor(Color.CYAN);
        menuHeaderTitle1.setFont(fontManager.get("font"));
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(background);
        target.draw(item1);
        target.draw(textInfo1);
        target.draw(textInfo2);
        target.draw(textInfo3);
        target.draw(exitText);
        target.draw(menuHeaderTitle1);
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        pressedKey = keyevent.key;
    }
}
