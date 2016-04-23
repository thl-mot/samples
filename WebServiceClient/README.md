#WebServiceClient

This is an exsample Webservice client for the exsample projects (SimpleWebService and SimpleEjbWebService) that implement the server side parts. Both services are secured by basic authentication.

The pom.xml shows two variants how to deal with the wsdl. Comment out the parts as shown below   

## use WSDL given by local files
        
    <wsdlDirectory>${basedir}/src/main/resources/wsdl</wsdlDirectory>
    				
    <!-- 
    <xauthFile>${basedir}/src/main/resources/authFile</xauthFile>
    <wsdlUrls> 
        <wsdlUrl>http://localhost:8080/SimpleWebService?wsdl</wsdlUrl> 
        <wsdlUrl>http://localhost:8080/EjbWebService/SimpleEjbWebServiceImpl?wsdl</wsdlUrl> 
    </wsdlUrls> 
    -->

## use WSDL from the service 
    
    <!--
    <wsdlDirectory>${basedir}/src/main/resources/wsdl</wsdlDirectory>
    -->
    				
    <xauthFile>${basedir}/src/main/resources/authFile</xauthFile>
    <wsdlUrls> 
        <wsdlUrl>http://localhost:8080/SimpleWebService?wsdl</wsdlUrl> 
        <wsdlUrl>http://localhost:8080/EjbWebService/SimpleEjbWebServiceImpl?wsdl</wsdlUrl> 
    </wsdlUrls> 

Depending on your server settings, you have to edit the file ``authFile`` and set the user and password you hav set in the login-module of the server.

    http://username:password@localhost:8080/SimpleWebService?wsdl



