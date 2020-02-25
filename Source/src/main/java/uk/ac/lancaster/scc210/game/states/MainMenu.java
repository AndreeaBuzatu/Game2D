package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.*;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;
import java.awt.Font;

public class MainMenu implements State {
    private final int MENU_TEXT_SIZE = 100;

    private final int LIST_PADDING = 250;

    private FontManager fontManager;

    private ViewSize viewSize;

    private InterfaceList interfaceList;

    private Text menuHeaderTitle1;
    private Text menuHeaderTitle2;

    private FloatRect viewBounds;

    private Sprite background;



    @Override
    public void setup(StateBasedGame game) {
        LevelSelect levelSelect = new LevelSelect();
        HelpSelect helpSelect = new HelpSelect();


        TextureManager textureManager = (TextureManager) game.getServiceProvider().get(TextureManager.class);
        background = new Sprite(textureManager.get("level-select.jpg:level-select"));
        background.setScale(2, 2);


        fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);


        viewSize = (ViewSize) game.getServiceProvider().get(ViewSize.class);

        viewBounds = viewSize.getViewBounds();

        createHeader();

        Vector2f headerPos1 = menuHeaderTitle1.getPosition();
        Vector2f headerPos2 = menuHeaderTitle2.getPosition();

        /*
        The position of the menu items
         */
        interfaceList = new InterfaceList(game, fontManager.get("font"), new Vector2f(1150,700));

        interfaceList.addListOption("Play", (() -> System.out.println("Hello, world")));

        interfaceList.addListOption("Level Select", (() -> game.pushState(levelSelect)));

        interfaceList.addListOption("Help", (() -> game.pushState(helpSelect)));

        interfaceList.addListOption("Exit", (game::popState));

        interfaceList.setEnabled(true);
    }

    private void createHeader() {
        menuHeaderTitle1 = new Text();
        menuHeaderTitle2 = new Text();
        menuHeaderTitle1.setString("MISSION:");
        menuHeaderTitle2.setString("SURVIVE");


        //menuHeader.setCharacterSize(MENU_TEXT_SIZE);

        FloatRect headerBounds = menuHeaderTitle1.getGlobalBounds();
        FloatRect headerBounds2 = menuHeaderTitle2.getGlobalBounds();

        Vector2f headerPos = new Vector2f((viewBounds.width / 2) - (headerBounds.width / 2), viewBounds.height / 5f);
        Vector2f headerPos2 = new Vector2f((viewBounds.width / 2) - (headerBounds2.width / 2), viewBounds.height / 5f);
        menuHeaderTitle1.setPosition(headerPos);
        menuHeaderTitle2.setPosition(headerPos2);
        menuHeaderTitle1.setCharacterSize(80);
        menuHeaderTitle2.setCharacterSize(310);
        menuHeaderTitle1.setColor(Color.add(Color.GREEN, Color.CYAN));
        menuHeaderTitle2.setColor(Color.YELLOW);
        menuHeaderTitle1.setFont(fontManager.get("font"));
        menuHeaderTitle2.setFont(fontManager.get("font2"));
    }

    @Override
    public void update(Time deltaTime) {
        interfaceList.update();
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(background);
        target.draw(interfaceList);
        target.draw(menuHeaderTitle1);
        target.draw(menuHeaderTitle2);
    }
}
