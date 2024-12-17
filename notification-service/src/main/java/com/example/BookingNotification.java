package com.example;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Simpson Alfred
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingNotification {

    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String guestName;
    private String guestEmail;
    private int numOfAdults;
    private int numOfChildren;
    private int totalNumOfGuests;
    private String bookingConfirmationCode;
    private Long roomId;
    private String roomType;
    private BigDecimal roomPrice;
    private String paymentId;
    private String paymentMethod;
    private String status;
    private Long bookingId;
    private BigDecimal amount;
    private String transaction_id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}