package com.ngockhuong.jms.topic;

import com.ngockhuong.jms.util.ContextUtil;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingException;

public class TopicConsumer implements MessageListener {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            System.out.println("Create JNDI Context");
            Context context = ContextUtil.getInitialContext();

            System.out.println("Get connection factory");
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");

            System.out.println("Create connection");
            connection = connectionFactory.createConnection();

            System.out.println("Create session");
            Session session = connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);

            System.out.println("Lookup queue");
            Topic topic = (Topic) context.lookup("/topic/HelloWorldTopic");

            System.out.println("Start connection");
            connection.start();

            System.out.println("Create consumer");
            MessageConsumer consumer = session.createConsumer(topic);

//            System.out.println("Set message listener");
//            consumer.setMessageListener(new TopicConsumer());

            while (true) {
                System.out.println("Message receiver");
                String text = ((TextMessage) consumer.receive()).getText();
                System.out.println(text);

                Thread.sleep(1000);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("close the connection consumer");
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onMessage(Message message) {
        try {
            System.out.println("Message receiver");
            String text = ((TextMessage) message).getText();
            System.out.println(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
