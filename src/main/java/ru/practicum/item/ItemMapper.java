package ru.practicum.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .userId(item.getUserId())
                .userId(item.getUserId())
                .url(item.getUrl())
                .tags(item.getTags())
                .build();
    }

    public static List<ItemDto> toItemDto(Iterable<Item> items) {
        return StreamSupport.stream(items.spliterator(), false)
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }

    public static Item toItem(ItemDto itemDto, long userId) {
        Item item = new Item();
        item.setUserId(userId);
        item.setUrl(itemDto.getUrl());
        item.setTags(itemDto.getTags());
        return item;
    }
}
