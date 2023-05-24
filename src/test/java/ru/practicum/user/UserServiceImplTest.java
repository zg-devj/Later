package ru.practicum.user;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.config.PersistenceConfig;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Transactional
@TestPropertySource(properties = {"db.name=test"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringJUnitConfig({PersistenceConfig.class, UserServiceImpl.class})
class UserServiceImplTest {
    private final EntityManager em;
    private final UserService service;

    //@Rollback(value = false)
    @Test
    void getAllUsers() {
        UserDto userDto1 = makeUserDto("some@email.com", "Пётр", "Иванов");
        UserDto userDto2 = makeUserDto("another@email.com", "Иван", "Сидоров");
        service.saveUser(userDto1);
        service.saveUser(userDto2);

        List<UserDto> users = service.getAllUsers();

        Assertions.assertThat(users)
                .hasSize(2)
                .extracting(UserDto::getEmail)
                .containsExactlyInAnyOrder(
                        "some@email.com", "another@email.com"
                );
        Assertions.assertThat(users)
                .filteredOn(user -> user.getFirstName() == "Пётр")
                .filteredOn(user -> user.getFirstName() == "Иван");

        Assertions.assertThat(users)
                .filteredOn("state", UserState.ACTIVE);
    }

    //@Rollback(value = false)
    @Test
    void saveUser() {
        UserDto userDto = makeUserDto("some@email.com", "Пётр", "Иванов");
        service.saveUser(userDto);

        TypedQuery<User> query = em.createQuery("select u from User u where u.email = :email", User.class);
        User user = query
                .setParameter("email", userDto.getEmail())
                .getSingleResult();

        assertThat(user.getId(), notNullValue());
        assertThat(user.getFirstName(), equalTo(userDto.getFirstName()));
        assertThat(user.getLastName(), equalTo(userDto.getLastName()));
        assertThat(user.getEmail(), equalTo(userDto.getEmail()));
        assertThat(user.getState(), equalTo(userDto.getState()));
        assertThat(user.getRegistrationDate(), notNullValue());
    }

    private UserDto makeUserDto(String email, String firstName, String lastName) {
        return UserDto.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .state(UserState.ACTIVE)
                .build();
    }
}