package cinema.controller;

import cinema.model.BookedSeat;
import cinema.model.Cinema;
import cinema.model.Purchase;
import cinema.model.Ticket;
import cinema.service.CinemaService;
import cinema.util.CinemaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

import static cinema.util.CinemaData.*;

@RestController
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(@Autowired CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public ResponseEntity<Cinema> getSeats() {
        return new ResponseEntity<>(cinemaService.getCinemaInfo(), HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody BookedSeat bookedSeat) {

        if (CinemaUtil.validate(bookedSeat)) {
            Purchase ticket = cinemaService.purchase(bookedSeat);
            if (ticket != null) {
                return new ResponseEntity<>(ticket, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Collections.singletonMap("error", PURCHASE_ERROR),
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(Collections.singletonMap("error", OUT_OF_BOUND_ERROR),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> refund(@RequestBody Map<String, String> token) {
        Map<String, Ticket> refundTicket = cinemaService.refund(token);

        if (refundTicket != null) {
            return new ResponseEntity<>(refundTicket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("error", WRONG_TOKEN), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/stats")
    public ResponseEntity<?> statistic(@RequestParam(defaultValue = "", required = false) String password) {

        if (!password.trim().isEmpty()) {
            return new ResponseEntity<>(cinemaService.getStats(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("error", WRONG_PASSWORD),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}
