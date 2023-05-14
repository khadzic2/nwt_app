package ba.unsa.etf.nwt.item_service.Controller;

import ba.unsa.etf.nwt.item_service.HomeController;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Model.SelectedSpecifications;
import ba.unsa.etf.nwt.item_service.Model.ItemOrders;
import ba.unsa.etf.nwt.item_service.Model.Specifications;
import ba.unsa.etf.nwt.item_service.Repository.ItemRepository;
import ba.unsa.etf.nwt.item_service.Repository.ItemOrdersRepository;
import ba.unsa.etf.nwt.item_service.Repository.SpecificationsRepository;
import ba.unsa.etf.nwt.item_service.Repository.SelectedSpecificationsRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ItemControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SpecificationsRepository specificationsRepository;
    @Autowired
    private SelectedSpecificationsRepository selectedSpecificationsRepository;

    @Autowired
    private HomeController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    private Item item1;
    // private Item name1;
    // private Item description1;
    //private Specifications specifications1;
    //private SelectedSpecifications selectedSpecifications1;

    @BeforeEach
    void setup() {
        itemRepository.deleteAll();
        item1 = new Item("Table", "new table", 12.5, Item.StatusType.AVAILABLE);
        Item item2 = new Item("Chair", "new chair white", 15.5, Item.StatusType.AVAILABLE);
        Item item3 = new Item("Chair101", "A new one", 20.5, Item.StatusType.NOT_AVAILABLE);
        List<Item> records = new ArrayList<>(Arrays.asList(item1, item2, item3));
        itemRepository.saveAll(records);
    }


    @Test
    void addItem() throws Exception {
        mockMvc.perform(post("/")
                .content("{\n" +
                        "\"name\": \"Sofa\",\n" +
                        "\"description\": \"new sofa\",\n" +
                        "\"price\": 12.5,\n" +
                        "\"status\": \"AVAILABLE\",\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

/*
    @Test
    void addItemBadRequest() throws Exception {
        mockMvc.perform(post("/api/item")
                        .content("{\n" +
                                "\"name\": \"Table\",\n" +
                                "\"description\": \"new table\",\n" +
                                "\"price\": 12.5,\n" +
                                "\"status\": \"AVAILABLE\",\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Chair"));
    }

 */

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
