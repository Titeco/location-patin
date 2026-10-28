package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(userMapper::toUserDto).toList();
    }

    @Transactional(readOnly = true)
    public List<UserDto> getUsersWithSkates() {
        List<UserEntity> userEntitiesWithSkates = userRepository.findAllBySkatesNotNull();
        return userEntitiesWithSkates.stream().map(userMapper::toUserDto).toList();
    }

    @Transactional(readOnly = true)
    public UserDto getUserByLicenceNumber(Integer licenceNumber) {
        UserEntity userEntityByLicenceNumber = userRepository.findByLicenceNumber(licenceNumber);
        return userMapper.toUserDto(userEntityByLicenceNumber);
    }

    @Transactional
    public UserDto createUser(Integer licenceNumber) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLicenceNumber(licenceNumber);
        userEntity = userRepository.save(userEntity);
        return userMapper.toUserDto(userEntity);
    }

    @Transactional
    public void deleteUser(Integer licenceNumber) {
        UserEntity userToDelete = userRepository.findByLicenceNumber(licenceNumber);
        userRepository.delete(userToDelete);
    }
}
