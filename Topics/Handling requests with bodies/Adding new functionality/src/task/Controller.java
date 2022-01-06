package task;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
public class Controller {
    Map<Integer, String> map = new ConcurrentHashMap<>();
    AtomicInteger atomicInteger = new AtomicInteger();

    @GetMapping("/api/data/{id}")
    public Map<String, String> getData(@PathVariable int id) {
        return Map.of("data", map.get(id));
    }

    // add your code below this line
    @PostMapping("/api/data/new")
    public Map<String, Integer> postData(@RequestBody Map<String, String> data) {
        int id = atomicInteger.incrementAndGet();
        map.put(id, data.get("data"));

        return Map.of("id", id);
    }

}