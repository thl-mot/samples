package com.lauerbach.samples.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SimpleEjbWebService {
	@WebMethod
	String hallo( String value);
}
