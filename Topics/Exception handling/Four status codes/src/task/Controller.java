package task;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

@RestController
public class Controller {

    @GetMapping("/test/{status}")
    public ResponseEntity<String> exceptionCode(@PathVariable int status) {

        switch (status) {
            case 500:
                return new ResponseEntity<>(String.format("%d Internal Server Error", status), HttpStatus.INTERNAL_SERVER_ERROR);
            case 400:
                return new ResponseEntity<>(String.format("%d Bad Request", status), HttpStatus.BAD_REQUEST);
            case 300:
                return new ResponseEntity<>(String.format("%d Multiple Choices", status), HttpStatus.MULTIPLE_CHOICES);
            default:
                return new ResponseEntity<>(String.format("%d OK", status), HttpStatus.OK);
        }
    }

}
