package com.fightfoodwaste.products_service.service;

import com.fightfoodwaste.products_service.dto.VerifyStockMessage;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

public interface ConsumingService {

    void consumeVerifyStock(VerifyStockMessage payload);
}
