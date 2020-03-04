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

/**
 * The type Uniform grid.
 */
public class UniformGrid implements Service {
    /**
     * The constant CELL_SIZE.
     */
    public static final int CELL_SIZE = 256;

    private final List<List<Cell>> grid;

    private final int numRows, numColumns;

    /**
     * Instantiates a new Uniform grid.
     *
     * @param viewSize the view size
     */
    public UniformGrid(ViewSize viewSize) {
        FloatRect viewBounds = viewSize.getViewBounds();

        grid = new ArrayList<>();

        // Round the numbers up so the entire view is covered
        numRows = (int) Math.ceil(viewBounds.height / CELL_SIZE);

        numColumns = (int) Math.ceil(viewBounds.width / CELL_SIZE);

        for (int row = 0; row < numRows; row++) {
            grid.add(new ArrayList<>());

            for (int column = 0; column < numColumns; column++) {
                grid.get(row).add(new Cell());
            }
        }
    }

    /**
     * Add entity.
     *
     * @param entity the entity
     */
    public void addEntity(Entity entity) {
        placeEntity(entity);
    }

    /**
     * Add entities.
     *
     * @param entities the entities
     */
    public void addEntities(Collection<? extends Entity> entities) {
        entities.forEach(this::addEntity);
    }

    /**
     * Remove entity.
     *
     * @param entity the entity
     */
    public void removeEntity(Entity entity) {
        grid.forEach(cells -> cells.forEach(cell -> cell.getEntities().remove(entity)));

        update();
    }

    /**
     * Gets grid.
     *
     * @return the grid
     */
    public List<List<Cell>> getGrid() {
        return grid;
    }

    /**
     * Update.
     */
    public void update() {
        for (List<Cell> cells : grid) {
            for (Cell cell : cells) {
                cell.getEntities().forEach(this::placeEntity);
            }
        }
    }

    /**
     * Clear.
     */
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

            //System.out.printf("Total: (%d, %d), Current: (%d, %d)\n", numRows, numColumns, row, column);

            // Don't allow out of bounds columns
            if (row > 0 && row < numRows && column > 0 && column < numColumns) {
                grid.get(row).get(column).addEntity(entity);
            }
        }
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        int sum = 0;

        for (List<Cell> cells : grid) {
            for (Cell cell : cells) {
                sum += cell.getEntities().size();
            }
        }

        return sum;
    }
}
