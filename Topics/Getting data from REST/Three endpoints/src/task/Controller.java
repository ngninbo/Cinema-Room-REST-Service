package task;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    int value = 1;
    String text = "two";
    Map<String, Integer> map = new HashMap<>();

    @GetMapping("/value")
    public int getValue() {
        return value;
    }

    @GetMapping("/text")
    public String getText() {
        return text;
    }

    @GetMapping("/json")
    public Map<String, Integer> getJson() {
        map.put("number", 3);
        return map;
    }
}
