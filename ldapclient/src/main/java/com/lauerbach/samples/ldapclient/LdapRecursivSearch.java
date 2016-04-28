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

import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LdapRecursivSearch {

	public static void main(String[] args) {
		try {
			LdapUserGroupHelper helper= new LdapUserGroupHelper();
			DirContext ctx = helper.getContext();
			
			SearchControls controls = new SearchControls();
	        controls.setSearchScope( SearchControls.SUBTREE_SCOPE);
			
			String filter = "(uid=user*)";

	        NamingEnumeration<?> answer = ctx.search("dc=lauerbach,dc=com", filter, controls);

			while (answer.hasMore()) {
				SearchResult sr = (SearchResult) answer.next();
				System.out.println(">>>" + sr.getName());
				System.out.println("  uid:" + sr.getAttributes().get("uid").get(0));
				System.out.println("  dn:" + sr.getNameInNamespace());
			}
			
			ctx.close();
		}
		catch( Exception ex) {
			ex.printStackTrace();
		}
	}

}
