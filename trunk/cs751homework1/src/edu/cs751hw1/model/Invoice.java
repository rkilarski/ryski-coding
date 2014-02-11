/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN
 * OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
 * FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR
 * PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF
 * LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that Software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of
 * any nuclear facility.
 */

package edu.cs751hw1.model;
import java.text.NumberFormat;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class implements the business logic for the sample.
 */
public class Invoice {

  /**
   * DOM Document
   */
  private Document doc;
  /**
   * Tax rate
   */
  private final double TAX = 8.25;
  /**
   * Discount rate
   */
  private final int DISCOUNT = 10;
  /**
   * Discount limit
   */
  private final int DISCOUNT_PRICE = 100;
  /**
   * Order price
   */
  private double orderPrice;
  /**
   * Order Tax
   */
  private double orderTax;
  /**
   * Order total
   */
  private double orderTotal;

  /**
   * Constructor
   * @param doc - Order Document
   */
  public Invoice(Document doc) {
    this.doc = doc;
    reCalculateOrder();
  }

  public Invoice(Order order) {
 
    }
  /**
   * Re calculate Order - price, tax and total
   */
  private void reCalculateOrder(){
    orderPrice = countPrice();
    orderTax = calculateTax();
    orderTotal = calculateOrder();
  }

  /**
   * Calculate total amount for element PRICE
   */
  private float countPrice(){
    float price = 0;
    String strValue = "";
    float floatValue = 0;
    final String tag = "PRICE";
    NodeList list = doc.getElementsByTagName(tag);

    for (int i = 0; i < list.getLength(); i++){
      Node element = list.item(i);
      NodeList elementsList = element.getChildNodes();
      for (int j = 0; j < elementsList.getLength(); j++){
        Node attribute = elementsList.item(j);
        if ( (Node.TEXT_NODE == attribute.getNodeType()) &&
             (attribute.getNodeValue() != null) )
          strValue = attribute.getNodeValue().trim();
      }

      try{
        floatValue = NumberFormat.getCurrencyInstance().parse(strValue).floatValue();
      } catch(java.text.ParseException e) {
        System.out.println("Can't parse the PRICE element - " + e);
      }

      price += floatValue;
    }

    return price;
  }

  /**
   * Calculate amount for element PRICE
   * @return Price
   */
  public String getOrderPrice(){
    return NumberFormat.getCurrencyInstance().format(orderPrice);
  }

  /**
   * Calculate the Tax
   * @return Tax
   */
  private double calculateTax(){
    double theTax = orderPrice * TAX / 100;
    return theTax;
  }

  /**
   * Returns Order's Tax
   * @return Tax
   */
  public String getOrderTax(){
    return NumberFormat.getCurrencyInstance().format(orderTax);
  }

  /**
   * Calculate the Order Total
   * @return Total
   */
  private double calculateOrder(){
    return orderTax + orderPrice;
  }

  /**
   * Returns Order Total
   * @return Total
   */
  public String getOrderTotal(){
    return NumberFormat.getCurrencyInstance().format(orderTotal);
  }

  /**
   * Create and append TAX and TOTAL elements
   */
  public void AddTaxAndTotal(){
    // Add Tax Element to Manifest
    // Get Manifest Element
    Node manifest = doc.getElementsByTagName("Manifest").item(0);
    // Create TAX Element
    Node taxElement = doc.createElement("TAX");
    // Insert TAX Element
    manifest.appendChild(taxElement);
    // Insert TAX value
    Node value = doc.createTextNode(getOrderTax());
    taxElement.appendChild(value);

    // Add Total Element to Manifest
    // Create TOTAL Element
    Node totalElement = doc.createElement("TOTAL");
    // Insert TOTAL Element
    manifest.appendChild(totalElement);
    // Insert TOTAL value
    value = doc.createTextNode(getOrderPrice());
    totalElement.appendChild(value);
  }

  /**
   * Calculate and apply discount
   */
  public void applyDiscount(){
    String strValue = "";
    float floatValue = 0;
    final String tag = "PRICE";
    NodeList list = doc.getElementsByTagName(tag);

    for (int i = 0; i < list.getLength(); i++){
      Node element = list.item(i);
      NodeList elementsList = element.getChildNodes();
      Node attribute = null;
      for (int j = 0; j < elementsList.getLength(); j++){
        attribute = elementsList.item(j);
        if ( (Node.TEXT_NODE == attribute.getNodeType()) &&
             (attribute.getNodeValue() != null) ){
          strValue = attribute.getNodeValue().trim();
          break;
        }
      }
      try{
        floatValue = NumberFormat.getCurrencyInstance().parse(strValue).floatValue();
      } catch(java.text.ParseException e) {
        System.out.println("Can't parse the PRICE element - " + e);
      }
      floatValue = floatValue - floatValue * DISCOUNT / 100;
      if ( null != attribute){
        strValue = NumberFormat.getCurrencyInstance().format(floatValue);
        attribute.setNodeValue(strValue);
      }
    }
    reCalculateOrder();
  }

  /**
   * Check for Discount
   * @return true for Discount and false otherwise
   */
  public boolean isDiscount(){
    if (orderPrice > DISCOUNT_PRICE)
      return true;
    else return false;
  }

  /**
   * Print the Order's Info
   */
  public void print(){
    System.out.println("Order price: " + getOrderPrice());
    System.out.println("Order tax: " + getOrderTax());
    System.out.println("Order total: " + getOrderTotal());
  }

}