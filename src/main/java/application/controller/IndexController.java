package application.controller;
import java.util.List;
import java.util.Arrays;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class IndexController {

    private List<String> frutas = Arrays.asList("Maça", "Uva", "Banana");
    @GetMapping
    public List<String> home() {
        return frutas;
    }
}