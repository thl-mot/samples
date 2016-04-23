
package com.lauerbach.samples.webserviceclient.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "SimpleEjbWebService", targetNamespace = "http://webservice.samples.lauerbach.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SimpleEjbWebService {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hallo", targetNamespace = "http://webservice.samples.lauerbach.com/", className = "com.lauerbach.samples.webserviceclient.service.Hallo")
    @ResponseWrapper(localName = "halloResponse", targetNamespace = "http://webservice.samples.lauerbach.com/", className = "com.lauerbach.samples.webserviceclient.service.HalloResponse")
    public String hallo(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
