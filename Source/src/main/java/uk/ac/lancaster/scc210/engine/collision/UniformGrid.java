package uk.ac.lancaster.scc210.engine.collision;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.TransformableComponent;

import java.util.ArrayList;
import java.util.List;

public class UniformGrid {
    public static final int CELL_SIZE = 128;

    private final List<List<Cell>> cells;

    public UniformGrid(ViewSize viewSize) {
        FloatRect viewBounds = viewSize.getViewBounds();

        cells = new ArrayList<>();

        // Round the numbers up so the entire view is covered
        int numRows = (int) Math.ceil(viewBounds.width / CELL_SIZE);

        int numColumns = (int) Math.ceil(viewBounds.height / CELL_SIZE);

        for (int row = 0; row < numRows; row++) {
            cells.add(new ArrayList<>());

            for (int column = 0; column < numColumns; column++) {
                cells.get(row).add(new Cell(row, column));
            }
        }

        System.out.println(viewBounds);

        System.out.println(cells.get(0).size());
    }

    public void addEntity(Entity entity) {
        if (!entity.hasComponent(TransformableComponent.class)) {
            return;
        }

        TransformableComponent transformableComponent = (TransformableComponent) entity.findComponent(TransformableComponent.class);

        Vector2f pos = transformableComponent.getTransformable().getPosition();

        int column = (int) Math.ceil(pos.x / CELL_SIZE);

        int row = (int) Math.ceil(pos.y / CELL_SIZE);

        cells.get(column).get(row).addEntity(entity);
    }
}
