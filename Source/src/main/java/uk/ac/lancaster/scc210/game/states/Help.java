package uk.ac.lancaster.scc210.game.states;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.TextEvent;
import uk.ac.lancaster.scc210.engine.InputListener;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.content.MusicManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.gui.EscapeText;
import uk.ac.lancaster.scc210.game.gui.MenuHeader;

public class Help implements State, InputListener {
    private final String aimString =
            "You are the pilot of a spaceship who's\n\n" +
            "aim is to defeat the aliens that are\n\n" +
            "trying to invade the Earth\n\n";

    private final String controlsString =
            "Q - rotate left  A -  move left\n\n" +
            "W - move forward D - move right\n\n" +
            "E - rotate right S - move backwards\n\n" +
            "Space - shoot";

    private final int SUB_HEADER_FONT_SIZE = 50;

    private final int SUB_HEADER_INFO_YPOS = SUB_HEADER_FONT_SIZE + 5;

    private FontManager fontManager;

    private StateBasedGame game;

    private EscapeText escapeText;

    private MenuHeader menuHeader;

    private Music music;

    private Sprite background;

    private Keyboard.Key pressedKey;

    private FloatRect viewBounds;

    private Text aimHeader, controlsHeader;

    private Text aimText, controlsText;

    @Override
    public void setup(StateBasedGame game) {
        this.game = game;

        game.addKeyListener(this);

        MusicManager musicManager = (MusicManager) game.getServiceProvider().get(MusicManager.class);

        TextureManager textureManager = (TextureManager) game.getServiceProvider().get(TextureManager.class);

        fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        viewBounds = ((ViewSize) game.getServiceProvider().get(ViewSize.class)).getViewBounds();

        escapeText = new EscapeText(fontManager, game);

        menuHeader = new MenuHeader("HELP", fontManager, viewBounds);

        music = musicManager.get("menu_music");

        background = new Sprite(textureManager.get("level-select.jpg:level-select"));

        background.setScale(2, 2);

        createAimText();

        createControlsText();
    }

    @Override
    public void onEnter(StateBasedGame game) {
        game.addKeyListener(escapeText);

        music.play();
    }

    @Override
    public void onExit(StateBasedGame game) {
        game.removeKeyListener(escapeText);
    }

    @Override
    public void update(Time deltaTime) {
        if (game != null && pressedKey != null && pressedKey == Keyboard.Key.ESCAPE) {
            game.popState();
        }
    }

    private void createAimText() {
        Vector2f headerPos = menuHeader.getPosition();

        float aimTextYPos = headerPos.y + (InterfaceList.LIST_PADDING >> 1);

        aimHeader = createSubHeader("Aim", aimTextYPos);

        aimText = createInformation(aimString, aimTextYPos);
    }

    private void createControlsText() {
        Vector2f headerPos = menuHeader.getPosition();

        float controlsHeaderYPos = headerPos.y + (InterfaceList.LIST_PADDING + (InterfaceList.LIST_PADDING >> 1));

        controlsHeader = createSubHeader("Controls", controlsHeaderYPos);

        controlsText = createInformation(controlsString, controlsHeaderYPos);
    }

    private Text createSubHeader(String textContent, float yPos) {
        Text header = new Text();

        header.setCharacterSize(SUB_HEADER_FONT_SIZE);

        header.setString(textContent);

        header.setColor(Color.CYAN);

        header.setFont(fontManager.get("font"));

        header.setPosition((viewBounds.width / 2) - header.getGlobalBounds().width / 2, yPos);

        return header;
    }

    private Text createInformation(String textContent, float yPos) {
        Text information = new Text();

        information.setCharacterSize(30);

        information.setString(textContent);

        information.setColor(Color.WHITE);

        information.setFont(fontManager.get("font"));

        information.setPosition((viewBounds.width / 2) - information.getGlobalBounds().width / 2, yPos + SUB_HEADER_INFO_YPOS);

        return information;
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(background);

        target.draw(escapeText);

        target.draw(menuHeader);

        target.draw(aimHeader);

        target.draw(aimText);

        target.draw(controlsHeader);

        target.draw(controlsText);
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        pressedKey = keyevent.key;
    }

    @Override
    public void keyTyped(TextEvent textevent) {

    }
}
