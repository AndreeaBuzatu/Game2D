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
    private Text textInfo4;
    private Text textInfo5;
    private Text textInfo6;

    private Text controlQ;
    private Text controlE;

    private Text textControls;
    private Text textAim;

    private Text Q;
    private Text W;
    private Text E;
    private Text A;
    private Text S;
    private Text D;



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

        item1.setPosition(590,1120);

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
        textInfo4 = new Text();
        textInfo5 = new Text();
        textInfo6 = new Text();
        controlQ = new Text();
        controlE = new Text();
        textControls = new Text();
        Q = new Text();
        W = new Text();
        E = new Text();
        A = new Text();
        S = new Text();
        D = new Text();

        textInfo1.setString("Hello young survivor.");
        textInfo2.setString("I understand that you have ");
        textInfo3.setString("some questions about");
        textInfo4.setString("mission:");
        textInfo5.setString("SURVIVAL");
        textInfo6.setString("You can find the answers below ↓");
        Q.setString("Q");
        W.setString("W");
        E.setString("E");
        A.setString("A");
        S.setString("S");
        D.setString("D");


        controlQ.setString("Q");
        controlE.setString("E");

        textControls.setString("Controls");
        textAim.setString("Aim");


        Vector2f textInfo1Pos1 = new Vector2f(975,577);
        Vector2f textInfo1Pos2 = new Vector2f(890,625);
        Vector2f textInfo1Pos3 = new Vector2f(853,675);
        Vector2f textInfo1Pos4 = new Vector2f(1475,675);
        Vector2f textInfo1Pos5 = new Vector2f(1060,655);
        Vector2f textInfo1Pos6= new Vector2f(800,828);
        Vector2f control1= new Vector2f(638,1170);
        Vector2f control2= new Vector2f(925,1170);
        Vector2f textControlV = new Vector2f(850,890);
        Vector2f textAimV = new Vector2f(925,890);


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

        target.draw(controlQ);
        target.draw(controlE);

        target.draw(textControls);
        //target.draw(textAim);

        target.draw(exitText);
        target.draw(menuHeaderTitle1);
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        pressedKey = keyevent.key;
    }
}
