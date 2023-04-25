package ru.practicum.item;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public List<Item> getItems(Long userId) {
        return null;
    }

    @Override
    public Item addNewItem(Long userId, Item item) {
        return null;
    }

    @Override
    public void deleteItem(Long userId, long itemId) {

    }
}
