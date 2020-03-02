package uk.ac.lancaster.scc210.engine.collision;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Entity> entities;

    Cell() {
        entities = new ArrayList<>();
    }

    public List<Entity[]> checkCollision() {
        List<Entity[]> collisions = new ArrayList<>();

        for (Entity outer : entities) {
            OrientatedBoxComponent outerOrientatedBoxComponent = (OrientatedBoxComponent) outer.findComponent(OrientatedBoxComponent.class);

            for (Entity inner : entities) {
                if (outer == inner) {
                    continue;
                }

                OrientatedBoxComponent innerOrientatedBoxComponent = (OrientatedBoxComponent) inner.findComponent(OrientatedBoxComponent.class);

                if (OrientatedBox.areColliding(outerOrientatedBoxComponent.getOrientatedBox(), innerOrientatedBoxComponent.getOrientatedBox())) {
                    //System.out.println("Are colliding: " + System.currentTimeMillis());

                    collisions.add(new Entity[]{outer, inner});
                }
            }
        }

        return collisions;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void clear() {
        entities.clear();
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
