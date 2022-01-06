package cinema.controller;

import cinema.model.Cinema;
import cinema.model.Purchase;
import cinema.model.PurchasedTicket;
import cinema.model.Ticket;
import cinema.service.CinemaService;
import cinema.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CinemaController {

    private final CinemaService cinemaService;
    private final Map<String, String> error = new HashMap<>();

    public CinemaController(@Autowired CinemaService cinemaService) {
        this.cinemaService = cinemaService;
        error.put("error", Message.EMPTY_ERROR);
    }

    @GetMapping("/seats")
    public ResponseEntity<Cinema> getSeats() {
        return new ResponseEntity<>(cinemaService.getCinemaInfo(), HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Purchase purchase) {

        if (cinemaService.validate(purchase)) {
            PurchasedTicket ticket = cinemaService.purchase(purchase);
            if (ticket != null) {
                return new ResponseEntity<>(ticket, HttpStatus.OK);
            } else {
                error.replace("error", Message.PURCHASE_ERROR);
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
        } else {
            error.replace("error", Message.OUT_OF_BOUND_ERROR);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> refund(@RequestBody Map<String, String> token) {
        error.replace("error", Message.WRONG_TOKEN);
        Map<String, Ticket> refundTicket = cinemaService.refund(token);

        if (refundTicket != null) {
            return new ResponseEntity<>(refundTicket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/stats")
    public ResponseEntity<?> statistic(@RequestParam("password") Optional<String> password) {
        error.replace("error", Message.WRONG_PASSWORD);

        if (password.isPresent() && !"".equals(password.get())) {
            return new ResponseEntity<>(cinemaService.getStats(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }
}
