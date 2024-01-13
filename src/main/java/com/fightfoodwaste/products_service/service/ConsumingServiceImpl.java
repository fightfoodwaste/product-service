package com.fightfoodwaste.products_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fightfoodwaste.products_service.config.MessagingConfig;
import com.fightfoodwaste.products_service.dto.ProductStockGetResponse;
import com.fightfoodwaste.products_service.dto.SetQuantityRequest;
import com.fightfoodwaste.products_service.dto.StockVerifiedMessage;
import com.fightfoodwaste.products_service.dto.VerifyStockMessage;
import com.fightfoodwaste.products_service.exceptions.ProductNotFoundException;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ConsumingServiceImpl implements ConsumingService{

    private final PublishingService publishingService;

    private final ProductStockService productStockService;

    private final ObjectMapper objectMapper;


    @RabbitListener(queues = MessagingConfig.VERIFY_STOCK_QUEUE_NAME)
    @Override
    public void consumeVerifyStock(com.fightfoodwaste.products_service.dto.VerifyStockMessage payload){
        StockVerifiedMessage responseMessage = new StockVerifiedMessage(payload.getOrder_id(), false);
        try{
            ProductStockGetResponse response = productStockService.getProductStock(payload.getProduct_id());
            int currentQuantity = response.getQuantity();
            int requestedQuantity = payload.getProduct_amount();
            if(currentQuantity >= requestedQuantity){
                responseMessage.setAvailable(true);
                SetQuantityRequest request = new SetQuantityRequest(response.getProduct_id(), currentQuantity - requestedQuantity);
                productStockService.setQuantity(request);
            }
        }catch(ProductNotFoundException e){
            responseMessage.setAvailable(false);
        }catch(Exception e){
            e.printStackTrace();
        }
        publishingService.publishStockVerified(responseMessage);
    }
    /*@Override
    public void consumeVerifyStock(Message message, Channel channel) {
        VerifyStockMessage payload;
        try{
            System.out.println(Arrays.toString(message.getBody()));
            System.out.println(message.getMessageProperties().toString());
            payload = objectMapper.readValue(message.getBody(), com.fightfoodwaste.products_service.dto.VerifyStockMessage.class);
            StockVerifiedMessage responseMessage = new StockVerifiedMessage(payload.getOrder_id(), false);
            try{
                ProductStockGetResponse response = productStockService.getProductStock(payload.getProduct_id());
                if(response.getQuantity() >= payload.getProduct_amount()){
                    responseMessage.setAvailable(true);
                    // remove from stock
                }
                publishingService.publishStockVerified(responseMessage);
            }catch(ProductNotFoundException e){
                responseMessage.setAvailable(false);
            }catch(Exception e){
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                e.printStackTrace();
            }
        }catch (Exception e){
            try{
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }catch (IOException e2){
                e2.printStackTrace();
            }
        }


    }*/
}
