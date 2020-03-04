package uk.ac.lancaster.scc210.engine.service;

import java.util.HashMap;

/**
 * A manager for services. Wraps a HashMap around HashMap<Class<? extends Service>, Service>.
 * This is used to share objects around the program without directly sharing many instances and passing many parameters.
 */
public class ServiceProvider {
    private final HashMap<Class<? extends Service>, Service> services;

    /**
     * Instantiates a new Service provider.
     */
    public ServiceProvider() {
        services = new HashMap<>();
    }

    /**
     * Put a new service into the services.
     *
     * @param klass   the class of the Service to place into the ServiceProvider
     * @param service the instance of the Service to place into the ServiceProvider
     */
    public void put(Class<? extends Service> klass, Service service) {
        services.put(klass, service);
    }

    /**
     * Get a Service from the ServiceProvider.
     *
     * @param klass the class to find in the ServiceProvider
     * @return the Service found or null
     */
    public Service get(final Class<? extends Service> klass) {
        return services.get(klass);
    }
}
