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

    private Text textInfo1 = new Text();
    private Text textInfo2 = new Text();
    private Text textInfo3 = new Text();
    private Text textInfo4 = new Text();
    private Text textInfo5 = new Text();
    private Text textInfo6 = new Text();
    private Text controlQ = new Text();
    private Text controlE = new Text();
    private Text textControls = new Text();
    private Text textAim = new Text();
    private Text Q = new Text();
    private Text W = new Text();
    private Text E = new Text();
    private Text A = new Text();
    private Text S = new Text();
    private Text D = new Text();
    private Text aim1 = new Text();
    private Text aim2 = new Text();
    private Text aim3 = new Text();
    private Text aim4 = new Text();
    private Text aim5 = new Text();
    private Text aim6 = new Text();
    private Text aim7 = new Text();



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

        item1 = new Sprite(textureManager.get("controls.png:controls"));

        item1.setScale(2,2);

        item1.setPosition(750,900);

        createTextInfo();

        createExitText();

        createHeader();
    }

    @Override
    public void update(Time deltaTime) {
        if (game != null && pressedKey != null && pressedKey == Keyboard.Key.ESCAPE) {
            game.popState();
        }
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
        textInfo1.setString("Hello young survivor.");
        textInfo2.setString("I understand that you have ");
        textInfo3.setString("some questions about");
        textInfo4.setString("mission:");
        textInfo5.setString("SURVIVAL");
        textInfo6.setString("You can find the answers below ↓");
        controlQ.setString("Q");
        controlE.setString("E");
        textControls.setString("Controls");
        textAim.setString("Aim");
        Q.setString("Q - rotate left");
        W.setString("W - move forward");
        E.setString("E - rotate right");
        A.setString("A - move left");
        S.setString("S - move backward");
        D.setString("D - move right");
        aim1.setString("You are the pilot of a spaceship ");
        aim2.setString("");
        aim3.setString("which aim in the game is to defeat");
        aim4.setString("");
        aim5.setString("the alien waves");
        aim6.setString("that are trying");
        aim7.setString("to invade the Earth");

        Vector2f textInfo1Pos1 = new Vector2f(975,577);
        Vector2f textInfo1Pos2 = new Vector2f(890,625);
        Vector2f textInfo1Pos3 = new Vector2f(853,675);
        Vector2f textInfo1Pos4 = new Vector2f(1475,670);
        Vector2f textInfo1Pos5 = new Vector2f(1060,645);
        Vector2f textInfo1Pos6= new Vector2f(800,800);
        Vector2f control1= new Vector2f(805,1190);
        Vector2f control2= new Vector2f(1076,1188);
        Vector2f textControlV = new Vector2f(860,870);
        Vector2f textAimV = new Vector2f(1200,850);
        Vector2f qV = new Vector2f(800,920);
        Vector2f wV = new Vector2f(800,955);
        Vector2f eV = new Vector2f(800,990);
        Vector2f aV = new Vector2f(800,1025);
        Vector2f sV = new Vector2f(800,1060);
        Vector2f dV = new Vector2f(800,1095);
        Vector2f aim1V = new Vector2f(820,910);
        Vector2f aim2V = new Vector2f(1400,1040);
        Vector2f aim3V = new Vector2f(1375,1090);
        Vector2f aim4V = new Vector2f(1365,1140);
        Vector2f aim5V = new Vector2f(1375,1190);
        Vector2f aim6V = new Vector2f(1375,1250);
        Vector2f aim7V = new Vector2f(1350,1300);

        textInfo1.setPosition(textInfo1Pos1);
        textInfo2.setPosition(textInfo1Pos2);
        textInfo3.setPosition(textInfo1Pos3);
        textInfo4.setPosition(textInfo1Pos4);
        textInfo5.setPosition(textInfo1Pos5);
        textInfo6.setPosition(textInfo1Pos6);
        controlQ.setPosition(control1);
        controlE.setPosition(control2);
        textControls.setPosition(textControlV);
        textAim.setPosition(textAimV);
        Q.setPosition(qV);
        W.setPosition(wV);
        E.setPosition(eV);
        A.setPosition(aV);
        S.setPosition(sV);
        D.setPosition(dV);
        aim1.setPosition(aim1V);
        aim2.setPosition(aim2V);
        aim3.setPosition(aim3V);
        aim4.setPosition(aim4V);
        aim5.setPosition(aim5V);
        aim6.setPosition(aim6V);
        aim7.setPosition(aim7V);

        textInfo1.setCharacterSize(30);
        textInfo2.setCharacterSize(30);
        textInfo3.setCharacterSize(30);
        textInfo4.setCharacterSize(28);
        textInfo5.setCharacterSize(140);
        textInfo6.setCharacterSize(30);
        controlQ.setCharacterSize(45);
        controlE.setCharacterSize(45);
        textControls.setCharacterSize(35);
        textAim.setCharacterSize(35);
        Q.setCharacterSize(30);
        W.setCharacterSize(30);
        E.setCharacterSize(30);
        A.setCharacterSize(30);
        S.setCharacterSize(30);
        D.setCharacterSize(30);
        aim1.setCharacterSize(30);
        aim2.setCharacterSize(30);

        textInfo4.setStyle(TextStyle.ITALIC);
        textInfo4.setColor(Color.CYAN);
        textInfo5.setColor(Color.YELLOW);
        textControls.setColor(Color.CYAN);
        textAim.setColor(Color.CYAN);

        textInfo1.setFont(fontManager.get("font"));
        textInfo2.setFont(fontManager.get("font"));
        textInfo3.setFont(fontManager.get("font"));
        textInfo4.setFont(fontManager.get("font"));
        textInfo5.setFont(fontManager.get("font2"));
        textInfo6.setFont(fontManager.get("font"));
        controlQ.setFont(fontManager.get("font"));
        controlE.setFont(fontManager.get("font"));
        textControls.setFont(fontManager.get("font"));
        textAim.setFont(fontManager.get("font"));
        Q.setFont(fontManager.get("font"));
        W.setFont(fontManager.get("font"));
        E.setFont(fontManager.get("font"));
        A.setFont(fontManager.get("font"));
        S.setFont(fontManager.get("font"));
        D.setFont(fontManager.get("font"));
        aim1.setFont(fontManager.get("font"));
        aim2.setFont(fontManager.get("font"));
        aim3.setFont(fontManager.get("font"));
        aim4.setFont(fontManager.get("font"));
        aim5.setFont(fontManager.get("font"));
        aim6.setFont(fontManager.get("font"));
        aim7.setFont(fontManager.get("font"));

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
        target.draw(textInfo4);
        target.draw(textInfo5);
        target.draw(textInfo6);

        /*
        target.draw(controlQ);
        target.draw(controlE);
        target.draw(textControls);
         */

        target.draw(textAim);

        /*
        target.draw(Q);
        target.draw(W);
        target.draw(E);
        target.draw(A);
        target.draw(S);
        target.draw(D);
         */

        target.draw(aim1);
        target.draw(aim2);
        target.draw(aim3);
        target.draw(aim4);
        target.draw(aim5);
        target.draw(aim6);
        target.draw(aim7);

        target.draw(exitText);
        target.draw(menuHeaderTitle1);
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        pressedKey = keyevent.key;
    }
}
