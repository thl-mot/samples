package com.lauerbach.samples.ldapclient;
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

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LdapSearchByFilter {

	public static void main(String[] args) {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://192.168.1.200:389");

		// Authenticate as S. User and password "mysecret"
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=lauerbach,dc=com");
		env.put(Context.SECURITY_CREDENTIALS, "test");

//		env.put(Context.SECURITY_PRINCIPAL, "uid=user1,ou=users,dc=lauerbach,dc=com");
//		env.put(Context.SECURITY_CREDENTIALS, "user1");
		
		// Create the initial context
		try {
			DirContext ctx = new InitialDirContext(env);

			SearchControls ctls = new SearchControls();
			// String filter = "(&(uid=user*)(mail=*))";
			String filter = "(uid=user*)";
			NamingEnumeration answer = ctx.search("ou=users,dc=lauerbach,dc=com", filter, ctls);

			while (answer.hasMore()) {
				SearchResult sr = (SearchResult) answer.next();
				System.out.println(">>>" + sr.getName());
				System.out.println("  uid:" + sr.getAttributes().get("uid"));
				System.out.println("  dn:" + sr.getNameInNamespace());
			}

			ctx.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
