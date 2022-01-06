package task;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    Object importantMessage = null;

    @PostMapping("/message")
    public void postImportantMessage(@RequestBody Object message) {
        importantMessage = message;
    }

    @GetMapping("/message")
    public Object getImportantMessage() {
        return importantMessage;
    }
}
