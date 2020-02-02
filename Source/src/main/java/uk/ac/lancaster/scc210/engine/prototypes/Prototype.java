package uk.ac.lancaster.scc210.engine.prototypes;

import uk.ac.lancaster.scc210.engine.ecs.Entity;

/**
 * Prototypes are Entities which contain components but do not represent a particular Entity on screen.
 * In effect, they are objects which are created when create() is called.
 * See: https://gameprogrammingpatterns.com/prototype.html. Not all of this is relevant, but the basic concept applies
 */
public interface Prototype {
    /**
     * Create the entity (likely with a set of components).
     *
     * @return the newly created entity.
     */
    Entity create();
}
