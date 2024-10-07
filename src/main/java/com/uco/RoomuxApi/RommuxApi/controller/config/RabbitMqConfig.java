package com.uco.RoomuxApi.RommuxApi.controller.config;

import com.uco.RoomuxApi.RommuxApi.messageService.messageSala.SalaMessageSender;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbit.exchange.name.sala}")
    private String exchange;
    @Value("${rabbit.queue.name.sala.crear}")
    private String queueCrear;
    @Value("${rabbit.routing.key.sala.crear}")
    private String routingKeyCrear;
    @Value("${rabbit.queue.name.sala.consultar}")
    private String queueConsultar;
    @Value("${rabbit.routing.key.sala.consultar}")
    private String routingKeyConsultar;
    @Value("${rabbit.queue.name.sala.eliminar}")
    private String queueEliminar;
    @Value("${rabbit.routing.key.sala.eliminar}")
    private String routingKeyEliminar;
//Exchanges/////////////////////////////////////////////////
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

//Queues/////////////////////////////////////////////////
    @Bean
    public Queue queueCrear(){
        return new Queue(queueCrear);
    }
    @Bean
    public Queue queueConsultar(){
        return new Queue(queueConsultar);
    }

    @Bean
    public Queue queueEliminar(){
        return new Queue(queueEliminar);
    }

//Bingings/////////////////////////////////////////
    @Bean
    public Binding bindingCrear(){
        return BindingBuilder.bind(queueCrear()).to(exchange()).with(routingKeyCrear);
    }
    @Bean
    public Binding bindingConsultar(){
        return BindingBuilder.bind(queueConsultar()).to(exchange()).with(routingKeyConsultar);
    }
    @Bean
    public Binding bindingEliminar(){
        return BindingBuilder.bind(queueEliminar()).to(exchange()).with(routingKeyEliminar);
    }


//SenderMessages/////////////////////////////////////////////////////////////////////////
    @Bean
    public SalaMessageSender salaMessageSender(RabbitTemplate rabbitTemplate) {
        return new SalaMessageSender(rabbitTemplate);
    }


    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory    ){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }


}
