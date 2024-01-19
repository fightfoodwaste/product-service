package com.fightfoodwaste.products_service.service;

import com.fightfoodwaste.products_service.config.MessagingConfig;
import com.fightfoodwaste.products_service.dto.StockVerifiedMessage;
import com.fightfoodwaste.products_service.dto.VerifyStockMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublishingServiceImpl implements PublishingService{

    private final RabbitTemplate rabbitTemplate;


    @Override
    public void publishStockVerified(StockVerifiedMessage payload) {
        System.out.println("Stock verification complete for order #"+ payload.getOrder_id());
        rabbitTemplate.convertAndSend(MessagingConfig.VERIFY_STOCK_EXCHANGE_NAME, MessagingConfig.STOCK_VERIFIED_ROUTING_KEY, payload);
    }
}
