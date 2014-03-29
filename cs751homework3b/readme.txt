/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */

 This is Homework #3, creating an Axis2 REST service for orders.
 
 edu.cs751hw3.model
 	These are my domain objects.
 	
 edu.cs751hw3.service
	This is my service definition.  All the services are in OrderResource.

 edu.cs751hw3.client
 	This is the client class that tests the services.
	
To run:
Use the build.xml file to deploy to an Axis2 server.

Here is a sample run of the tests.

     [java] **************************************Testing getOrders
     [java] ==========================Running:  getOrders==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:getOrders xmlns:ns="http://service.cs751hw3.edu">
     [java] </ns:getOrders>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getOrdersResponse xmlns:ns="http://service.cs751hw3.edu"><ns:return>http://192.168.1.139:8080/axis2/services/OrderService/order/a1</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/OrderService/order/a2</ns:return></ns:getOrdersResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getOrdersResponse xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/OrderService/order/a1
     [java]  </ns:return>
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/OrderService/order/a2
     [java]  </ns:return>
     [java] </ns:getOrdersResponse>
     [java] -----------Pretty Response End-----------
     [java] **************************************End getOrders
     [java]  
     [java] **************************************Testing getOrder
     [java] ==========================Running:  getOrder a1==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:getOrder xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:id>
     [java]      a1
     [java]  </ns:id>
     [java] </ns:getOrder>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getOrderResponse xmlns:ns="http://service.cs751hw3.edu"><ns:return><ns:billTo><ns:city>Boston</ns:city><ns:name>Ryszard Kilarski</ns:name><ns:phone>617-353-5000</ns:phone><ns:state>MA</ns:state><ns:street>881 Comm Ave</ns:street><ns:zipCode>02115</ns:zipCode></ns:billTo><ns:orderId>a1</ns:orderId><ns:orderItems><ns:price>3.0</ns:price><ns:productName>Item 1</ns:productName><ns:quantity>1</ns:quantity></ns:orderItems><ns:orderItems><ns:price>6.0</ns:price><ns:productName>Item 2</ns:productName><ns:quantity>2</ns:quantity></ns:orderItems></ns:return></ns:getOrderResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getOrderResponse xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:return>
     [java]   <ns:billTo>
     [java]    <ns:city>
     [java]        Boston
     [java]    </ns:city>
     [java]    <ns:name>
     [java]        Ryszard Kilarski
     [java]    </ns:name>
     [java]    <ns:phone>
     [java]        617-353-5000
     [java]    </ns:phone>
     [java]    <ns:state>
     [java]        MA
     [java]    </ns:state>
     [java]    <ns:street>
     [java]        881 Comm Ave
     [java]    </ns:street>
     [java]    <ns:zipCode>
     [java]        02115
     [java]    </ns:zipCode>
     [java]   </ns:billTo>
     [java]   <ns:orderId>
     [java]       a1
     [java]   </ns:orderId>
     [java]   <ns:orderItems>
     [java]    <ns:price>
     [java]        3.0
     [java]    </ns:price>
     [java]    <ns:productName>
     [java]        Item 1
     [java]    </ns:productName>
     [java]    <ns:quantity>
     [java]        1
     [java]    </ns:quantity>
     [java]   </ns:orderItems>
     [java]   <ns:orderItems>
     [java]    <ns:price>
     [java]        6.0
     [java]    </ns:price>
     [java]    <ns:productName>
     [java]        Item 2
     [java]    </ns:productName>
     [java]    <ns:quantity>
     [java]        2
     [java]    </ns:quantity>
     [java]   </ns:orderItems>
     [java]  </ns:return>
     [java] </ns:getOrderResponse>
     [java] -----------Pretty Response End-----------
     [java] **************************************End getOrder
     [java]  
     [java] **************************************Testing addOrder
     [java] ==========================Running:  addOrder b3==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:addOrder xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:order>
     [java]   <ns:orderId>
     [java]       b3
     [java]   </ns:orderId>
     [java]   <ns:billTo>
     [java]    <ns:name>
     [java]        Sheldon Cooper
     [java]    </ns:name>
     [java]    <ns:street>
     [java]        Les Robles Drive
     [java]    </ns:street>
     [java]    <ns:city>
     [java]        Boston
     [java]    </ns:city>
     [java]    <ns:state>
     [java]        CA
     [java]    </ns:state>
     [java]    <ns:zipCode>
     [java]        99999
     [java]    </ns:zipCode>
     [java]    <ns:phone>
     [java]        617-353-5000
     [java]    </ns:phone>
     [java]   </ns:billTo>
     [java]   <ns:orderItems>
     [java]    <ns:item>
     [java]     <ns:productName>
     [java]         Item 4
     [java]     </ns:productName>
     [java]     <ns:quantity>
     [java]         5
     [java]     </ns:quantity>
     [java]     <ns:price>
     [java]         15.00
     [java]     </ns:price>
     [java]    </ns:item>
     [java]    <ns:item>
     [java]     <ns:productName>
     [java]         Item 5
     [java]     </ns:productName>
     [java]     <ns:quantity>
     [java]         6
     [java]     </ns:quantity>
     [java]     <ns:price>
     [java]         18.00
     [java]     </ns:price>
     [java]    </ns:item>
     [java]   </ns:orderItems>
     [java]  </ns:order>
     [java] </ns:addOrder>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:addOrderResponse xmlns:ns="http://service.cs751hw3.edu"><ns:return>http://192.168.1.139:8080/axis2/services/OrderService/order/b3</ns:return></ns:addOrderResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:addOrderResponse xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/OrderService/order/b3
     [java]  </ns:return>
     [java] </ns:addOrderResponse>
     [java] -----------Pretty Response End-----------
     [java] ==========================Running:  results of addOrder b3==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:getOrders xmlns:ns="http://service.cs751hw3.edu">
     [java] </ns:getOrders>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getOrdersResponse xmlns:ns="http://service.cs751hw3.edu"><ns:return>http://192.168.1.139:8080/axis2/services/OrderService/order/b3</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/OrderService/order/a1</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/OrderService/order/a2</ns:return></ns:getOrdersResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getOrdersResponse xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/OrderService/order/b3
     [java]  </ns:return>
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/OrderService/order/a1
     [java]  </ns:return>
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/OrderService/order/a2
     [java]  </ns:return>
     [java] </ns:getOrdersResponse>
     [java] -----------Pretty Response End-----------
     [java] ==========================Running:  getOrder b3==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:getOrder xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:id>
     [java]      b3
     [java]  </ns:id>
     [java] </ns:getOrder>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getOrderResponse xmlns:ns="http://service.cs751hw3.edu"><ns:return><ns:billTo><ns:city>Boston</ns:city><ns:name>Sheldon Cooper</ns:name><ns:phone>617-353-5000</ns:phone><ns:state>CA</ns:state><ns:street>Les Robles Drive</ns:street><ns:zipCode>99999</ns:zipCode></ns:billTo><ns:orderId>b3</ns:orderId><ns:orderItems><ns:item><ns:productName>Item 4</ns:productName><ns:quantity>5</ns:quantity><ns:price>15.00</ns:price></ns:item><ns:item><ns:productName>Item 5</ns:productName><ns:quantity>6</ns:quantity><ns:price>18.00</ns:price></ns:item></ns:orderItems></ns:return></ns:getOrderResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getOrderResponse xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:return>
     [java]   <ns:billTo>
     [java]    <ns:city>
     [java]        Boston
     [java]    </ns:city>
     [java]    <ns:name>
     [java]        Sheldon Cooper
     [java]    </ns:name>
     [java]    <ns:phone>
     [java]        617-353-5000
     [java]    </ns:phone>
     [java]    <ns:state>
     [java]        CA
     [java]    </ns:state>
     [java]    <ns:street>
     [java]        Les Robles Drive
     [java]    </ns:street>
     [java]    <ns:zipCode>
     [java]        99999
     [java]    </ns:zipCode>
     [java]   </ns:billTo>
     [java]   <ns:orderId>
     [java]       b3
     [java]   </ns:orderId>
     [java]   <ns:orderItems>
     [java]    <ns:item>
     [java]     <ns:productName>
     [java]         Item 4
     [java]     </ns:productName>
     [java]     <ns:quantity>
     [java]         5
     [java]     </ns:quantity>
     [java]     <ns:price>
     [java]         15.00
     [java]     </ns:price>
     [java]    </ns:item>
     [java]    <ns:item>
     [java]     <ns:productName>
     [java]         Item 5
     [java]     </ns:productName>
     [java]     <ns:quantity>
     [java]         6
     [java]     </ns:quantity>
     [java]     <ns:price>
     [java]         18.00
     [java]     </ns:price>
     [java]    </ns:item>
     [java]   </ns:orderItems>
     [java]  </ns:return>
     [java] </ns:getOrderResponse>
     [java] -----------Pretty Response End-----------
     [java] **************************************End addOrder
     [java]  
     [java] **************************************Testing updateOrder
     [java] ==========================Running:  updateOrder==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:updateOrder xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:id>
     [java]      a2
     [java]  </ns:id>
     [java]  <ns:item>
     [java]   <ns:productName>
     [java]       Item 3
     [java]   </ns:productName>
     [java]   <ns:quantity>
     [java]       15
     [java]   </ns:quantity>
     [java]   <ns:price>
     [java]       28.99
     [java]   </ns:price>
     [java]  </ns:item>
     [java] </ns:updateOrder>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:updateOrderResponse xmlns:ns="http://service.cs751hw3.edu"><ns:return>http://192.168.1.139:8080/axis2/services/OrderService/order/a2</ns:return></ns:updateOrderResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:updateOrderResponse xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/OrderService/order/a2
     [java]  </ns:return>
     [java] </ns:updateOrderResponse>
     [java] -----------Pretty Response End-----------
     [java] ==========================Running:  results of updateOrder a2==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:getOrder xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:id>
     [java]      a2
     [java]  </ns:id>
     [java] </ns:getOrder>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getOrderResponse xmlns:ns="http://service.cs751hw3.edu"><ns:return><ns:billTo><ns:city>Boston</ns:city><ns:name>Suresh Kalathur</ns:name><ns:phone>617-353-5001</ns:phone><ns:state>MA</ns:state><ns:street>975 Comm Ave</ns:street><ns:zipCode>02116</ns:zipCode></ns:billTo><ns:orderId>a2</ns:orderId><ns:orderItems><ns:price>28.99</ns:price><ns:productName>Item 3</ns:productName><ns:quantity>15</ns:quantity></ns:orderItems><ns:orderItems><ns:price>12.0</ns:price><ns:productName>Item 4</ns:productName><ns:quantity>4</ns:quantity></ns:orderItems></ns:return></ns:getOrderResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getOrderResponse xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:return>
     [java]   <ns:billTo>
     [java]    <ns:city>
     [java]        Boston
     [java]    </ns:city>
     [java]    <ns:name>
     [java]        Suresh Kalathur
     [java]    </ns:name>
     [java]    <ns:phone>
     [java]        617-353-5001
     [java]    </ns:phone>
     [java]    <ns:state>
     [java]        MA
     [java]    </ns:state>
     [java]    <ns:street>
     [java]        975 Comm Ave
     [java]    </ns:street>
     [java]    <ns:zipCode>
     [java]        02116
     [java]    </ns:zipCode>
     [java]   </ns:billTo>
     [java]   <ns:orderId>
     [java]       a2
     [java]   </ns:orderId>
     [java]   <ns:orderItems>
     [java]    <ns:price>
     [java]        28.99
     [java]    </ns:price>
     [java]    <ns:productName>
     [java]        Item 3
     [java]    </ns:productName>
     [java]    <ns:quantity>
     [java]        15
     [java]    </ns:quantity>
     [java]   </ns:orderItems>
     [java]   <ns:orderItems>
     [java]    <ns:price>
     [java]        12.0
     [java]    </ns:price>
     [java]    <ns:productName>
     [java]        Item 4
     [java]    </ns:productName>
     [java]    <ns:quantity>
     [java]        4
     [java]    </ns:quantity>
     [java]   </ns:orderItems>
     [java]  </ns:return>
     [java] </ns:getOrderResponse>
     [java] -----------Pretty Response End-----------
     [java] **************************************End updateOrder
     [java]  
     [java] **************************************Testing deleteOrder
     [java] ==========================Running:  deleteOrder==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:deleteOrder xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:id>
     [java]      a1
     [java]  </ns:id>
     [java] </ns:deleteOrder>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] ==========================Running:  results of deleteOrder a1==========================
     [java] -----------Pretty Request Start-----------
     [java] <ns:getOrders xmlns:ns="http://service.cs751hw3.edu">
     [java] </ns:getOrders>
     [java] -----------Pretty Request End-----------
     [java]  
     [java] -----------Raw Response Start-----------
     [java] <ns:getOrdersResponse xmlns:ns="http://service.cs751hw3.edu"><ns:return>http://192.168.1.139:8080/axis2/services/OrderService/order/b3</ns:return><ns:return>http://192.168.1.139:8080/axis2/services/OrderService/order/a2</ns:return></ns:getOrdersResponse>-----------Raw Response End-----------
     [java]  
     [java] -----------Pretty Response Start-----------
     [java] <ns:getOrdersResponse xmlns:ns="http://service.cs751hw3.edu">
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/OrderService/order/b3
     [java]  </ns:return>
     [java]  <ns:return>
     [java]      http://192.168.1.139:8080/axis2/services/OrderService/order/a2
     [java]  </ns:return>
     [java] </ns:getOrdersResponse>
     [java] -----------Pretty Response End-----------
     [java] **************************************End deleteOrder
