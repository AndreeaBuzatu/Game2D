package uk.ac.lancaster.scc210.game.states;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.gui.InterfaceGrid;
import uk.ac.lancaster.scc210.engine.gui.InterfaceList;
import uk.ac.lancaster.scc210.engine.states.State;

public class LevelSelect implements State {
    private InterfaceGrid interfaceGrid;

    private Sprite background;

    @Override
    public void setup(StateBasedGame game) {
        FontManager fontManager = (FontManager) game.getServiceProvider().get(FontManager.class);

        ViewSize viewSize = (ViewSize) game.getServiceProvider().get(ViewSize.class);

        TextureManager textureManager = (TextureManager) game.getServiceProvider().get(TextureManager.class);

        background = new Sprite(textureManager.get("level-select.jpg:level-select"));

        background.setScale(2, 2);

        InterfaceList interfaceList = new InterfaceList(game, fontManager.get("font"), Vector2f.ZERO);

        interfaceList.addListOption("Level 1", (() -> System.out.println("Hello, world: " + System.currentTimeMillis())));

        interfaceList.addListOption("Level 2", (() -> System.out.println("Thing")));

        interfaceList.addListOption("Level 3", (() -> System.out.println("Item")));

        InterfaceList otherList = new InterfaceList(game, fontManager.get("font"), Vector2f.ZERO);

        otherList.addListOption("Level 4", (() -> System.out.println("Other list")));

        otherList.addListOption("Level 5", (() -> System.out.println("Other list")));

        otherList.addListOption("Level 6", (() -> System.out.println("Other list")));

        InterfaceList anotherList = new InterfaceList(game, fontManager.get("font"), Vector2f.ZERO);

        anotherList.addListOption("Level 7", (() -> System.out.println("Other list")));

        anotherList.addListOption("Level 8", (() -> System.out.println("Other list")));

        anotherList.addListOption("Level 9", (() -> System.out.println("Other list")));

        FloatRect viewBounds = viewSize.getViewBounds();

        interfaceGrid = new InterfaceGrid(game, new Vector2f(viewBounds.width / 4 + 50, viewBounds.height / 3));

        interfaceGrid.addColumn(interfaceList);

        interfaceGrid.addColumn(otherList);

        interfaceGrid.addColumn(anotherList);
    }

    @Override
    public void update(Time deltaTime) {
        interfaceGrid.update();
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(background);

        target.draw(interfaceGrid);
    }

    @Override
    public boolean complete() {
        return false;
    }
}
