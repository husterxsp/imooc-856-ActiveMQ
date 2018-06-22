package queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {

    public static final String url = "tcp://127.0.0.1:61616";
    public static final String queueName = "queue-test";

    public static void main(String[] args) throws JMSException {

//        1. 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//        2. 创建连接
        Connection connection = connectionFactory.createConnection();

//        3. 启动连接
        connection.start();

//        4. 创建会话
//        是否在事务中处理。使用自动应答模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

//        5. 创建目标
        Destination destination = session.createQueue(queueName);

//        6. 创建生产者
        MessageProducer messageProducer = session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
//            7. 创建消息

            TextMessage textMessage = session.createTextMessage("test" + i);

//            8. 发布消息
            messageProducer.send(textMessage);
            System.out.println("发送：" + textMessage.getText()    );

        }

//        9. 关闭连接
        connection.stop();

    }


}
