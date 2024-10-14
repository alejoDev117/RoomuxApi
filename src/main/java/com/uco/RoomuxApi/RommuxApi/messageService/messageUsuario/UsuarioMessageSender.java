package com.uco.RoomuxApi.RommuxApi.messageService.messageUsuario;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.domain.SalaDomain;
import com.uco.RoomuxApi.RommuxApi.domain.UsuarioDomain;
import com.uco.RoomuxApi.RommuxApi.messageService.messageSala.SalaMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMessageSender {
    @Value("${rabbit.exchange.name.usuario}")
    private String exchange;
    @Value("${rabbit.routing.key.usuario.crear}")
    private String routingKeyCrear;
    @Value("${rabbit.queue.name.usuario.crear}")
    private String queueCrear;
    @Value("${rabbit.routing.key.usuario.eliminar}")
    private String routingKeyEliminar;
    @Value("${rabbit.queue.name.usuario.eliminar}")
    private String queueEliminar;

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioMessageSender.class);
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public UsuarioMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendSalaMessage(UsuarioDomain message, int codeSend) throws Exception {
        switch (codeSend) {
            case 1:
                LOGGER.info(String.format("Sending SalaMessage: %s", message.toString()));
                rabbitTemplate.convertAndSend(exchange, routingKeyCrear, message);
                break;
            case 2:
                LOGGER.info(String.format("Sending SalaMessage: %s", message.toString()));
                rabbitTemplate.convertAndSend(exchange, routingKeyEliminar, message);
                break;
            default:
                throw new RoomuxApiException("Error opcion no válida");
        }
    }
}
