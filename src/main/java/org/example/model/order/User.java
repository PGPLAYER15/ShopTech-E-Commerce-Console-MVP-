package org.example.model.order;

public class User implements OrderObserver {
    private final int id;
    private String name;
    private String email;
    private String shippingAddress;
    private int rewardPoints;

    public User(int id, String name, String email, String shippingAddress) {
        this.id = id;
        this.setName(name);
        this.setEmail(email);
        this.setShippingAddress(shippingAddress);
        setRewardPoints(0);
    }

    @Override
    public void update(Order order, String event) {
        System.out.println("📧 EMAIL sent to " + email);
        System.out.println("   To: " + name);
        System.out.println("   Subject: Order Update - " + order.getOrderId());
        System.out.println("   Message: " + event);
        System.out.println("   Total: $" + order.getTotalAmount());
        System.out.println();
    }

    // getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public int getRewardPoints() {
        return rewardPoints;
    }
    public String getEmail() {
        return email;
    }
    public String getShippingAddress() {
        return shippingAddress;
    }


    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}
