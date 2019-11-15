package uk.ac.lancaster.game;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.Texture;

import java.io.IOException;
import java.nio.file.Paths;

public class Assets {
    static Texture loadTexture(String fileName) {
        Texture texture = new Texture();

        // TODO: Add proper exception handling
        try {
            texture.loadFromFile(Paths.get("res", fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return texture;
    }

    public static Font loadFont(String fileName) {
        Font font = new Font();

        // TODO: Add proper exception handling
        try {
            font.loadFromFile(Paths.get("res", fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return font;
    }
}
