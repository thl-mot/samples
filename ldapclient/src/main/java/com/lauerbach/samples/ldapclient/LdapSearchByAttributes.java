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
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchResult;

public class LdapSearchByAttributes {

	public static void main(String[] args) {
		try {
			LdapUserGroupHelper helper= new LdapUserGroupHelper();
			DirContext ctx = helper.getContext();
			
			Attributes matchAttrs = new BasicAttributes(true); 
			matchAttrs.put(new BasicAttribute("uid", "user1"));
			NamingEnumeration answer = ctx.search("ou=users,dc=lauerbach,dc=com", matchAttrs);

			while (answer.hasMore()) {
			    SearchResult sr = (SearchResult)answer.next();
			    System.out.println(">>>" + sr.getName());
			    System.out.println("  uid:"+sr.getAttributes().get("uid"));
			    System.out.println("  dn:"+sr.getNameInNamespace());
			}
			
			ctx.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
