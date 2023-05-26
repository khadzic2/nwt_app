package ba.unsa.etf.nwt.order_service.Controller;

import ba.unsa.etf.nwt.order_service.repository.DateRepository;
import ba.unsa.etf.nwt.order_service.repository.OrderRepository;
import ba.unsa.etf.nwt.order_service.repository.StateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@TestPropertySource(locations = "classpath:./application-test.properties")
@SpringBootTest(classes = {ba.unsa.etf.nwt.order_service.OrderServiceApplication.class})
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
//    @BeforeEach
//    public void setup(){
//        orderRepository.deleteAll();
//        dateRepository.deleteAll();
//        stateRepository.deleteAll();
//    }
    @Test
    public void createStateSuccessTest() throws Exception{
        mockMvc.perform(post("/state")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(String.format("{\n" +
                            "\"state\" : \"Nvvvvv\"" +
                            "\n" +
                            "}")));
    }
}
