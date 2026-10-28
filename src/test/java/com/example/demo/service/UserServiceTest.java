package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    private UserEntity userEntity;
    private UserDto userDto;
    private final Integer licenceNumber = 123456;

    @BeforeEach
    public void setup() {
        userEntity = new UserEntity();
        userEntity.setLicenceNumber(licenceNumber);
        userDto = new UserDto(licenceNumber, null);
    }

    @Test
    void givenUsers_whenFindAll_thenReturnUsers() {
        when(userRepository.findAll()).thenReturn(List.of(userEntity));
        when(userMapper.toUserDto(userEntity)).thenReturn(userDto);

        List<UserDto> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals(userDto, result.getFirst());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void givenLicenceNumber_whenCreateUser_thenReturnUserDto() {

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.toUserDto(userEntity)).thenReturn(userDto);

        UserDto userDtoCreated = userService.createUser(licenceNumber);

        assertNotNull(userDtoCreated);
        assertEquals(userDto, userDtoCreated);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void givenLicenceNumber_whenDeleteUser_thenUserIsDeleted() {
        when(userRepository.findByLicenceNumber(licenceNumber)).thenReturn(userEntity);
        doNothing().when(userRepository).delete(any(UserEntity.class));

        userService.deleteUser(licenceNumber);

        verify(userRepository, times(1)).delete(userEntity);
    }
}
