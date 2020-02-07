package uk.ac.lancaster.scc210.engine.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.engine.pooling.Pool;

/**
 * Component which indicates that an entity is contained within a pool
 */
public class PooledComponent implements Component {
    private final Class<? extends Pool> poolClass;

    public PooledComponent(Class<? extends Pool> poolClass) {
        this.poolClass = poolClass;
    }

    public Class<? extends Pool> getPoolClass() {
        return poolClass;
    }
}
