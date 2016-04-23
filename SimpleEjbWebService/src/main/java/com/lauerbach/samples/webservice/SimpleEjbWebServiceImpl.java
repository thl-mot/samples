package com.lauerbach.samples.webservice;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.ws.api.annotation.WebContext;


@Stateless
@WebService
@SecurityDomain( value="energyUserDomain")
@DeclareRoles( { "role1","role2"})
@WebContext(authMethod="BASIC", transportGuarantee="NONE", secureWSDLAccess=false)
public class SimpleEjbWebServiceImpl implements SimpleEjbWebService {
	
	@Resource
	SessionContext sessionContext;
	
	@WebMethod
	@RolesAllowed( "role1")
	public String hallo(String value) {
		String caller="";
		if ( sessionContext!=null) {
			caller= sessionContext.getCallerPrincipal()+"";
		}
		return "ok ("+caller+")";
	}

}
