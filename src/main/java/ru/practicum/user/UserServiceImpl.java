package ru.practicum.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public List<UserDto> getAllUsers() {
        return UserMapper.toUserDto(repository.findAll());
    }

    @Transactional
    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = repository.save(UserMapper.toUser(userDto));
        return UserMapper.toUserDto(user);
    }

    public void checkUsers() {
        // сначала создаём описание сортировки по полю id
        Sort sortById = Sort.by(Sort.Direction.ASC, "id");
        // затем создаём описание первой "страницы" размером 32 элемента
        Pageable page = PageRequest.of(0, 32, sortById);
        do {
            // запрашиваем у базы данных страницу с данными
            Page<User> userPage = repository.findAll(page);
            // результат запроса получаем с помощью метода getContent()
            userPage.getContent().forEach(user -> {
                // проверяем пользователей
            });
            // для типа Page проверяем, существует ли следующая страница
            if (userPage.hasNext()) {
                // если следующая страница существует, создаём её описание, чтобы запросить на следующей итерации цикла
                page = PageRequest.of(userPage.getNumber() + 1, userPage.getSize(), userPage.getSort()); // или для простоты -- userPage.nextOrLastPageable()
            } else {
                page = null;
            }
        } while (page != null);
    }
}
