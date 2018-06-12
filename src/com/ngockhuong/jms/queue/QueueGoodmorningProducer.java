package com.ngockhuong.jms.queue;

import com.ngockhuong.jms.util.ContextUtil;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingException;


public class QueueGoodmorningProducer {
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
            Queue queue = (Queue) context.lookup("/queue/HelloWorldQueue");

            System.out.println("Start connection");
            connection.start();

            System.out.println("Create producer");
            MessageProducer producer = session.createProducer(queue);

            int msgTemp = 0;

            while (true) {
                msgTemp++;
                System.out.println("-- Create Good morning message ");
                Message helloWorldText = session.createTextMessage("------ Good morning! - " + msgTemp);
                System.out.println("-- Send Good morning message " + msgTemp);
                producer.send(helloWorldText);

                Thread.sleep(2000);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("close the connection good morning");
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
