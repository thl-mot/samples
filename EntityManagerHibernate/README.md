#EntityManagerHibernate

Here is an exsample project how to use access a mysql database by EntityManager of hibernate.

##How to create and initialize the database


Start the commandline mysql client in a shell by

	mysql -u root

If the root account is secured by a password you have to add the parameter ``-p``

Step 1: Create the mysql database

    create database sample;

Step 2: Grant user test to the ``sample`` database

    grant all on sample.* to 'test'@'%' indentified by test;
    
The user ``test`` can now access the database from any client in your network.

