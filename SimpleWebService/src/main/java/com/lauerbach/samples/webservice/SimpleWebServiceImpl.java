package com.lauerbach.samples.webservice;
/**
 * Copyright 2016 Thomas Lauerbach
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService( endpointInterface= "com.lauerbach.samples.SimpleWebService")
public class SimpleWebServiceImpl implements SimpleWebService {

	@Resource
	WebServiceContext wsContext;
	
	@Override
	@RolesAllowed( value={"role1"})
	public String hallo( String bla) {
		
		if (wsContext!=null) {
			return "ok (no WebServiceContext)";
		} else {
			if (wsContext.isUserInRole("role1")) {
				return "ok (role1)";
			}
		}
		return "ok";
	}
	
}
