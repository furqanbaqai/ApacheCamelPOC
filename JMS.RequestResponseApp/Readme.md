# JMS Request / Response POC Route
Sample Apache Camel route in JAVA-DSL for reading a JMS message from **Apache Active MQ** and sending a response to a
reply to queue received as a header in the JMS message.

Following use-cases are covered in this example:

## Use-Case 01: Read a message from JMS request queue as per the configuration
Application should keep on listening on the queue JMS.SAMPLE.REQ and should process the message.

## Use-Case 02: Read reply to queue property from the request message
## Use-Case 03: Transform the message
## Use-Case 04: Send the message to a response queue
## Use-Case 05: Log all messages in a log file
## Use-Case 06: Exception / Error handling
