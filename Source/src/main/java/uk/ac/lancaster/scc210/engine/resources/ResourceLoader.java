package uk.ac.lancaster.scc210.engine.resources;

import uk.ac.lancaster.scc210.game.Game;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

/**
 * Contains the functionality and error handling for loading a resource
 */
public class ResourceLoader {
    private static final String ERROR_TITLE = "Unable to load resource";

    /**
     * Given a resource, load it. If the resource fails to load, show an error message in the box
     *
     * @param <T>      the type parameter
     * @param resource the resource
     * @param fileName the file name
     * @throws ResourceNotFoundException the resource not found exception
     */
    public static<T extends Resource> void loadFromFile(T resource, String fileName) throws ResourceNotFoundException {
        Path path = loadFromRes(fileName);

        try {
            resource.loadFromFile(path);

            Game.LOGGER.log(Level.INFO, String.format("[%s] Loading %s", resource.getClass().getName(), path));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, String.format("Unable to load %s", path), ERROR_TITLE, JOptionPane.ERROR_MESSAGE);

            throw new ResourceNotFoundException(fileName);
        }
    }

    private static Path loadFromRes(String fileName) {
        return Paths.get("src", "main", "resources", fileName);
    }
}
