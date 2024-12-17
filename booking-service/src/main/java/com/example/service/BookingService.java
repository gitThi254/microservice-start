package com.example.service;

import com.example.client.PaymentFeign;
import com.example.client.RoomFeign;
import com.example.event.BookingNotification;
import com.example.event.OrderPlacedEvent;
import com.example.exception.InvalidBookingRequestException;
import com.example.exception.NotFoundException;
import com.example.model.BookedRoom;
import com.example.repository.BookingRepository;
import com.example.response.BookingResponse;
import com.example.response.PaymentDto;
import com.example.response.RoomResponse;
import com.example.resquest.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 * @author Simpson Alfred
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository;
    private final RoomFeign roomFeign;
    private final PaymentFeign paymentFeign;
    private final KafkaTemplate<String, BookingNotification> kafkaTemplate;


    @Override
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll()
                .stream().map(
                booking -> {
                    RoomResponse room =  roomFeign.getRoomById(booking.getRoomId());
                    PaymentDto payment = paymentFeign.getPayment(booking.getBookingId());
                   return new BookingResponse(booking, room, payment);
                }
        ).toList();
    }


    @Override
    public List<BookingResponse> getBookingsByUserEmail(String email) {
        return bookingRepository.findByGuestEmail(email)
                .stream().map(
                        booking -> {
                            RoomResponse room =  roomFeign.getRoomById(booking.getRoomId());
                            PaymentDto payment = paymentFeign.getPayment(booking.getBookingId());
                            return new BookingResponse(booking, room, payment);
                        }
                ).toList();
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public List<BookingResponse> getAllBookingsByRoomId(Long roomId) {
        return bookingRepository.findAllByRoomId(roomId)
                .stream().map(
                        booking -> {
                            RoomResponse room =  roomFeign.getRoomById(booking.getRoomId());
                            PaymentDto payment = paymentFeign.getPayment(booking.getBookingId());

                            return new BookingResponse(booking, room, payment);
                        }
                ).toList();
    }

    @Override
    public String saveBooking(Long roomId, String userId, BookedRoom bookingRequest) {
        if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())){
            throw new InvalidBookingRequestException("Check-in date must come before check-out date");
        }
        RoomResponse room = roomFeign.getRoomById(roomId);
        List<BookingResponse> existingBookings = this.getAllBookingsByRoomId(room.getId());
        boolean roomIsAvailable = roomIsAvailable(bookingRequest,existingBookings);
        if (roomIsAvailable){
            String bookingCode = RandomStringUtils.randomNumeric(10);
            bookingRequest.setBookingConfirmationCode(bookingCode);
            bookingRequest.setRoomId(room.getId());
            bookingRequest.setUserId(userId);
            BookedRoom newBookedRoom =  bookingRepository.save(bookingRequest);
            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setUserId(userId);
            paymentRequest.setBookingId(newBookedRoom.getBookingId());
            paymentRequest.setPaymentMethod("Banking");
            paymentRequest.setAmount(room.getRoomPrice());
            PaymentDto payment = paymentFeign.doPayment(paymentRequest);
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setBookingId(newBookedRoom.getBookingId());
            kafkaTemplate.send("notificationTopic", new BookingNotification(newBookedRoom,payment, room));

        }else{
            throw  new InvalidBookingRequestException("Sorry, This room is not available for the selected dates;");
        }
        return bookingRequest.getBookingConfirmationCode();
    }

    @Override
    public BookingResponse findByBookingConfirmationCode(String confirmationCode) {
        return bookingRepository.findByBookingConfirmationCode(confirmationCode)
                .map(
                        booking -> {
                            RoomResponse room =  roomFeign.getRoomById(booking.getRoomId());
                            PaymentDto payment = paymentFeign.getPayment(booking.getBookingId());
                            return new BookingResponse(booking, room, payment);
                        })
                .orElseThrow(() -> new NotFoundException("No booking found with booking code :"+confirmationCode));

    }


    private boolean roomIsAvailable(BookedRoom bookingRequest, List<BookingResponse> existingBookings) {
        return existingBookings.stream()
                .allMatch(existingBooking -> Objects.equals(existingBooking.getPayment().getStatus(), "paid") || (bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckInDate())
                        || bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckOutDate())));
    }




}
