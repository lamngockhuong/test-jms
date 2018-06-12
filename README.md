Java Simple Application - JMS - JBoss
--
Send message with Queue and Topic

Built on JBoss AS 5.1.0 (http://sourceforge.net/projects/jboss/files/JBoss/JBoss-5.1.0.GA)

Add config to file path: jboss-home/server/default/deploy/messaging/destinations-service.xml

```
   <mbean code="org.jboss.jms.server.destination.QueueService"
   name="jboss.messaging.destination:service=Queue,name=HelloWorldQueue"
   xmbean-dd="xmdesc/Queue-xmbean.xml">
      <depends optional-attribute-name="ServerPeer">jboss.messaging:service=ServerPeer</depends>
      <depends>jboss.messaging:service=PostOffice</depends>
   </mbean>

   <mbean code="org.jboss.jms.server.destination.TopicService"
   name="jboss.messaging.destination:service=Queue,name=HelloWorldTopic"
   xmbean-dd="xmdesc/Topic-xmbean.xml">
      <depends optional-attribute-name="ServerPeer">jboss.messaging:service=ServerPeer</depends>
      <depends>jboss.messaging:service=PostOffice</depends>
   </mbean>
```