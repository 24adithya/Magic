import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "my-service")
public interface MyServiceClient {
    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") Long id);
}
