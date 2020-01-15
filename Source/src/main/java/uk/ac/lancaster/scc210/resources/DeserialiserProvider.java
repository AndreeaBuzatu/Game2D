package uk.ac.lancaster.scc210.resources;

import uk.ac.lancaster.scc210.resources.deserialise.SpaceShipDeserialiser;
import uk.ac.lancaster.scc210.resources.deserialise.TextureAtlasDeserialiser;

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
