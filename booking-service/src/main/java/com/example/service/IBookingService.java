package com.example.service;


import com.example.model.BookedRoom;
import com.example.response.BookingResponse;
import com.example.resquest.BookingRequest;

import java.util.List;

/**
 * @author Simpson Alfred
 */

public interface IBookingService {
    void cancelBooking(Long bookingId);

    List<BookingResponse> getAllBookingsByRoomId(Long roomId);

    String saveBooking(Long roomId, String userId, BookedRoom bookingRequest);

    BookingResponse findByBookingConfirmationCode(String confirmationCode);

    List<BookingResponse> getAllBookings();

    List<BookingResponse> getBookingsByUserEmail(String email);
}