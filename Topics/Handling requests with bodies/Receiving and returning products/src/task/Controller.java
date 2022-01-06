package task;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @PostMapping("/api/products")
    public Product postProduct(@RequestBody Product product) {
        return product;
    }

}

class Product {

    private String id;
    private String name;
    private String price;

    public Product(String id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}