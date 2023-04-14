package ba.unsa.etf.nwt.order_service.Controller;

import ba.unsa.etf.nwt.order_service.repository.DateRepository;
import ba.unsa.etf.nwt.order_service.repository.OrderRepository;
import ba.unsa.etf.nwt.order_service.repository.StateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@TestPropertySource(locations = "classpath:./application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DateControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DateRepository dateRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private OrderRepository orderRepository;
    @BeforeEach
    public void setup(){
        dateRepository.deleteAll();
        stateRepository.deleteAll();
        orderRepository.deleteAll();
    }

}
