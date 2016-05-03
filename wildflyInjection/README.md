#Injection on Pojos with wildfly

In this example I'd like to show how to do dependency injection on pojos from within managed beans (e.g. Session Beans, Servlet).

The Idea was born when I had to inject references to Beans that where dynamically created within a Stateless Session Bean based on information coming from the business process.  

I reduced my code to a Stateless Session Bean that basically follows a Command Pattern.

The Session Bean has one single method that gets the class name as String parameter and runs one method of the bean itself.
