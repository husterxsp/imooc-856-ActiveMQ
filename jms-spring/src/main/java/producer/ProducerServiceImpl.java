package producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.*;

public class ProducerServiceImpl implements ProducerService {

    @Autowired
    JmsTemplate jmsTemplate;

    /**
    * 使用资源的方式
    * 因为可能会注入多个不同的目的地，这里用name区分
    * */
    @Resource(name = "topicDestination")
    Destination destination;

    @Override
    public void sendMessage(final String message) {
//        使用jmsTemplate发送消息
        jmsTemplate.send(destination, new MessageCreator() {
//            创建消息
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                return textMessage;
            }
        });
        System.out.println("发送消息" + message);

    }
}
