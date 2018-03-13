package com.robindrew.codegenerator.api.serializer.xml.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomNode {

	private final Node node;
	private final boolean root;
	private int childIndex = 0;

	public DomNode(Node node, boolean root) {
		this.node = node;
		this.root = root;
	}

	public String getName() {
		return node.getNodeName();
	}

	public boolean isEmpty() {
		return !node.hasChildNodes();
	}

	public boolean hasAttribute(String name) {
		NamedNodeMap map = node.getAttributes();
		return map.getNamedItem(name) != null;
	}

	public String getAttribute(String name) {
		NamedNodeMap map = node.getAttributes();
		Node item = map.getNamedItem(name);
		return item.getNodeValue();
	}

	public String getValue() {
		String value;
		if (node.getChildNodes().getLength() == 1) {
			Node child = node.getChildNodes().item(0);
			if (child.getNodeType() != Node.TEXT_NODE) {
				throw new IllegalStateException("Expected text node: " + child + " in " + node);
			}
			value = child.getNodeValue();
		} else {
			value = node.getNodeValue();
		}
		return value;
	}

	public DomNode nextChild(String name) {
		if (root) {
			if (!getName().equals(name)) {
				throw new IllegalArgumentException("expected <" + name + ">, found <" + getName() + ">");
			}
			return new DomNode(node, false);
		}

		NodeList children = node.getChildNodes();

		// Skip text nodes
		Node child = null;
		while (childIndex < children.getLength()) {
			child = children.item(childIndex++);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				break;
			}
		}

		DomNode childNode = new DomNode(child, false);
		if (!childNode.getName().equals(name)) {
			throw new IllegalArgumentException("expected <" + name + ">, found <" + childNode.getName() + ">");
		}
		return childNode;
	}

}
