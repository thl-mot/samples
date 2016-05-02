package com.lauerbach.samples.wildflyInjection;
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

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote( SimpleInjectingService.class)
public class SimpleInjectingServiceImpl implements SimpleInjectingService {

	@Override
	public void runAfterInjection(String className) {
		try {
			Class<?> c;
			c = Class.forName(className);
			InjectionCall obj = (InjectionCall) c.newInstance();
			BeanManagerHelper.inject(obj);
			obj.run();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
