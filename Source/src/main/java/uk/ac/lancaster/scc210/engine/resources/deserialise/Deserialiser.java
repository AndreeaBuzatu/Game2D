package uk.ac.lancaster.scc210.engine.resources.deserialise;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Deserialiser.
 *
 * @param <T> the type parameter
 */
public abstract class Deserialiser<T extends Serialised> {
    private final String tagName;

    /**
     * The Serialised.
     */
    protected final List<T> serialised;

    /**
     * The Document.
     */
    protected final Document document;

    /**
     * The Nodes.
     */
    protected NodeList nodes;

    /**
     * Instantiates a new Deserialiser.
     *
     * @param document the document
     * @param tagName  the tag name
     * @throws ResourceNotFoundException the resource not found exception
     */
    protected Deserialiser(Document document, String tagName) throws ResourceNotFoundException {
        this.document = document;
        this.tagName = tagName;

        serialised = new ArrayList<>();

        if (document != null) {
            nodes = document.getDocumentElement().getChildNodes();

            deserialise();
        }
    }

    /**
     * Instantiates a new Deserialiser.
     *
     * @param document the document
     * @param nodes    the nodes
     * @param tagName  the tag name
     * @throws ResourceNotFoundException the resource not found exception
     */
    Deserialiser(Document document, NodeList nodes, String tagName) throws ResourceNotFoundException {
        this.document = document;
        this.nodes = nodes;
        this.tagName = tagName;

        serialised = new ArrayList<>();

        deserialise();
    }

    /**
     * Deserialise.
     *
     * @throws ResourceNotFoundException the resource not found exception
     */
    protected abstract void deserialise() throws ResourceNotFoundException;

    /**
     * Found node boolean.
     *
     * @param node the node
     * @return the boolean
     */
    protected boolean foundNode(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(tagName);
    }

    /**
     * Gets serialised.
     *
     * @return the serialised
     */
    public List<T> getSerialised() {
        return serialised;
    }
}
