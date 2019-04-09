package io.openfintechlab.mw.bootstrap;

// import io.openfintechlab.mw.routes.ActiveMQRoute;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
// import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

import javax.jms.ConnectionFactory;

public class ActiveMQBootStrap {

    public static void main(String[] args) {
        Main main = new Main();

        CamelContext context = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", ActiveMQConnection.DEFAULT_BROKER_URL); // Remote connection
        // ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false"); // not working
        context.addComponent("activemq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("activemq:JMS.SAMPLE.REQ?asyncConsumer=true&concurrentConsumers=10")
                            .to("log:?level=INFO&showBody=true&showHeaders=true")
                            .to("activemq:JMS.SAMPLE.RLY")
                            ;
                }
            });
            context.start();
            Thread.sleep(50000);
            context.stop();
        } catch (Exception e) {
            // Do something here
            e.printStackTrace();
        }
    }
}
