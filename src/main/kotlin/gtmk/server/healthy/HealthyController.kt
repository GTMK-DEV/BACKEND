package gtmk.server.healthy

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
public class HealthyController {
    @GetMapping("/")
    public fun healthy(): String {return "test hello world"}
}