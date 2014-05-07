# James Ward tutorial - Reactive All the Way Down

### Action.async
Facilitates asynchronous composition of results via a Future of a result
### AngularJS timeout
This is required to bind variables to the correct scope
### AngularJS ngApp
This defines the root html element of the angular application
### Angular $timeout
Within the context of this tutorial, the timeout seems to be required to enable variable population
### Play Result class
is deprecated in favour of SimpleResult in 2.2.3
### Actor 'pipeTo'

Utilising actors in a response may yield a different thread for the actor's sender ref.

     pipeTo sender //ensures the future result is piped to the original sender in a threadsafe manner

### ExecutionContext
    import scala.concurrent.ExecutionContext.Implicits.global //Provides a theadpool abstraction

### WebJars
Provides a dependency management wrapper around to serve common web libraries as JARs.
Integration requires web frameworks that can serve static assets from JAR files.