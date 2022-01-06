package task;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @PostMapping("/test")
    public <T> T test(@RequestParam T param) {
        return param;
    }
}
