package org.example.patterns.adapter;

import org.example.model.order.Order;

public class AccountingAdapter implements AccountingService {

    private LegacyAccountingSystem legacyAccountingSystem;

    public AccountingAdapter(LegacyAccountingSystem legacyAccountingSystem) {

        if(legacyAccountingSystem == null){
            throw new IllegalArgumentException("LegacyAccountingSystem cannot be null");
        }

        this.legacyAccountingSystem = legacyAccountingSystem;
    }

    @Override
    public void logSale(Order order) {
        if(order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        String orderId = order.getOrderId();
        String customerName = order.getUser().getName();
        double amount = order.getTotalAmount();

        System.out.println("ADAPTER: Translating logSale() -> registrarVenta()");
        legacyAccountingSystem.registrarVenta(orderId, customerName, amount);

    }
}

