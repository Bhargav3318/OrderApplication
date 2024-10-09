package com.orderManagementSystem.constants;

public interface OrderStatus {

    public static String CREATED = "CREATED";
    public static String PAYMENT_PROCESSED = "PAYMENT_PROCESSED";
    public static String PREPARATION = "PREPARATION";
    public static String READY = "READY";
    public static String DELIVERED = "DELIVERED";
    public static String DETAILS_ERROR = "NOT_VALID_DETAILS";
    public static String NO_ORDER_FOUND = "NO_ORDER_FOUND";
    public static String PAYMENT_PENDING = "PAYMENT_PENDING";
    public static String CHOOSE_A_DIFFERENT_TYPE = "CHOOSE_A_DIFFERENT_TYPE";
    public static String NO_ORDER_FOUND_WITH_ID = "NO_ORDER_FOUND_WITH_ID";
}
