package com.example.client;


import com.example.exception.InvalidBookingRequestException;
import com.example.exception.NotFoundException;
import com.example.exception.ServiceUnavailableException;
import com.example.response.RoomResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
//@FeignClient(name="room-service", path = "/rooms")
@FeignClient(name="room-service", url="http://localhost:8081", path = "/rooms")
public interface RoomFeign {
    Log log = LogFactory.getLog(RoomFeign.class);

    @GetMapping("/{roomId}")
    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackRoomGetById")
    RoomResponse getRoomById(@PathVariable Long roomId);
    default RoomResponse fallbackRoomGetById(Long roomId, Throwable ex) {
            throw new ServiceUnavailableException("Oops! Something went wrong on the room service, please try again later!");
    }
}
