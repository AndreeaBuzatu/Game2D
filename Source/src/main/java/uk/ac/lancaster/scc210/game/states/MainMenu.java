package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.gui.MenuHeader;

public class MainMenu implements State {
    private final int LIST_PADDING = 250;

    private InterfaceList interfaceList;

    private MenuHeader menuHeader;

    @Override
    public void setup(StateBasedGame game) {
        LevelSelect levelSelect = new LevelSelect();

        HighScoreList highScoreList = new HighScoreList();

        FontManager fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        ViewSize viewSize = (ViewSize) game.getServiceProvider().get(ViewSize.class);

        FloatRect viewBounds = viewSize.getViewBounds();

        menuHeader = new MenuHeader("We Don't Have A Name", fontManager, viewBounds);

        Vector2f headerPos = menuHeader.getPosition();

        interfaceList = new InterfaceList(game, fontManager.get("font"), new Vector2f(headerPos.x, headerPos.y + LIST_PADDING));

        interfaceList.addListOption("Play", (() -> System.out.println("Hello, world")));

        interfaceList.addListOption("Level Select", (() -> game.pushState(levelSelect)));

        interfaceList.addListOption("High Scores", (() -> game.pushState(highScoreList)));

        interfaceList.addListOption("Exit", (game::popState));

        interfaceList.setEnabled(true);
    }

    @Override
    public void update(Time deltaTime) {
        interfaceList.update();
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(menuHeader);

        target.draw(interfaceList);
    }
}
