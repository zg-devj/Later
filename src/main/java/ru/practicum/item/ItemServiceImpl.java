package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<Item> getItems(Long userId) {
        return itemRepository.findByUserId(userId);
    }

    @Transactional
    @Override
    public Item addNewItem(Long userId, Item item) {
        item.setUserId(userId);
        return itemRepository.save(item);
    }

    @Transactional
    @Override
    public void deleteItem(Long userId, long itemId) {
        itemRepository.deleteByUserIdAndItemId(userId, itemId);
    }
}
