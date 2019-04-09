package io.openfintechlab.mw.routes;

import org.apache.camel.builder.RouteBuilder;



public class ActiveMQRoute extends RouteBuilder {

    public void configure() throws Exception {
        from("activemq:queue:JMS.SAMPLE.REQ")
              .log("Message body: ${body} and headers ${headers}")
              // .to("log:?level=INFO&showBody=true")
              .to("direct:readQueue");
    }
}
