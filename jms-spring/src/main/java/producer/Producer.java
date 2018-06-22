package producer;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

public class Producer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("producer.xml");

        ProducerService service = context.getBean(ProducerService.class);

        for (int i = 0; i < 100; i++) {
            service.sendMessage("test" + i);
        }

//        关闭上下文后，spring会自动清理资源，比如创建的连接
        context.close();
    }
}
