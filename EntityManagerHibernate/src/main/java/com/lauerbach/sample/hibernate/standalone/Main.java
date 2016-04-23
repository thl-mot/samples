package com.lauerbach.sample.hibernate.standalone;
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

import javax.persistence.EntityManager;

import com.lauerbach.sample.hibernate.standalone.model.Test;
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

public class Main {

	public static void main(String[] args) {
		EntityManager em = HibernateHelper.getEntityManager();
		em.getTransaction().begin();
		Test t = em.find(Test.class, (long) 4);
		if (t == null) {
			t= new Test();
			t.setName("Just some content");
			em.persist( t);
			System.out.println("Saved to database: "+t.getName()+" with id "+t.getId());
		} else {
			System.out.println("From Database: "+t.getName()+" with id "+t.getId());
		}
		em.getTransaction().commit();
		em.close();
	}

}
