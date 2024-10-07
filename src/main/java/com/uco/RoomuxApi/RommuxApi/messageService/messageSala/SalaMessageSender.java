package com.uco.RoomuxApi.RommuxApi.messageService.messageSala;


import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.domain.SalaDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SalaMessageSender {

    @Value("${rabbit.exchange.name.sala}")
    private String exchange;
    @Value("${rabbit.routing.key.sala.crear}")
    private String routingKeyCrear;
    @Value("${rabbit.queue.name.sala.crear}")
    private String queueCrear;
    @Value("${rabbit.routing.key.sala.consultar}")
    private String routingKeyConsultar;
    @Value("${rabbit.queue.name.sala.consultar}")
    private String queueConsultar;
    @Value("${rabbit.routing.key.sala.eliminar}")
    private String routingKeyEliminar;
    @Value("${rabbit.queue.name.sala.eliminar}")
    private String queueEliminar;

    private static final Logger LOGGER = LoggerFactory.getLogger(SalaMessageSender.class);
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public SalaMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendSalaMessage(SalaDomain message,int codeSend) throws RoomuxApiException {
        switch (codeSend){
            case 1:
                LOGGER.info(String.format("Sending SalaMessage: %s", message.toString()));
                rabbitTemplate.convertAndSend(exchange, routingKeyCrear, message);
                break;
            case 2:
                LOGGER.info(String.format("Sending SalaMessage: %s", message.toString()));
                rabbitTemplate.convertAndSend(exchange, routingKeyConsultar, message);
                break;
            case 3:
                LOGGER.info(String.format("Sending SalaMessage: %s", message.toString()));
                rabbitTemplate.convertAndSend(exchange, routingKeyEliminar, message);
                break;
            default:
                throw new RoomuxApiException("Error opcion no válida");
        }
    }
}
