package com.lauerbach.samples.webserviceclient;

import javax.xml.ws.BindingProvider;

import com.lauerbach.samples.webserviceclient.service.SimpleEjbWebService;
import com.lauerbach.samples.webserviceclient.service.SimpleEjbWebServiceImplService;

public class SimpleEjbWebServcieClient {

	public static void main(String[] args) {
		SimpleEjbWebServiceImplService svc= new SimpleEjbWebServiceImplService();
		SimpleEjbWebService port = svc.getSimpleEjbWebServiceImplPort();
		((BindingProvider)port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "test");
		((BindingProvider)port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "test");
		
		System.out.println( port.hallo("ghdlkfkjd"));
	}

}
