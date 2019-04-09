package io.openfintechlab.mw.bootstrap;

import io.openfintechlab.mw.routes.ActiveMQRoute;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

public class ActiveMQBootStrap {

    public static void main(String[] args) {
        CamelContext context = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", ActiveMQConnection.DEFAULT_BROKER_URL);
        context.addComponent("activemq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("activemq:JMS.SAMPLE.REQ")
                            .to("log:?level=INFO&showBody=true&showHeaders=true")
                            .to("activemq:JMS.SAMPLE.RLY")
                            ;
                }
            });
            context.start();
            Thread.sleep(5000);
            context.stop();
        } catch (Exception e) {
            // Do something here
            e.printStackTrace();
        }

       /* CamelContext context = new DefaultCamelContext();
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file:data/input?noop=true")
                            .to("file:data/output")
                            ;
                }
            });

            context.start();
            Thread.sleep(5000);
            context.stop();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error received: "+ e.getLocalizedMessage());
        }*/



    }
}
