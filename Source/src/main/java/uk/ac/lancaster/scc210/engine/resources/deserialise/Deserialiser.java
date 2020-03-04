package uk.ac.lancaster.scc210.engine.resources.deserialise;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Deserialiser object. Used to take an Object and create a Java Object.
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
    protected Document document;

    /**
     * The Nodes.
     */
    protected NodeList nodes;

    /**
     * Instantiates a new Deserialiser.
     *
     * @param document the xml document
     * @param tagName  the global tag name
     * @throws ResourceNotFoundException if the resource cannot be created or found
     */
    protected Deserialiser(Document document, String tagName) throws ResourceNotFoundException {
        this.document = document;
        this.tagName = tagName;

        serialised = new ArrayList<>();

        if (document == null) {
            createStandinXML();
        }

        if (document != null) {
            nodes = document.getDocumentElement().getChildNodes();

            deserialise();
        }
    }

    /**
     * Instantiates a new Deserialiser.
     *
     * @param document        the xml document
     * @param nodeListTagName the child nodeListTagName used to deserialise
     * @param tagName         the global tag name
     * @throws ResourceNotFoundException if the resource cannot be created or found
     */
    protected Deserialiser(Document document, String nodeListTagName, String tagName) throws ResourceNotFoundException {
        this.document = document;
        this.tagName = tagName;

        serialised = new ArrayList<>();

        if (document != null) {
            this.nodes = document.getElementsByTagName(nodeListTagName);

            deserialise();
        }

    }

    /**
     * Deserialise the given XML document into a Java Object.
     *
     * @throws ResourceNotFoundException the resource cannot be created
     */
    protected abstract void deserialise() throws ResourceNotFoundException;

    /**
     * Given a Node attempt to see if the node == tagName.
     *
     * @param node the node which to be found
     * @return if the node is found or not
     */
    protected boolean foundNode(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(tagName);
    }

    /**
     * Given a Node attempt to see if the node == tagName.
     *
     * @param node    the node which to be found
     * @param tagName tagName to search for
     * @return if the node is found or not
     */
    protected boolean foundNode(Node node, String tagName) {
        return node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(tagName);
    }

    /**
     * Create standin xml.
     *
     * @throws ResourceNotFoundException the resource not found exception
     */
    protected void createStandinXML() throws ResourceNotFoundException {
    }

    /**
     * Gets serialised.
     *
     * @return serialised serialised
     */
    public List<T> getSerialised() {
        return serialised;
    }

    /**
     * Gets document.
     *
     * @return the document
     */
    public Document getDocument() {
        return document;
    }
}
