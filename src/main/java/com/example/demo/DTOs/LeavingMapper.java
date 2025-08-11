package com.example.demo.DTOs;

import com.example.demo.Models.Leaving;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeavingMapper {
    LeavingDTO toLeavingDTO(Leaving leaving);
}
