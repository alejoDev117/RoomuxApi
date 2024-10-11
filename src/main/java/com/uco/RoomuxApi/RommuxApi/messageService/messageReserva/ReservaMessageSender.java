package com.uco.RoomuxApi.RommuxApi.messageService.messageReserva;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.domain.ReservaDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReservaMessageSender {

    @Value("${rabbit.exchange.name.reserva}")
    private String exchange;
    @Value("${rabbit.routing.key.reserva.crear}")
    private String routingKeyCrear;
    @Value("${rabbit.queue.name.reserva.crear}")
    private String queueCrear;
    @Value("${rabbit.routing.key.reserva.consultar}")
    private String routingKeyConsultar;
    @Value("${rabbit.queue.name.reserva.consultar}")
    private String queueConsultar;
    @Value("${rabbit.routing.key.reserva.eliminar}")
    private String routingKeyEliminar;
    @Value("${rabbit.queue.name.reserva.eliminar}")
    private String queueEliminar;
    @Value("${rabbit.routing.key.reserva.actualizar}")
    private String routingKeyActualizar;
    @Value("${rabbit.queue.name.reserva.actualizar}")
    private String queueActualizar;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservaMessageSender.class);
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public ReservaMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendReservaMessage(ReservaDomain message, int codeSend) throws RoomuxApiException {
        switch (codeSend) {
            case 1:
                LOGGER.info(String.format("Sending CrearReservaMessage: %s", message.toString()));
                rabbitTemplate.convertAndSend(exchange, routingKeyCrear, message);
                break;
            case 2:
                LOGGER.info(String.format("Sending ConsultarReservaMessage: %s", message.toString()));
                rabbitTemplate.convertAndSend(exchange, routingKeyConsultar, message);
                break;
            case 3:
                LOGGER.info(String.format("Sending EliminarReservaMessage: %s", message.toString()));
                rabbitTemplate.convertAndSend(exchange, routingKeyEliminar, message);
                break;
            case 4:
                LOGGER.info(String.format("Sending ActualizarReservaMessage: %s", message.toString()));
                rabbitTemplate.convertAndSend(exchange, routingKeyActualizar, message);
                break;
            default:
                throw new RoomuxApiException("Error opción no válida");
        }
    }
}
