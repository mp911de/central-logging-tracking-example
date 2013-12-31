Example for central logging in an distributed environment
====================
This small project demonstrates an approach to track requests/sessions though an distributed environment.
All requests are assumed to be HTTP or HTTPS. Example services are provided as REST and SOAP WebServices.


Running the example
----------
You need Apache Maven 3 in order to run the example. The example will start an embedded HTTP server on port 8080

Start the server using

    mvn clean package jetty:run


Then you can run the tests using

    mvn integration-test


What you'll see
----------

You'll see in the server process some messages flickering. The interesting parts are the JSON fields `_Tracking.RootRequestId` and
`_Tracking.RootSessionId` (well, since there's no session, you'll see "none" as session Id).

The magical thing is not just passing request id's or session id's around. The big deal about that is, that you have the id data at the moment
of logging. This allows you to correlate the requests. So you can see in one place, which log messages were produced during the user session/the user request
or even machine requests (depending on who your users are).



More
----------
See http://www.paluch.biz/blog/91-tracking-requests-in-a-distributed-environment.html for more details.

