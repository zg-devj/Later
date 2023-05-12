package ru.practicum.item;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface ItemService {
    List<ItemDto> getItems(long userId);

    ItemDto addNewItem(long userId, ItemDto itemDto);

    void deleteItem(long userId, long itemId);

    List<ItemDto> getItems(long userId, Set<String> tags);

    List<ItemCountByUser> getItemCountByUser(String urlPart);
}
