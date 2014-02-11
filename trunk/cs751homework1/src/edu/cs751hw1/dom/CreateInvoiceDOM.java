package edu.cs751hw1.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.cs751hw1.model.Invoice;

public class CreateInvoiceDOM {
    /**
     * DOM Document
     */
    private Document document = null;

    public CreateInvoiceDOM(Invoice invoice) {
      DocumentBuilder builder = null;
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      try{
        builder = factory.newDocumentBuilder();
        document = builder.newDocument();
      } catch (ParserConfigurationException e) {
        e.printStackTrace();
      }

      // Insert Root Order
      Element root = (Element) document.createElement("inv:invoice");
      document.appendChild(root);

      // Insert child Manifest
      Node manifestChild = document.createElement("Manifest");
      root.appendChild(manifestChild);
      
      // Insert Items
      insertItem(document, manifestChild, "101", "Name one", "$29.99");
      insertItem(document, manifestChild, "108", "Name two", "$19.99");
      insertItem(document, manifestChild, "125", "Name three", "$39.99");
      insertItem(document, manifestChild, "143", "Name four", "$59.99");
      insertItem(document, manifestChild, "118", "Name five", "$99.99");

      // Normalizing the DOM
      document.getDocumentElement().normalize();
    }

    /**
     * @return Document
     */
    public Document getDocument(){
      return document;
    }

    /**
     * Insert "Item" to Document
     * @param document - Order Document
     * @param parent - Node where to insert a "Item"
     * @param id - Item's ID
     * @param name - Item's Name
     * @param price - Item's Price
     */
    private void insertItem(Document document, Node parent, String id, String name, String price) {
      // Insert child Item
      Node itemChild = document.createElement("Item");
      parent.appendChild(itemChild);

      // Insert child ID
      Node item = document.createElement("ID");
      itemChild.appendChild(item);
      // Insert ID value
      Node value = document.createTextNode(id);
      item.appendChild(value);

      // Insert child NAME
      item = document.createElement("NAME");
      itemChild.appendChild(item);
      // Insert NAME value
      value = document.createTextNode(name);
      item.appendChild(value);

      // Insert child PRICE
      item = document.createElement("PRICE");
      itemChild.appendChild(item);
      // Insert PRICE value
      value = document.createTextNode(price);
      item.appendChild(value);
    }
}
