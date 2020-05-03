package com.rcelik.sia.chaptereight.messaging;

import com.rcelik.sia.chaptereight.domain.Order;

public interface OrderMessagingService {

    void sendOrder(Order order);

}
