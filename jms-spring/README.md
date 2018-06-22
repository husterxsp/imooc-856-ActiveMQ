队列模式和主题模式需要修改的地方
- consumer.xml  中 jmsContainer 的 destination 属性的ref
- ProducerServiceImpl.java 中注入的destination 