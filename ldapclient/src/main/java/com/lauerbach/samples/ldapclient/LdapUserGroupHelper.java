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

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapContext;

public class LdapUserGroupHelper {
	public static final String LDAP_USER= "cn=admin,dc=lauerbach,dc=com";
	public static final String LDAP_PASSWORD= "test";
	
	DirContext ctx;

	public LdapUserGroupHelper() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://192.168.1.200:389");

		// Authenticate as S. User and password "mysecret"
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, LDAP_USER);
		env.put(Context.SECURITY_CREDENTIALS, LDAP_PASSWORD);

		ctx = new InitialDirContext(env);
	}

	public void close() throws NamingException {
		ctx.close();
	}

	public void clearUsersAndGroups() {
		try {
			LdapContext usersCtx = (LdapContext) ctx.lookup("ou=users,dc=lauerbach,dc=com");
			System.out.println("Users:");
			NamingEnumeration<Binding> users = ctx.listBindings("ou=users,dc=lauerbach,dc=com");
			while (users.hasMore()) {
				Binding bd = (Binding) users.next();
				System.out.println(bd.getName() + ": " + bd.getObject());
				usersCtx.unbind( bd.getName());
			}
			usersCtx.close();

			LdapContext groupsCtx = (LdapContext) ctx.lookup("ou=groups,dc=lauerbach,dc=com");
			System.out.println("Groups:");
			NamingEnumeration<Binding> groups = ctx.listBindings("ou=groups,dc=lauerbach,dc=com");
			while (groups.hasMore()) {
				Binding bd = (Binding) groups.next();
				System.out.println(bd.getName() + ": " + bd.getObject());
				groupsCtx.unbind( bd.getName());
			}
			groupsCtx.close();
			
			LdapContext contactsCtx = (LdapContext) ctx.lookup("ou=contacts,dc=lauerbach,dc=com");
			System.out.println("Contacts:");
			NamingEnumeration<Binding> contacts = ctx.listBindings("ou=contacts,dc=lauerbach,dc=com");
			while (contacts.hasMore()) {
				Binding bd = (Binding) contacts.next();
				System.out.println(bd.getName() + ": " + bd.getObject());
				contactsCtx.unbind( bd.getName());
			}
			contactsCtx.close();

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void addGroup(String name) throws NamingException {
		LdapContext groupsCtx = (LdapContext) ctx.lookup("ou=groups,dc=lauerbach,dc=com");

		Attributes attrs = new BasicAttributes(true);
		Attribute objclass = new BasicAttribute("objectClass");
		objclass.add("groupOfNames");
		attrs.put(objclass);

		attrs.put(new BasicAttribute("member", ""));

		Context neu = groupsCtx.createSubcontext("cn=" + name, attrs);

		neu.close();
		groupsCtx.close();
	}

	public void addUser2Group( String uid, String groupName) throws NamingException {
		ModificationItem[] mods = new ModificationItem[1];
		mods[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE,
			    new BasicAttribute("member", "uid="+uid+",ou=users,dc=lauerbach,dc=com"));
		ctx.modifyAttributes( "cn="+groupName+",ou=groups,dc=lauerbach,dc=com", mods);
	}
	
	private byte[] encodePassword(String pass) throws UnsupportedEncodingException {
		final String ATT_ENCODING = "UTF-16LE";
		String pwd = "\"" + pass + "\"";
		byte bytes[] = pwd.getBytes(ATT_ENCODING);
		return bytes;
	}

	public void addUser(String name, String password) throws NamingException, UnsupportedEncodingException {
		LdapContext usersCtx = (LdapContext) ctx.lookup("ou=users,dc=lauerbach,dc=com");

		Attributes attrs = new BasicAttributes(true);
		Attribute objclass = new BasicAttribute("objectClass");
		objclass.add("account");
		objclass.add("simpleSecurityObject");
		attrs.put(objclass);

		attrs.put(new BasicAttribute("userPassword", encodePassword(password)));

		Context neu = usersCtx.createSubcontext("uid=" + name, attrs);

		neu.close();
		usersCtx.close();

	}

	public void addContact(String name) throws NamingException {
		LdapContext contactsCtx = (LdapContext) ctx.lookup("ou=contacts,dc=lauerbach,dc=com");

		Attributes attrs = new BasicAttributes(true);
		Attribute objclass = new BasicAttribute("objectClass");
		objclass.add("person");
		objclass.add("inetOrgPerson");
		attrs.put(objclass);

		// Person
		attrs.put(new BasicAttribute("sn", "sn "+name));
		attrs.put(new BasicAttribute("telephoneNumber", "0123 4567890"));
		attrs.put(new BasicAttribute("description", "description"));
		
		// inetOrgPerson
		attrs.put(new BasicAttribute("street", "street"));
		attrs.put(new BasicAttribute("givenName", "given name"));
		attrs.put(new BasicAttribute("homePhone", "home phone"));
		attrs.put(new BasicAttribute("mobile", "mobile phone"));
		attrs.put(new BasicAttribute("pager", "pager"));
		attrs.put(new BasicAttribute("mail", "maildomain"));
		
		// extra

		Context neu = contactsCtx.createSubcontext("cn=" + name, attrs);

		neu.close();
		contactsCtx.close();

	}
	

	public DirContext getContext() {
		return ctx;
	}

	
	
}
