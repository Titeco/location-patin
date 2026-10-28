package com.example.demo.mapper;

import com.example.demo.dto.SkateDto;
import com.example.demo.entity.SkateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkateMapper {
    SkateEntity toSkateEntity (SkateDto skateDto);
    SkateDto toSkateDto (SkateEntity skateEntity);
}
