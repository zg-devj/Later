package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> get(@RequestHeader("X-Later-User-Id") Long userId,
                             @RequestParam(required = false) Set<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return itemService.getItems(userId);
        } else {
            return itemService.getItems(userId, tags);
        }
    }

    @GetMapping(params = "lastName")
    public List<ItemDto> get(@RequestParam String lastName) {
        return itemService.getUserItems(lastName);
    }

    @PostMapping
    public ItemDto add(@RequestHeader("X-Later-User-Id") Long userId,
                       @RequestBody ItemDto itemDto) {
        return itemService.addNewItem(userId, itemDto);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@RequestHeader("X-Later-User-Id") Long userId,
                           @PathVariable Long itemId) {
        itemService.deleteItem(userId, itemId);
    }

    @PostMapping("/part")
    public List<ItemCountByUser> getPart(@RequestBody String urlPart) {

        return itemService.getItemCountByUser(urlPart);
    }
}
