package uk.ac.lancaster.scc210.engine.collision;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cell {
    private final Set<Entity> entities;

    Cell() {
        entities = new HashSet<>();
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

    public Set<Entity> getEntities() {
        return entities;
    }
}
