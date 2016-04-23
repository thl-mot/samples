#SimpleEjbWebService

This is an example of a WebService based on a Stateless Session Bean. It is secured by BASIC auth.
The client is part of another project "WebServiceClient" next to this one.

This project is created and tested to run on a wildfly 10.  

##Server configuration

###Definition of the security domain in standalone.xml

I'd like to use the Database module to handle authentication.

First part is to define the datasource (can also be configured with management interface).

```

    <datasource jta="true" jndi-name="java:/userDS" pool-name="userDS" enabled="true" use-ccm="true">
    	<connection-url>jdbc:mysql://localhost:3306/userDB</connection-url>
    	<driver-class>com.mysql.jdbc.Driver</driver-class>
    	<driver>mysql-connector-java-5.1.38.jar_com.mysql.jdbc.Driver_5_1</driver>
    	<security>
    		<user-name>jboss</user-name>
    		<password>jboss</password>
    	</security>
    	
    	<validation>
    		<valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
    		<background-validation>true</background-validation>
    		<exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
    	</validation>
    </datasource>
    
```

The database itself has two tables:

```

    CREATE TABLE Users(username VARCHAR(255) PRIMARY KEY, passwd VARCHAR(255));
    CREATE TABLE UserRoles(username VARCHAR(255), role VARCHAR(32));
     
```


Here is the block needed for the security-domain

```

    <security-domain name="energyUserDomain" cache-type="default">
    	<authentication>
    		<login-module code="Database" flag="required">
    			<module-option name="dsJndiName" value="java:/userDS"/>
    			<module-option name="principalsQuery" value="select passwd from Users where username=?"/>
    			<module-option name="rolesQuery" value="select role,'Roles' from UserRoles where username=?"/>
    		</login-module>
    	</authentication>
    </security-domain>


```
In this case user and password are stored plain text, for production this is of course not acceptable. So you have to add more settings: 

```

 	<module-option name="hashAlgorithm" value="SHA-256"/>
    <module-option name="hashEncoding" value="base64"/>
                            
```

for further information read [here](http://blog.eisele.net/2015/01/jdbc-realm-wildfly820-primefaces51.html) 

