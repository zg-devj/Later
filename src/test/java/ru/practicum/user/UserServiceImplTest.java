package ru.practicum.user;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    void getAllUsers_em() {
        // given
        List<UserDto> sourceUsers = List.of(
                makeUserDto("ivan@email", "Ivan", "Ivanov"),
                makeUserDto("petr@email", "Petr", "Petrov"),
                makeUserDto("vasilii@email", "Vasilii", "Vasiliev")
        );

        for (UserDto user : sourceUsers) {
            User entity = UserMapper.toUser(user);
            em.persist(entity);
        }
        em.flush();

        // when
        List<UserDto> targetUsers = service.getAllUsers();

        // then
        assertThat(targetUsers, hasSize(sourceUsers.size()));
        for (UserDto sourceUser : sourceUsers) {
            assertThat(targetUsers, hasItem( allOf(
                    hasProperty("id", notNullValue()),
                    hasProperty("firstName", equalTo(sourceUser.getFirstName())),
                    hasProperty("lastName", equalTo(sourceUser.getLastName())),
                    hasProperty("email", equalTo(sourceUser.getEmail()))
            )));
        }
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