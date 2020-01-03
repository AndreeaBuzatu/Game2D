package uk.ac.lancaster.scc210.resources;

import uk.ac.lancaster.scc210.content.SpaceShipManager;
import uk.ac.lancaster.scc210.content.TextureManager;
import uk.ac.lancaster.scc210.resources.deserialise.SpaceShipDeserialiser;
import uk.ac.lancaster.scc210.resources.deserialise.TextureAtlasDeserialiser;

public class ResourceProvider {
    private TextureManager textureManager;

    private SpaceShipManager spaceShipManager;

    private TextureAtlasDeserialiser textureAtlasDeserialiser;

    private SpaceShipDeserialiser spaceShipDeserialiser;

    public ResourceProvider() throws ResourceNotFoundException {
        XMLAdapter xmlAdapter = new XMLAdapter();

        ResourceLoader.loadFromFile(xmlAdapter, "textures.xml");

        textureAtlasDeserialiser = new TextureAtlasDeserialiser(xmlAdapter.getResource());

        textureManager = new TextureManager(textureAtlasDeserialiser.getSerialisedTextureAtlases());

        ResourceLoader.loadFromFile(xmlAdapter, "spaceships.xml");

        spaceShipDeserialiser = new SpaceShipDeserialiser(xmlAdapter.getResource());

        spaceShipManager = new SpaceShipManager(textureManager, spaceShipDeserialiser.getSerialisedSpaceShips());
    }

    public TextureManager getTextureManager() {
        return textureManager;
    }

    public SpaceShipManager getSpaceShipManager() {
        return spaceShipManager;
    }
}
