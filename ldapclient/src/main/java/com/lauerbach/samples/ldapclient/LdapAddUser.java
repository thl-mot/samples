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

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.LdapContext;

public class LdapAddUser {

	private static byte[] encodePassword(String pass) throws UnsupportedEncodingException {
		final String ATT_ENCODING = "UTF-16LE";
		String pwd = "\"" + pass + "\"";
		byte bytes[] = pwd.getBytes(ATT_ENCODING);
		return bytes;
	}

	public static void main(String[] args) {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://192.168.1.200:389");

		// Authenticate as S. User and password "mysecret"
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=lauerbach,dc=com");
		env.put(Context.SECURITY_CREDENTIALS, "test");

		// Create the initial context
		try {
			DirContext ctx = new InitialDirContext(env);

			LdapContext usersCtx = (LdapContext) ctx.lookup("ou=users,dc=lauerbach,dc=com");

			Attributes attrs = new BasicAttributes(true);
			Attribute objclass = new BasicAttribute("objectClass");
			objclass.add("account");
			objclass.add("simpleSecurityObject");
			attrs.put(objclass);

			attrs.put(new BasicAttribute("userPassword", encodePassword("test")));
			// attrs.put(new BasicAttribute("", ""));

			Context neu = usersCtx.createSubcontext("uid=user7", attrs);

			usersCtx.close();
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
