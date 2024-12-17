package com.example.event;

import com.example.model.BookedRoom;
import com.example.response.PaymentDto;
import com.example.response.RoomResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    public BookingNotification(BookedRoom bookedRoom, PaymentDto paymentDto, RoomResponse room) {
         this.id = bookedRoom.getBookingId();
        this.checkInDate = bookedRoom.getCheckInDate();
        this.checkOutDate = bookedRoom.getCheckOutDate();
        this.guestName = bookedRoom.getGuestFullName();
        this.guestEmail = bookedRoom.getGuestEmail();
        this.numOfAdults = bookedRoom.getNumOfAdults();
        this.numOfChildren = bookedRoom.getNumOfChildren();
        this.totalNumOfGuests = bookedRoom.getTotalNumOfGuest();
        this.bookingConfirmationCode = bookedRoom.getBookingConfirmationCode();
        this.roomId = room.getId();
        this.roomType = room.getRoomType();
        this.roomPrice = room.getRoomPrice();
        this.paymentId = paymentDto.getId();
        this.paymentMethod = paymentDto.getPaymentMethod();
        this.status = paymentDto.getStatus();
        this.bookingId = paymentDto.getBookingId();
        this.amount = paymentDto.getAmount();
        this.transaction_id = paymentDto.getTransaction_id();
        this.createdAt = paymentDto.getCreatedAt();
        this.updatedAt = paymentDto.getUpdatedAt();
    }
}