package uk.ac.lancaster.scc210.engine.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.engine.pooling.Pool;

/**
 * Component which indicates that an entity is contained within a pool
 */
public class PooledComponent implements Component {
    private final Class<? extends Pool> poolClass;

    /**
     * Instantiates a new Pooled component.
     *
     * @param poolClass the pool class
     */
    public PooledComponent(Class<? extends Pool> poolClass) {
        this.poolClass = poolClass;
    }

    /**
     * Gets pool class.
     *
     * @return the pool class
     */
    public Class<? extends Pool> getPoolClass() {
        return poolClass;
    }
}
