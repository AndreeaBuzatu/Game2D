package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;

public class MainMenu implements State {
    private final int MENU_TEXT_SIZE = 100;

    private final int LIST_PADDING = 250;

    private FontManager fontManager;

    private ViewSize viewSize;

    private InterfaceList interfaceList;

    private Text menuHeader;

    private FloatRect viewBounds;

    private Sprite background;

    @Override
    public void setup(StateBasedGame game) {
        LevelSelect levelSelect = new LevelSelect();

        TextureManager textureManager = (TextureManager) game.getServiceProvider().get(TextureManager.class);

        background = new Sprite(textureManager.get("level-select.jpg:level-select"));
        background.setScale(2, 2);
        fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        viewSize = (ViewSize) game.getServiceProvider().get(ViewSize.class);

        viewBounds = viewSize.getViewBounds();

        createHeader();

        Vector2f headerPos = menuHeader.getPosition();

        interfaceList = new InterfaceList(game, fontManager.get("font"), new Vector2f(headerPos.x, headerPos.y + LIST_PADDING));

        interfaceList.addListOption("Play", (() -> System.out.println("Hello, world")));

        interfaceList.addListOption("Level Select", (() -> game.pushState(levelSelect)));

        interfaceList.addListOption("Help", (game::popState));

        interfaceList.addListOption("Exit", (game::popState));

        interfaceList.setEnabled(true);


    }

    private void createHeader() {
        menuHeader = new Text();

        menuHeader.setString("MISSION:SURVIVE");

        menuHeader.setFont(fontManager.get("font"));

        menuHeader.setCharacterSize(MENU_TEXT_SIZE);

        FloatRect headerBounds = menuHeader.getGlobalBounds();

        Vector2f headerPos = new Vector2f((viewBounds.width / 2) - (headerBounds.width / 2), viewBounds.height / 5f);

        menuHeader.setPosition(headerPos);
    }

    @Override
    public void update(Time deltaTime) {
        interfaceList.update();
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(background);
        target.draw(interfaceList);
        target.draw(menuHeader);
    }
}
