package uk.ac.lancaster.scc210.engine.service;

import java.util.HashMap;

public class ServiceProvider<T extends Service> {
    private final HashMap<Class<? extends Service>, Service> services;

    public ServiceProvider() {
        services = new HashMap<>();
    }

    public void put(Class<? extends Service> klass, Service service) {
        services.put(klass, service);
    }

    public Service get(final Class<? extends Service> klass) {
        return services.get(klass);
    }
}
