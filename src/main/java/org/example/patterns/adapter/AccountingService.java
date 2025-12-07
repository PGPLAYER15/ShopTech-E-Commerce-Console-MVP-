package org.example.patterns.adapter;

import org.example.model.order.Order;

public interface AccountingService {
    void logSale(Order order);
}

