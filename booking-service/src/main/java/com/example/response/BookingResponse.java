package com.example.response;

import com.example.model.BookedRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Simpson Alfred
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String guestName;

    private String guestEmail;

    private int numOfAdults;

    private int numOfChildren;

    private int totalNumOfGuests;

    private String bookingConfirmationCode;
    private PaymentDto payment;
    private RoomResponse room;

    public BookingResponse(BookedRoom bookedRoom, RoomResponse room, PaymentDto payment) {
        this.id = bookedRoom.getBookingId();
        this.checkInDate = bookedRoom.getCheckInDate();
        this.checkOutDate = bookedRoom.getCheckOutDate();
        this.bookingConfirmationCode = bookedRoom.getBookingConfirmationCode();
        this.guestName = bookedRoom.getGuestFullName();
        this.guestEmail = bookedRoom.getGuestEmail();
        this.numOfAdults = bookedRoom.getNumOfAdults();
        this.numOfChildren = bookedRoom.getNumOfChildren();
        this.totalNumOfGuests = bookedRoom.getTotalNumOfGuest();
        this.room = room;
        this.payment = payment;
    }
}