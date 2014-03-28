/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */

 This is Homework #3, creating a REST service for orders.
 
 edu.cs751hw3.model
 	These are my domain objects.
 	
 edu.cs751hw3.service
	This is my service definition.  All the services are in OrderResource.

 edu.cs751hw3.client
 	This is the client class that tests the services.
	
	
To run:
Use the build.xml file to deploy to an Axis2 server.

Here is a sample run of the tests.


==========================BEGIN Testing: addOrder b3==========================

Location: http://localhost:8888/cs751homework3/services/order/b3

==========================Testing: result of addOrder, b3==========================
Content-Type: */*;q=".2"
Results of getOrders:
[/order/b3, /order/a1, /order/a2]

Content-Type: application/xml
Results of getting order b3
<?xml version="1.0" ?>
 <order>
  <billTo>
   <city>
       Boston
   </city>
   <name>
       Sheldon Cooper
   </name>
   <phone>
       617-976-5000
   </phone>
   <state>
       CA
   </state>
   <zipCode>
       99999
   </zipCode>

  </billTo>
  <orderId>
      b3
  </orderId>
  <items>
   <item>
    <price>
        15.0
    </price>
    <productName>
        Item 4
    </productName>
    <quantity>
        5
    </quantity>

   </item>
   <item>
    <price>
        18.0
    </price>
    <productName>
        Item 5
    </productName>
    <quantity>
        6
    </quantity>

   </item>

  </items>

 </order>
 
==========================END Testing: addOrder==========================


==========================BEGIN Testing: updateOrder b3==========================

Location: http://localhost:8888/cs751homework3/services/order/b3

==========================Testing: results of updateOrder b3==========================

Content-Type: application/xml
Results of getting order b3
<?xml version="1.0" ?>
 <order>
  <billTo>
   <city>
       Boston
   </city>
   <name>
       Sheldon Cooper
   </name>
   <phone>
       617-976-5000
   </phone>
   <state>
       CA
   </state>
   <zipCode>
       99999
   </zipCode>

  </billTo>
  <orderId>
      b3
  </orderId>
  <items>
   <item>
    <price>
        28.99
    </price>
    <productName>
        Item 4
    </productName>
    <quantity>
        15
    </quantity>

   </item>
   <item>
    <price>
        18.0
    </price>
    <productName>
        Item 5
    </productName>
    <quantity>
        6
    </quantity>

   </item>

  </items>

 </order>

==========================END Testing: updateOrder b3==========================


==========================BEGIN Testing: getOrder a1==========================

Content-Type: application/xml
Results of getting order a1
<?xml version="1.0" ?>
 <order>
  <billTo>
   <city>
       Boston
   </city>
   <name>
       Ryszard Kilarski
   </name>
   <phone>
       617-353-5000
   </phone>
   <state>
       MA
   </state>
   <street>
       881 Comm Ave
   </street>
   <zipCode>
       02115
   </zipCode>

  </billTo>
  <orderId>
      a1
  </orderId>
  <items>
   <item>
    <price>
        3.0
    </price>
    <productName>
        Item 1
    </productName>
    <quantity>
        1
    </quantity>

   </item>
   <item>
    <price>
        6.0
    </price>
    <productName>
        Item 2
    </productName>
    <quantity>
        2
    </quantity>

   </item>

  </items>

 </order>

==========================END Testing: getOrder a1==========================


==========================BEGIN Testing: deleteOrder a2, before:==========================

Content-Type: */*;q=".2"
Results of getOrders:
[/order/b3, /order/a1, /order/a2]
Content-Type: null

==========================Results of deleteOrder deleting a2, after:==========================

Content-Type: */*;q=".2"
Results of getOrders:
[/order/b3, /order/a1]

==========================END Testing: deleteOrder==========================


==========================BEGIN Testing: getOrders==========================

Content-Type: */*;q=".2"
Results of getOrders:
[/order/b3, /order/a1]

==========================END Testing: getOrders==========================

