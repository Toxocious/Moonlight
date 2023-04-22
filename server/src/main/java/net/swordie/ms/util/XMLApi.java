package net.swordie.ms.util;

import net.swordie.ms.ServerConstants;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 11/17/2017.
 */
public class XMLApi {

    private Node root;

    public XMLApi(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    /**
     * Returns a list of Nodes containing all children of a given Node. Filters out all text elements.
     *
     * @param node The Node of which the children are requested.
     * @return The list of children nodes of <code>node</code>. An empty list if there are none.
     */
    public static List<Node> getAllChildren(Node node) {
        List<Node> result = new ArrayList<>();

        Node childNode = node.getFirstChild();
        while (childNode != null) {
            if (!childNode.getNodeName().contains("#text")) {
                result.add(childNode);
            }
            childNode = childNode.getNextSibling();
        }

        return result == null ? new ArrayList<>() : result;
    }

    /**
     * Gets all attributes in a String, String map.
     *
     * @param node The Node the attributes are requested for.
     * @return The attributes corresponding to <code>node</code>.
     */
    public static Map<String, String> getAttributes(Node node) {
        if (node == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        NamedNodeMap namedNodeMap = node.getAttributes();
        for (int i = 0; i < namedNodeMap.getLength(); i++) {
            Node n = namedNodeMap.item(i);
            result.put(n.getNodeName(), n.getNodeValue());
        }
        return result;
    }

    /**
     * Generates a parsed Document, given an XML file.
     *
     * @param file The file to parse.
     * @return The parsed Document.
     */
    public static Document getRoot(File file) {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = dBuilder.parse(file);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * Breadth first search for a child node of a given Node.
     *
     * @param node The node to search in.
     * @param name The name of the child.
     * @return The first occurrence of the name in the children of node, or null if there is none.
     */
    public static Node getFirstChildByNameBF(Node node, String name) {
        List<Node> nodes = getAllChildren(node);
        for (Node n : nodes) {
            Map<String, String> attrs = getAttributes(n);
            String nodeName = attrs.get("name");
            if (name.equals(nodeName)) {
                return n;
            }
        }
        for (Node n : nodes) {
            Node child = getFirstChildByNameBF(n, name);
            if (child != null) {
                return child;
            }
        }
        return null;
    }

    /**
     * Depth first search for a child node of a given Node.
     *
     * @param node The node to search in.
     * @param name The name of the child.
     * @return The first occurrence of the name in the children of node, or null if there is none.
     */
    public static Node getFirstChildByNameDF(Node node, String name) {
        List<Node> nodes = getAllChildren(node);
        for (Node n : nodes) {
            Map<String, String> attrs = getAttributes(n);
            String nodeName = attrs.get("name");
            if (name.equals(nodeName)) {
                return n;
            }
            for (Node n2 : getAllChildren(n)) {
                Node child = getFirstChildByNameDF(n2, name);
                if (child != null) {
                    return child;
                }
            }
        }
        return null;
    }

    /**
     * Grabs the Node from a specified path.
     *
     * @param xmlPath  The path to the xml file.
     * @param nodeName The name of the node to be returned.
     * @return The first occurrence of the given nodeName in the given xmlPath, or null if there is none.
     */
    public static Node getNodeByPath(String xmlPath, String nodeName) {
        File file = new File(ServerConstants.WZ_DIR + "/" + xmlPath + ".xml");
        Document doc = getRoot(file);
        Node node = getAllChildren(doc).get(0);

        return getFirstChildByNameBF(node, nodeName);
    }

    public static String getNamedAttribute(Node node, String name) {
        return getAttributes(node).get(name);
    }
}
