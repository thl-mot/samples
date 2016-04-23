package ldapclient;
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

import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lauerbach.samples.ldapclient.LdapUserGroupHelper;

public class LdapUsersGroupsTest {

	
	private static LdapUserGroupHelper helper= null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		helper= new LdapUserGroupHelper();
		helper.clearUsersAndGroups();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		helper.close();
	}

	@Test
	public void addUsers() {
		try {
			helper.addUser("user1", "pwd1");
			helper.addUser("user2", "pwd2");
			helper.addUser("user3", "pwd3");
			helper.addUser("user4", "pwd4");
			helper.addUser("user5", "pwd5");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addGroups() {
		try {
			helper.addGroup("group1");
			helper.addGroup("group2");
			helper.addGroup("group3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
