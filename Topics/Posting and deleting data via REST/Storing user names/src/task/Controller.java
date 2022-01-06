package task;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;

@RestController
public class Controller {

    List<String> users = new ArrayList<>();

    @GetMapping("/users")
    public List<String> getUsers() {
        return users;
    }

    @PostMapping("/users")
    public void postUser(@RequestParam String name) {
        users.add(name);
    }

}
