package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.*;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;

public class MainMenu implements State {

    private FontManager fontManager;

    private InterfaceList interfaceList;

    private Text titleHeader;

    private Text subtitleHeader;

    private Sprite background;

    @Override
    public void setup(StateBasedGame game) {
        LevelSelect levelSelect = new LevelSelect();

        Help help = new Help();

        LeaderboardSelect leaderboardSelect = new LeaderboardSelect();

        TextureManager textureManager = (TextureManager) game.getServiceProvider().get(TextureManager.class);

        background = new Sprite(textureManager.get("level-select.jpg:level-select"));

        background.setScale(2, 2);

        fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        createHeader();

        /*
        The position of the menu items
         */
        interfaceList = new InterfaceList(game, fontManager.get("font"), new Vector2f(1150,850));

        interfaceList.addListOption("Play", (() -> System.out.println("Hello, world")));

        interfaceList.addListOption("Level Select", (() -> game.pushState(levelSelect)));

        interfaceList.addListOption("Leaderboard", (() -> game.pushState(leaderboardSelect)));

        interfaceList.addListOption("Help", (() -> game.pushState(help)));

        interfaceList.addListOption("Exit", (game::popState));

        interfaceList.setEnabled(true);
    }

    private void createHeader() {
        titleHeader = new Text();

        subtitleHeader = new Text();

        titleHeader.setString("MISSION:");

        subtitleHeader.setString("SURVIVE");

        Vector2f headerPos = new Vector2f(1060,450);

        Vector2f headerPos2 = new Vector2f(880, 390);

        titleHeader.setPosition(headerPos);

        subtitleHeader.setPosition(headerPos2);

        titleHeader.setCharacterSize(60);

        subtitleHeader.setCharacterSize(310);

        titleHeader.setStyle(TextStyle.ITALIC);

        titleHeader.setColor(Color.CYAN);

        subtitleHeader.setColor(Color.YELLOW);

        titleHeader.setFont(fontManager.get("font"));

        subtitleHeader.setFont(fontManager.get("font2"));
    }

    @Override
    public void update(Time deltaTime) {
        interfaceList.update();
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(background);

        target.draw(interfaceList);

        target.draw(titleHeader);

        target.draw(subtitleHeader);
    }
}
