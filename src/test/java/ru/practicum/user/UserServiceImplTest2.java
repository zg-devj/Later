package ru.practicum.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest
class UserServiceImplTest2 {

//    private final EntityManager em;
//    private final UserService userService;
//
////    @Test
////    void saveUser() {
////        // given
////        UserDto userDto = makeUserDto("some@email.com", "Пётр", "Иванов");
////
////        // when
////        userService.saveUser(userDto);
////
////        // then
////        TypedQuery<User> query = em.createQuery("Select u from User u where u.email = :email", User.class);
////        User user = query.setParameter("email", userDto.getEmail())
////                .getSingleResult();
////
////        assertThat(user.getId(), notNullValue());
////        assertThat(user.getFirstName(), equalTo(userDto.getFirstName()));
////        assertThat(user.getLastName(), equalTo(userDto.getLastName()));
////        assertThat(user.getEmail(), equalTo(userDto.getEmail()));
////        assertThat(user.getState(), equalTo(userDto.getState()));
////        assertThat(user.getRegistrationDate(), notNullValue());
////    }
//
//    @Test
//    void getAllUsers() {
//        // given
//        List<UserDto> sourceUsers = List.of(
//                makeUserDto("ivan@email", "Ivan", "Ivanov"),
//                makeUserDto("petr@email", "Petr", "Petrov"),
//                makeUserDto("vasilii@email", "Vasilii", "Vasiliev")
//        );
//
//        for (UserDto user : sourceUsers) {
//            User entity = UserMapper.mapToNewUser(user);
//            em.persist(entity);
//        }
//
//        // when
//        List<UserDto> targetUsers = userService.getAllUsers();
//
//        // then
//        assertThat(targetUsers, hasSize(sourceUsers.size()));
////        for (UserDto sourceUser : sourceUsers) {
////            assertThat(targetUsers, hasItem(allOf(
////                    hasProperty("id", notNullValue()),
////                    hasProperty("firstName", equalTo(sourceUser.getFirstName())),
////                    hasProperty("lastName", equalTo(sourceUser.getLastName())),
////                    hasProperty("email", equalTo(sourceUser.getEmail()))
////            )));
////        }
//    }
//
//    private UserDto makeUserDto(String email, String firstName, String lastName) {
//        UserDto dto = new UserDto();
//        dto.setEmail(email);
//        dto.setFirstName(firstName);
//        dto.setLastName(lastName);
//        dto.setState(UserState.ACTIVE);
//
//        return dto;
//    }
}