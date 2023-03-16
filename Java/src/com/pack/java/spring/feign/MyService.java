import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    @Autowired
    private MyServiceClient client;

    @Autowired
    private JsonPlaceholderClient client;

    public Post getPostById(Long id) {
        return client.getPostById(id);
    }

    public User getUserById(Long id) {
        return client.getUserById(id);
    }
}
