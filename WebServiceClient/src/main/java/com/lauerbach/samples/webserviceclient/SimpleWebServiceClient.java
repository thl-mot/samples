package com.lauerbach.samples.webserviceclient;

import java.io.File;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import com.lauerbach.samples.webserviceclient.service.SimpleWebService;

public class SimpleWebServiceClient {

	public static void main(String[] args) throws Exception {
		URL wsdlURL = new File("src/main/resources/wsdl/SimpleWebService.wsdl").toURL();
		QName qname = new QName("http://samples.lauerbach.com/", "SimpleWebServiceImplService");
		Service service = Service.create(wsdlURL, qname);
		SimpleWebService port = (SimpleWebService)service.getPort(SimpleWebService.class);
		 
		BindingProvider bp = (BindingProvider)port;
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "test");
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "test");
	
		System.out.println( port.hallo("ghdlkfkjd"));
	
	}
	
}
