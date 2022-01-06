package task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class Controller {

    List<Student> students = List.of(
            new Student(84, "Alice"),
            new Student(99, "Kate"),
            new Student(55, "Someone"));

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {

        for (Student student: students) {
            if (student.getId() == id) {
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }

        return null;
    }

}

class Student {

    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
