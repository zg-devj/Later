package ru.practicum.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class ItemMapper {
    public static ItemDto mapToItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .userId(item.getUserId())
                .url(item.getUrl())
                .tags(new HashSet<>(item.getTags()))
                .build();
    }

    public static List<ItemDto> mapToItemDto(Iterable<Item> items) {
        return StreamSupport.stream(items.spliterator(), false)
                .map(ItemMapper::mapToItemDto)
                .collect(Collectors.toList());
    }

    public static Item mapToItem(ItemDto itemDto, long userId) {
        Item item = new Item();
        item.setUserId(userId);
        item.setUrl(itemDto.getUrl());
        item.setTags(itemDto.getTags());
        return item;
    }

//public static Item mapToItem(ItemDto itemDto, Long userId) {
//    Item item = new Item();
//    item.setUserId(userId);
//    item.setUrl(itemDto.getUrl());
//    item.setTags(itemDto.getTags());
//    return item;
//}

//    public static ItemDto mapToItemDto(Item item) {
//        return new ItemDto(
//                item.getId(),
//                item.getUserId(),
//                item.getUrl(),
//                new HashSet<>(item.getTags())
//        );
//    }

//    public static List<ItemDto> mapToItemDto(Iterable<Item> items) {
//        List<ItemDto> dtos = new ArrayList<>();
//        for (Item item : items) {
//            dtos.add(mapToItemDto(item));
//        }
//        return dtos;
//    }
}
