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
///////////////SALA////////////////////////////
    @Value("${rabbit.exchange.name.sala}")
    private String exchangeSala;
    @Value("${rabbit.queue.name.sala.crear}")
    private String queueCrearSala;
    @Value("${rabbit.routing.key.sala.crear}")
    private String routingKeyCrearSala;
    @Value("${rabbit.queue.name.sala.consultar}")
    private String queueConsultarSala;
    @Value("${rabbit.routing.key.sala.consultar}")
    private String routingKeyConsultarSala;
    @Value("${rabbit.queue.name.sala.eliminar}")
    private String queueEliminarSala;
    @Value("${rabbit.routing.key.sala.eliminar}")
    private String routingKeyEliminarSala;
////////////RESERVA////////////////////////////////////
    @Value("${rabbit.exchange.name.reserva}")
    private String exchangeReserva;
    @Value("${rabbit.queue.name.reserva.crear}")
    private String queueCrearReserva;
    @Value("${rabbit.routing.key.reserva.crear}")
    private String routingKeyCrearReserva;
    @Value("${rabbit.queue.name.reserva.consultar}")
    private String queueConsultarReserva;
    @Value("${rabbit.routing.key.reserva.consultar}")
    private String routingKeyConsultarReserva;
    @Value("${rabbit.queue.name.reserva.eliminar}")
    private String queueEliminarReserva;
    @Value("${rabbit.routing.key.reserva.eliminar}")
    private String routingKeyEliminarReserva;
    @Value("${rabbit.queue.name.reserva.actualizar}")
    private String queueActualizarReserva;
    @Value("${rabbit.routing.key.reserva.actualizar}")
    private String routingKeyActualizarReserva;

//Exchanges/////////////////////////////////////////////////
    @Bean
    public TopicExchange exchangeSala(){
        return new TopicExchange(exchangeSala);
    }

    @Bean
    public TopicExchange exchangeReserva(){
        return new TopicExchange(exchangeReserva);
    }

//Queues/////////////////////////////////////////////////
    @Bean
    public Queue queueCrearSala(){
        return new Queue(queueCrearSala);
    }
    @Bean
    public Queue queueConsultarSala(){
        return new Queue(queueConsultarSala);
    }

    @Bean
    public Queue queueEliminarSala(){
        return new Queue(queueEliminarSala);
    }

    @Bean
    public Queue queueCrearReserva(){
        return new Queue(queueCrearReserva);
    }
    @Bean
    public Queue queueConsultarReserva(){
        return new Queue(queueConsultarReserva);
    }

    @Bean
    public Queue queueEliminarReserva(){
        return new Queue(queueEliminarReserva);
    }

    @Bean
    public Queue queueActualizarReserva(){
        return new Queue(queueActualizarReserva);
    }

    //Bingings/////////////////////////////////////////
    @Bean
    public Binding bindingSalaCrear(){
        return BindingBuilder.bind(queueCrearSala()).to(exchangeSala()).with(routingKeyCrearSala);
    }
    @Bean
    public Binding bindingSalaConsultar(){
        return BindingBuilder.bind(queueConsultarSala()).to(exchangeSala()).with(routingKeyConsultarSala);
    }
    @Bean
    public Binding bindingSalaEliminar(){
        return BindingBuilder.bind(queueEliminarSala()).to(exchangeSala()).with(routingKeyEliminarSala);
    }

    @Bean
    public Binding bindingReservaCrear(){
        return BindingBuilder.bind(queueCrearReserva()).to(exchangeReserva()).with(routingKeyCrearReserva);
    }
    @Bean
    public Binding bindingReservaConsultar(){
        return BindingBuilder.bind(queueConsultarReserva()).to(exchangeReserva()).with(routingKeyConsultarReserva);
    }
    @Bean
    public Binding bindingReservaEliminar(){
        return BindingBuilder.bind(queueEliminarReserva()).to(exchangeReserva()).with(routingKeyEliminarReserva);
    }
    @Bean
    public Binding bindingReservaActualizar(){
        return BindingBuilder.bind(queueActualizarReserva()).to(exchangeReserva()).with(routingKeyActualizarReserva);
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
