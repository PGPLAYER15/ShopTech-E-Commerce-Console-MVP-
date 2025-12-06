package org.example.service;

import org.example.model.order.Order;
import org.example.model.order.OrderObserver;

public class NotificationService implements OrderObserver {
    private String serviceName;

    public NotificationService(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void update(Order order, String event) {
        System.out.println("═══════════════════════════════════════");
        System.out.println("📨 [" + serviceName + "] System Notification");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Event: " + event);
        System.out.println("Customer: " + order.getUser().getName());
        System.out.println("Total: $" + order.getTotalAmount());
        System.out.println("═══════════════════════════════════════");
    }
}

