package com.fightfoodwaste.products_service.service;

import com.fightfoodwaste.products_service.dto.StockVerifiedMessage;
import com.fightfoodwaste.products_service.dto.VerifyStockMessage;

public interface PublishingService {

    void publishStockVerified(StockVerifiedMessage message);
}
