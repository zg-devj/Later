package ru.practicum.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void getAllUsers() {
        // given
        final UserDto userDto = makeUserDto("some@email.com", "Пётр", "Иванов");

        when(userRepository.findAll()).thenReturn(List.of(UserMapper.mapToNewUser(userDto)));

        final List<UserDto> result = userService.getAllUsers();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEmail()).isEqualTo(userDto.getEmail());
        assertThat(result.get(0).getFirstName()).isEqualTo(userDto.getFirstName());
        assertThat(result.get(0).getLastName()).isEqualTo(userDto.getLastName());
        assertThat(result.get(0).getRegistrationDate()).isNotNull();

        verify(userRepository,times(1)).findAll();
    }

    private UserDto makeUserDto(String email, String firstName, String lastName) {
        UserDto dto = new UserDto();
        dto.setEmail(email);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setState(UserState.ACTIVE);

        return dto;
    }
}
