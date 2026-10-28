package com.example.demo.service;

import com.example.demo.dto.SkateDto;
import com.example.demo.entity.SkateEntity;
import com.example.demo.mapper.SkateMapper;
import com.example.demo.repository.SkateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SkateService {

    private final SkateRepository skateRepository;
    private final SkateMapper skateMapper;

    @Transactional(readOnly = true)
    public List<SkateDto> getAllSkates() {
        List<SkateEntity> skatesEntities = skateRepository.findAll();
        return skatesEntities.stream().map(skateMapper::toSkateDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SkateDto> getAvailableSkates() {
        List<SkateEntity> availableSkatesEntities = skateRepository.findByUserNotNull();
        return  availableSkatesEntities.stream().map(skateMapper::toSkateDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SkateDto> getAvailableSkatesBySize(Double size) {
        List<SkateEntity> availableSkatesEntitiesBySize = skateRepository.findBySizeAndUserNotNull(size);
        return  availableSkatesEntitiesBySize.stream().map(skateMapper::toSkateDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SkateDto> getSkateBySize(Double size) {
        List<SkateEntity> SkatesEntitiesBySize = skateRepository.findBySize(size);
        return SkatesEntitiesBySize.stream().map(skateMapper::toSkateDto).toList();
    }

    @Transactional
    public SkateDto createSkate (Double size) {
        SkateEntity skateEntity = new SkateEntity();
        skateEntity.setSize(size);

        SkateEntity skateEntitySaved = skateRepository.save(skateEntity);
        return skateMapper.toSkateDto(skateEntitySaved);
    }

    @Transactional
    public void deleteSkate (SkateDto skateDto) {
        SkateEntity skateToDelete = skateMapper.toSkateEntity(skateDto);
        skateRepository.delete(skateToDelete);
    }

}
