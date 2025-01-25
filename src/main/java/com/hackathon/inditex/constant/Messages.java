package com.hackathon.inditex.constant;

public final class Messages {

    private Messages() {}

    public static final String API_WORKING = "API is working.";
    public static final String CENTER_CREATED = "Logistics center created successfully.";
    public static final String CENTER_UPDATED = "Logistics center updated successfully.";
    public static final String CENTER_DELETED = "Logistics center deleted successfully.";
    public static final String CENTER_IN_USE_ERR = "Center is in use by existing orders.";
    public static final String CENTER_EXISTS_ERR = "There is already a logistics center in that position.";
    public static final String CENTER_EXCEEDS_CAPACITY_ERR = "Current load cannot exceed max capacity.";
    public static final String CENTER_NOT_FOUND_ERR = "Center not found.";
    public static final String CENTER_UNSUPPORTED_ERR = "No available centers support the order type.";
    public static final String CENTER_FULL_ERR = "All centers are at maximum capacity.";
    public static final String CENTER_UNAVAILABLE_ERR = "No available centers.";
    public static final String ORDER_CREATED  = "Order created successfully in PENDING status.";
}
