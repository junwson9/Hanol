package com.ssafy.hanol.global.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    private String rabbitmqHost;

    @Value("${spring.rabbitmq.port}")
    private int rabbitmqPort;

    @Value("${spring.rabbitmq.username}")
    private String rabbitmqUsername;

    @Value("${spring.rabbitmq.password}")
    private String rabbitmqPassword;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.request.name}")
    private String requestQueueName;

    @Value("${rabbitmq.queue.request.routing-key}")
    private String requestRoutingKey;

    @Value("${rabbitmq.queue.response.name}")
    private String responseQueueName;

    @Value("${rabbitmq.queue.response.routing-key}")
    private String responseRoutingKey;


    /**
     * RabbitMQ 연결을 위한 ConnectionFactory 빈 생성
     *
     * @return ConnectionFactory 객체
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitmqHost);
        connectionFactory.setPort(rabbitmqPort);
        connectionFactory.setUsername(rabbitmqUsername);
        connectionFactory.setPassword(rabbitmqPassword);
        return connectionFactory;
    }

    /**
     * RabbitTemplate 생성
     *
     * @param connectionFactory
     * @return RabbitTemplate 객체
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // JSON 형식의 메시지를 직렬화하고 역직렬할 수 있도록 설정
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    /**
     * Jackson 라이브러리를 사용하여 메세지를 JSON 형식으로 변환하는 MessageConverter 빈 생성
     *
     * @return MessageConverter 객체
     */
    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange scalpDiagnoseExchange() {
        return new DirectExchange(exchangeName);
    }

    // 지정된 큐 이름으로 Queue 빈 생성
    @Bean
    public Queue scalpDiagnoseRequestQueue() {
        return new Queue(requestQueueName, true, false, false);
    }

    @Bean
    public Queue scalpDiagnoseResponseQueue() {
        return new Queue(responseQueueName, true, false, false);
    }

    // 주어진 queue와 exchange를 바인딩하고 routing-key를 사용해서 Binding 빈 생성
    @Bean
    public Binding bindingScalpDiagnoseRequestQueue(Queue scalpDiagnoseRequestQueue, DirectExchange scalpDiagnoseExchange) {
        return BindingBuilder.bind(scalpDiagnoseRequestQueue).to(scalpDiagnoseExchange).with(requestRoutingKey);
    }

    @Bean
    public Binding bindingScalpDiagnoseResponseQueue(Queue scalpDiagnoseResponseQueue, DirectExchange scalpDiagnoseExchange) {
        return BindingBuilder.bind(scalpDiagnoseResponseQueue).to(scalpDiagnoseExchange).with(responseRoutingKey);
    }


}
