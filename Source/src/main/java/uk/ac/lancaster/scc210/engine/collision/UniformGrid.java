package uk.ac.lancaster.scc210.engine.collision;

import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.service.Service;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UniformGrid implements Service {
    public static final int CELL_SIZE = 128;

    private final List<List<Cell>> grid;

    private final int numRows, numColumns;

    public UniformGrid(ViewSize viewSize) {
        FloatRect viewBounds = viewSize.getViewBounds();

        grid = new ArrayList<>();

        // Round the numbers up so the entire view is covered
        numRows = (int) Math.ceil(viewBounds.width / CELL_SIZE);

        numColumns = (int) Math.ceil(viewBounds.height / CELL_SIZE);

        for (int row = 0; row < numRows; row++) {
            grid.add(new ArrayList<>());

            for (int column = 0; column < numColumns; column++) {
                grid.get(row).add(new Cell());
            }
        }

        System.out.println(viewBounds);

        System.out.println(grid.get(0).size());
    }

    public void addEntity(Entity entity) {
        placeEntity(entity);
    }

    public void addEntities(Collection<? extends Entity> entities) {
        entities.forEach(this::addEntity);
    }

    public void removeEntity(Entity entity) {
        grid.forEach(cells -> cells.forEach(cell -> cell.getEntities().remove(entity)));
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void update() {
        for (List<Cell> cells : grid) {
            for (Cell cell : cells) {
                cell.getEntities().forEach(this::placeEntity);
            }
        }
    }

    public void clear() {
        for (List<Cell> cells : grid) {
            for (Cell cell : cells) {
                cell.clear();
            }
        }
    }

    private void placeEntity(Entity entity) {
        if (!entity.hasComponent(OrientatedBoxComponent.class)) {
            return;
        }

        OrientatedBoxComponent orientatedBoxComponent = (OrientatedBoxComponent) entity.findComponent(OrientatedBoxComponent.class);

        orientatedBoxComponent.getOrientatedBox().calculatePoints();

        Vector2f[] boxPoints = orientatedBoxComponent.getOrientatedBox().getPoints();

        for (Vector2f point : boxPoints) {
            int column = (int) Math.ceil((point.x - 0) / CELL_SIZE) - 1;

            int row = (int) Math.ceil((point.y - 0) / CELL_SIZE) - 1;

            // Don't allow out of bounds columns
            if (row > 0 && row < numRows && column > 0 && column < numColumns) {
                Cell cell = grid.get(row).get(column);

                if (!cell.getEntities().contains(entity)) {
                    grid.get(row).get(column).addEntity(entity);
                }
            }
        }
    }
}
