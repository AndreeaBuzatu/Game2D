package uk.ac.lancaster.scc210.engine.collision;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Entity> entities;

    private final RectangleShape bounds;

    private final FloatRect boundingBox;

    Cell(int row, int column) {
        bounds = new RectangleShape(new Vector2f(UniformGrid.CELL_SIZE, UniformGrid.CELL_SIZE));

        bounds.setPosition(column * UniformGrid.CELL_SIZE, row * UniformGrid.CELL_SIZE);

        boundingBox = bounds.getGlobalBounds();

        entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
}
