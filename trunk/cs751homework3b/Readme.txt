From the HW3_Sample folder window, generate the WSDL:
AXIS_HOME/bin/java2wsdl.sh  -wv 2.0 
    -o resources/ 
    -of test.wsdl 
    -sn OrderService 
    -cp build/classes/ 
    -cn edu.cs751hw3.service.OrderService
    
Copy test.wsdl to new.wsdl under resources folder

Edit the new.wsdl for getStudent
<wsdl2:operation ref="tns:getStudent" whttp:location="student/{name}"
			whttp:method="GET">
			
ant - generate.service
ant - run.client
			




