package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.ResourceLoader;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.resources.XMLAdapter;
import uk.ac.lancaster.scc210.engine.resources.deserialise.TextureAtlasDeserialiser;

public class DeserialiserProvider {
    private TextureAtlasDeserialiser textureAtlasDeserialiser;

    private SpaceShipDeserialiser spaceShipDeserialiser;

    public DeserialiserProvider() throws ResourceNotFoundException {
        XMLAdapter xmlAdapter = new XMLAdapter();

        ResourceLoader.loadFromFile(xmlAdapter, "textures.xml");

        textureAtlasDeserialiser = new TextureAtlasDeserialiser(xmlAdapter.getResource());

        ResourceLoader.loadFromFile(xmlAdapter, "spaceships.xml");

        spaceShipDeserialiser = new SpaceShipDeserialiser(xmlAdapter.getResource());
    }

    public TextureAtlasDeserialiser getTextureAtlasDeserialiser() {
        return textureAtlasDeserialiser;
    }

    public SpaceShipDeserialiser getSpaceShipDeserialiser() {
        return spaceShipDeserialiser;
    }
}
