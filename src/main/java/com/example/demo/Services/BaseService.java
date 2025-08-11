package com.example.demo.Services;

import java.util.Map;

public interface BaseService {
    void updateEntity(Map<String, Object> entityDTO, Object entityToUpdate, Class entityToUpdateClass);

}
