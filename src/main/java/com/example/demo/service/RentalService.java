package com.example.demo.service;

import com.example.demo.dto.SkateDto;
import com.example.demo.entity.SkateEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.SkateMapper;
import com.example.demo.repository.SkateRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RentalService {
    private final UserRepository userRepository;
    private final SkateRepository skateRepository;
    private final SkateMapper skateMapper;

    @Transactional
    public SkateDto rentSkate (Integer licenceNumber, SkateDto skateDto) {
        SkateEntity skateEntity = skateMapper.toSkateEntity(skateDto);
        UserEntity userEntity = userRepository.findByLicenceNumber(licenceNumber);
        skateEntity.setUserEntity(userEntity);
        SkateEntity savedSkate = skateRepository.save(skateEntity);
        return skateMapper.toSkateDto(savedSkate);
    }

    @Transactional
    public SkateDto returnSkate (SkateDto skateDto) {
        SkateEntity skateEntity = skateMapper.toSkateEntity(skateDto);
        skateEntity.setUserEntity(null);
        SkateEntity savedSkate = skateRepository.save(skateEntity);
        return skateMapper.toSkateDto(savedSkate);
    }
}
