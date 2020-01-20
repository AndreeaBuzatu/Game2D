package uk.ac.lancaster.scc210.engine.service;

import java.util.HashMap;

/**
 * The type Service provider.
 *
 * @param <T> the type parameter
 */
public class ServiceProvider<T extends Service> {
    private final HashMap<Class<? extends Service>, Service> services;

    /**
     * Instantiates a new Service provider.
     */
    public ServiceProvider() {
        services = new HashMap<>();
    }

    /**
     * Put.
     *
     * @param klass   the klass
     * @param service the service
     */
    public void put(Class<? extends Service> klass, Service service) {
        services.put(klass, service);
    }

    /**
     * Get service.
     *
     * @param klass the klass
     * @return the service
     */
    public Service get(final Class<? extends Service> klass) {
        return services.get(klass);
    }
}
