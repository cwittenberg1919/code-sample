# Charter Enterprise MOTD Sample Project
A small project that allows users to get, set, and reset a general charter maintanance message.

## How to Use:

This application accepts the following REST requests:

Get:
	- Path: "/"
	- Result: The currently set maintenance message for the application.

Put:
	- Path: "/"
	- Param:
		String message: the new message to be returned by the application. If the value "reset" is used in the message, the message reverts to the default message. Parameter cannot be null or empty. 
	- Result: 
		200: The message has been set or reset successfully.
		400: Message cannot be null or empty.

## Changes Made:

- Controller stores the message to be output in a private variable called Motd. Original message is stored as a constant called DEFAULT_MESSAGE. Motd is set to DEFAULT_MESSAFE initially.

- Added PUT mapping that allows the user to specify a custom message to be returned by the application on get requests. If the message equals the value "reset", then the Motd variable is set back to DEFAULT_MESSAGE. Added error handling case for null and empty message strings
- REST methods return ResponseEntities<String> instead of Strings so appropriate HTTP statuses can be returned.
- Fixed unit tests and added more unit tests to test additional cases expalined above.

### Getting Started
* To compile
```mvn clean package```

* To run
```mvn spring-boot:run```

* To see the message:
```curl localhost:8080```

### Prerequisites
* Java 1.8
* Maven
* cURL
  
### Deployment
If you whiz through this sample, try adding a deployment.   We are a Docker and AWS shop.  Getting something into an
AWS or Heroku, or whatever you're comfortable with will be an "A+"
