package topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static queue.Producer.queueName;

public class Consumer {

    public static final String url = "tcp://127.0.0.1:61616";
    public static final String topicName = "topic-test";

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
        Destination destination = session.createTopic(topicName);

//        6. 创建消费者
        MessageConsumer messageConsumer = session.createConsumer(destination);

//        7. 创建监听器

        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });


//        8. 关闭连接
//        接收消息异步过程，所以这里不需要关闭
//        connection.stop();

    }


}
