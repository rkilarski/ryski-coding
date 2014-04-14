/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */

 This is Homework #4, creating a SOAP service for orders.
 
 edu.cs751homework4.model
 	These are my domain objects.
 	
 edu.cs751homework4.service
	This is my service definition.  All the services are in OrderServiceImpl.

 edu.cs751homework4.client
	This is the client that tests the services.
	
	
To run:
Use the build.xml file to deploy to an Axis server.

Note:  
I did not get the UpdateOrderHandler to work.  So far, I can either have all the other services work fine OR I can get Ant to generate an ObjectFactory class that (presumably) would be used in UpdateOrderHandler to unmarshall the message, but NOT BOTH.
The offending item in build.xml is:
		<!--
		<exec executable="${xjc_tool}">
			<arg value="-d" />
			<arg value="gen-wsdl" />
			<arg value="${basedir}/resources/META-INF/OrderServiceImplService_schema1.xsd" />
		</exec>
		-->

If I uncomment this, I get an ObjectdFactory, but then none of my services work.  If I remove it, my services all work fine, but UpdateOrderHandler doesn't work because I don't produce an ObjectFactory.  The same problem causes my exceptions to generate twice as well, causing me to have to not hook up the exceptions yet.

Additionally, the addOrder doesn't fully work, I'm not sure how to get the XmlElementWrapper functionality to work correctly to wrap the Items correctly in the XML.

Here is a sample run of the client tests.

     [java] **************************************Testing getOrders
     [java] Client : handleMessage : Outbound true
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Body><ns2:getOrders xmlns:ns2="http://service.cs751homework4.edu/" /></soapenv:Body></soapenv:Envelope>
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Header><clientId xmlns="http://edu.cs751homework4/" soapenv:actor="http://schemas.xmlsoap.org/soap/actor/next">100</clientId></soapenv:Header><soapenv:Body><ns2:getOrders xmlns:ns2="http://service.cs751homework4.edu/" /></soapenv:Body></soapenv:Envelope>
     [java] Client : getHeaders()......
     [java] Client : handleMessage : Outbound false
     [java] Client : close()......
     [java] order id: a1
     [java] name: Ryszard Kilarski
     [java] street: 881 Comm Ave
     [java] city/state/zip: Boston MA 02115
     [java] phone: 617-353-5000
     [java]  
     [java] items:
     [java]      Item 1 3.0 1
     [java]      Item 2 6.0 2
     [java]  
     [java] order id: a2
     [java] name: Suresh Kalathur
     [java] street: 975 Comm Ave
     [java] city/state/zip: Boston MA 02116
     [java] phone: 617-353-5001
     [java]  
     [java] items:
     [java]      Item 3 9.0 3
     [java]      Item 4 12.0 4
     [java]  
     [java] **************************************End getOrders
     [java]  
     [java] **************************************Testing getOrder
     [java] Client : handleMessage : Outbound true
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Body><ns2:getOrder xmlns:ns2="http://service.cs751homework4.edu/"><arg0>a1</arg0></ns2:getOrder></soapenv:Body></soapenv:Envelope>
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Header><clientId xmlns="http://edu.cs751homework4/" soapenv:actor="http://schemas.xmlsoap.org/soap/actor/next">100</clientId></soapenv:Header><soapenv:Body><ns2:getOrder xmlns:ns2="http://service.cs751homework4.edu/"><arg0>a1</arg0></ns2:getOrder></soapenv:Body></soapenv:Envelope>
     [java] Client : getHeaders()......
     [java] Client : handleMessage : Outbound false
     [java] Client : close()......
     [java] order id: a1
     [java] name: Ryszard Kilarski
     [java] street: 881 Comm Ave
     [java] city/state/zip: Boston MA 02115
     [java] phone: 617-353-5000
     [java]  
     [java] items:
     [java]      Item 1 3.0 1
     [java]      Item 2 6.0 2
     [java]  
     [java] **************************************End getOrder
     [java]  
     [java] **************************************Testing addOrder
     [java] Client : handleMessage : Outbound true
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Body><ns2:addOrder xmlns:ns2="http://service.cs751homework4.edu/"><arg0><billTo><city>Boston</city><name>Sheldon Cooper</name><phone>617-353-5000</phone><state>CA</state><street>Les Robles Drive</street><zipCode>99999</zipCode></billTo><orderId>b3</orderId><orderItems><price>15.0</price><productName>Item 4</productName><quantity>5</quantity></orderItems><orderItems><price>18.0</price><productName>Item 5</productName><quantity>6</quantity></orderItems></arg0></ns2:addOrder></soapenv:Body></soapenv:Envelope>
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Header><clientId xmlns="http://edu.cs751homework4/" soapenv:actor="http://schemas.xmlsoap.org/soap/actor/next">100</clientId></soapenv:Header><soapenv:Body><ns2:addOrder xmlns:ns2="http://service.cs751homework4.edu/"><arg0><billTo><city>Boston</city><name>Sheldon Cooper</name><phone>617-353-5000</phone><state>CA</state><street>Les Robles Drive</street><zipCode>99999</zipCode></billTo><orderId>b3</orderId><orderItems><price>15.0</price><productName>Item 4</productName><quantity>5</quantity></orderItems><orderItems><price>18.0</price><productName>Item 5</productName><quantity>6</quantity></orderItems></arg0></ns2:addOrder></soapenv:Body></soapenv:Envelope>
     [java] Client : getHeaders()......
     [java] Client : handleMessage : Outbound false
     [java] Client : close()......
     [java] order b3 added.
     [java] Client : handleMessage : Outbound true
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Body><ns2:getOrder xmlns:ns2="http://service.cs751homework4.edu/"><arg0>b3</arg0></ns2:getOrder></soapenv:Body></soapenv:Envelope>
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Header><clientId xmlns="http://edu.cs751homework4/" soapenv:actor="http://schemas.xmlsoap.org/soap/actor/next">100</clientId></soapenv:Header><soapenv:Body><ns2:getOrder xmlns:ns2="http://service.cs751homework4.edu/"><arg0>b3</arg0></ns2:getOrder></soapenv:Body></soapenv:Envelope>
     [java] Client : getHeaders()......
     [java] Client : handleMessage : Outbound false
     [java] Client : close()......
     [java] order id: b3
     [java] name: Sheldon Cooper
     [java] street: Les Robles Drive
     [java] city/state/zip: Boston CA 99999
     [java] phone: 617-353-5000
     [java]  
     [java] items:
     [java]  
     [java] Client : handleMessage : Outbound true
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Body><ns2:getOrders xmlns:ns2="http://service.cs751homework4.edu/" /></soapenv:Body></soapenv:Envelope>
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Header><clientId xmlns="http://edu.cs751homework4/" soapenv:actor="http://schemas.xmlsoap.org/soap/actor/next">100</clientId></soapenv:Header><soapenv:Body><ns2:getOrders xmlns:ns2="http://service.cs751homework4.edu/" /></soapenv:Body></soapenv:Envelope>
     [java] Client : getHeaders()......
     [java] Client : handleMessage : Outbound false
     [java] Client : close()......
     [java] order id: b3
     [java] name: Sheldon Cooper
     [java] street: Les Robles Drive
     [java] city/state/zip: Boston CA 99999
     [java] phone: 617-353-5000
     [java]  
     [java] items:
     [java]  
     [java] order id: a1
     [java] name: Ryszard Kilarski
     [java] street: 881 Comm Ave
     [java] city/state/zip: Boston MA 02115
     [java] phone: 617-353-5000
     [java]  
     [java] items:
     [java]      Item 1 3.0 1
     [java]      Item 2 6.0 2
     [java]  
     [java] order id: a2
     [java] name: Suresh Kalathur
     [java] street: 975 Comm Ave
     [java] city/state/zip: Boston MA 02116
     [java] phone: 617-353-5001
     [java]  
     [java] items:
     [java]      Item 3 9.0 3
     [java]      Item 4 12.0 4
     [java]  
     [java] **************************************End addOrder
     [java]  
     [java] **************************************Testing updateOrder
     [java] Client : handleMessage : Outbound true
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Body><ns2:updateOrder xmlns:ns2="http://service.cs751homework4.edu/"><arg0>a2</arg0><arg1><price>28.99</price><productName>Item 3</productName><quantity>15</quantity></arg1></ns2:updateOrder></soapenv:Body></soapenv:Envelope>
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Header><clientId xmlns="http://edu.cs751homework4/" soapenv:actor="http://schemas.xmlsoap.org/soap/actor/next">100</clientId></soapenv:Header><soapenv:Body><ns2:updateOrder xmlns:ns2="http://service.cs751homework4.edu/"><arg0>a2</arg0><arg1><price>28.99</price><productName>Item 3</productName><quantity>15</quantity></arg1></ns2:updateOrder></soapenv:Body></soapenv:Envelope>
     [java] Client : getHeaders()......
     [java] Client : handleMessage : Outbound false
     [java] Client : close()......
     [java] order a2 updated.
     [java] Client : handleMessage : Outbound true
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Body><ns2:getOrder xmlns:ns2="http://service.cs751homework4.edu/"><arg0>a2</arg0></ns2:getOrder></soapenv:Body></soapenv:Envelope>
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Header><clientId xmlns="http://edu.cs751homework4/" soapenv:actor="http://schemas.xmlsoap.org/soap/actor/next">100</clientId></soapenv:Header><soapenv:Body><ns2:getOrder xmlns:ns2="http://service.cs751homework4.edu/"><arg0>a2</arg0></ns2:getOrder></soapenv:Body></soapenv:Envelope>
     [java] Client : getHeaders()......
     [java] Client : handleMessage : Outbound false
     [java] Client : close()......
     [java] order id: a2
     [java] name: Suresh Kalathur
     [java] street: 975 Comm Ave
     [java] city/state/zip: Boston MA 02116
     [java] phone: 617-353-5001
     [java]  
     [java] items:
     [java]      Item 3 28.99 15
     [java]      Item 4 12.0 4
     [java]  
     [java] **************************************End updateOrder
     [java]  
     [java] **************************************Testing deleteOrder
     [java] Client : handleMessage : Outbound true
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Body><ns2:deleteOrder xmlns:ns2="http://service.cs751homework4.edu/"><arg0>a1</arg0></ns2:deleteOrder></soapenv:Body></soapenv:Envelope>
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Header><clientId xmlns="http://edu.cs751homework4/" soapenv:actor="http://schemas.xmlsoap.org/soap/actor/next">100</clientId></soapenv:Header><soapenv:Body><ns2:deleteOrder xmlns:ns2="http://service.cs751homework4.edu/"><arg0>a1</arg0></ns2:deleteOrder></soapenv:Body></soapenv:Envelope>
     [java] Client : getHeaders()......
     [java] Client : handleMessage : Outbound false
     [java] Client : close()......
     [java] order a1 deleted.
     [java] Client : handleMessage : Outbound true
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Body><ns2:getOrders xmlns:ns2="http://service.cs751homework4.edu/" /></soapenv:Body></soapenv:Envelope>
     [java] <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"><soapenv:Header><clientId xmlns="http://edu.cs751homework4/" soapenv:actor="http://schemas.xmlsoap.org/soap/actor/next">100</clientId></soapenv:Header><soapenv:Body><ns2:getOrders xmlns:ns2="http://service.cs751homework4.edu/" /></soapenv:Body></soapenv:Envelope>
     [java] Client : getHeaders()......
     [java] Client : handleMessage : Outbound false
     [java] Client : close()......
     [java] order id: b3
     [java] name: Sheldon Cooper
     [java] street: Les Robles Drive
     [java] city/state/zip: Boston CA 99999
     [java] phone: 617-353-5000
     [java]  
     [java] items:
     [java]  
     [java] order id: a2
     [java] name: Suresh Kalathur
     [java] street: 975 Comm Ave
     [java] city/state/zip: Boston MA 02116
     [java] phone: 617-353-5001
     [java]  
     [java] items:
     [java]      Item 3 28.99 15
     [java]      Item 4 12.0 4
     [java]  
     [java] **************************************End deleteOrder
BUILD SUCCESSFUL
Total time: 10 seconds
