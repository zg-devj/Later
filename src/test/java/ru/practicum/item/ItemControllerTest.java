package ru.practicum.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.practicum.item.dto.AddItemRequest;
import ru.practicum.item.dto.ItemDto;
import ru.practicum.item.dto.ModifyItemRequest;
import ru.practicum.user.UserDto;
import ru.practicum.user.UserState;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {
    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController controller;

    private final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mvc;

    private ItemDto itemDto;
    private AddItemRequest addItemRequest;
    private ModifyItemRequest modifyItemRequest;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
        addItemRequest = new AddItemRequest();
        addItemRequest.setUrl("");

//        itemDto = new ItemDto(1L,
//                "example.com",
//                "example.com",
//                "TEXT");
    }

    @Test
    void addNewItem() {
        Mockito.when(itemService.addNewItem(
                Mockito.anyLong(), Mockito.any(AddItemRequest.class)));

        //mvc.perform()
    }
}