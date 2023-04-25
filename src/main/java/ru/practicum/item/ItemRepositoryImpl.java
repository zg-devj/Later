package ru.practicum.item;

import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    Set<Item> items = new HashSet<>();
    private static Long itemId = 0L;

    @Override
    public List<Item> findByUserId(long userId) {
        return items.stream().filter(e -> e.getUserId() == userId).collect(Collectors.toList());
    }

    @Override
    public Item save(Item item) {
        if (!items.contains(item)) {
            item.setId(++itemId);
            items.add(item);
            return item;
        }
        return null;
    }

    @Override
    public void deleteByUserIdAndItemId(long userId, long itemId) {
        Optional<Item> find = items.stream()
                .filter(((Predicate<Item>) s -> s.getUserId() == userId)
                        .and(u -> u.getId() == itemId))
                .findFirst();
        find.ifPresent(item -> items.remove(item));
    }
}
