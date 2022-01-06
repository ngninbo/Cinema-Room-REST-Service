package task;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @GetMapping("/{id}")
    public <T> T getNumbers(@PathVariable T id) {
        return id;
    }
}
