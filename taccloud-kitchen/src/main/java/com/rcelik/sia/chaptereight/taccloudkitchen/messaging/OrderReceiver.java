package com.rcelik.sia.chaptereight.taccloudkitchen.messaging;

import com.rcelik.sia.chaptereight.taccloudkitchen.domain.Order;

public interface OrderReceiver {
    Order receiveOrder();
}
