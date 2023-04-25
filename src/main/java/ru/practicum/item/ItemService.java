package ru.practicum.item;

import java.util.List;

public interface ItemService {
    List<Item> getItems(Long userId);

    Item addNewItem(Long userId, Item item);

    void deleteItem(Long userId, long itemId);
}
