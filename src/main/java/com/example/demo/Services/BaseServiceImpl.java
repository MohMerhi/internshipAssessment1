package com.example.demo.Services;

import com.example.demo.exceptions.InvalidRequestDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service

public class BaseServiceImpl implements BaseService {
    @Override
    public void updateEntity(Map<String, Object> entityDTO, Object entityToUpdate, Class entityToUpdateClass) {
        entityDTO.remove("createdBy");
        // Map key is field name, v is value
        entityDTO.forEach((k, v) -> {

            // use reflection to get field k on entityToUpdate and set it to value k
            Field field = ReflectionUtils.findField(entityToUpdateClass, k);
            if(field == null){
                return;
            }
            // convert value from type integer to type Long if the field is Long type
            if (field != null && field.getType().equals(Long.class) && v instanceof Integer)
                v = ((Integer) v).longValue();
            if (field != null && field.getType().equals(Double.class) && v instanceof Long)
                v = ((Long) v).doubleValue();
            if (field != null && field.getType().equals(Double.class) && v instanceof Integer)
                v = ((Integer) v).doubleValue();
            if (field != null && field.getType().equals(BigDecimal.class) && v instanceof Integer)
                v = BigDecimal.valueOf(((Integer) v).intValue());
            if (field != null && field.getType().equals(BigDecimal.class) && v instanceof Double)
                v = BigDecimal.valueOf(((Double) v).doubleValue());
            if (field.getType().equals(LocalDate.class) && v instanceof String) {
                v = LocalDate.parse((String) v, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }

            if (k.equals("suffix") && v != null) {
                v = v + "";
            }

            try {
                field.setAccessible(true);
            } catch (NullPointerException e) {
                throw new RuntimeException("field " + k + " not exist in " + entityToUpdateClass.getName());
            }
            ReflectionUtils.setField(field, entityToUpdate, v);
        });
    }
    public void validateDataTypes(Map<String, Object> data, String[] requiredDataFromRequest, Class<?>[] dataTypes) {
        List<String> errors = new ArrayList<>();

        for(int i = 0; i < requiredDataFromRequest.length; i++){
            if(!data.containsKey(requiredDataFromRequest[i])){
                errors.add("Required field " + requiredDataFromRequest[i] + "is missing");
            }
            else if(data.get(requiredDataFromRequest[i]) == null){
                    errors.add(requiredDataFromRequest[i] + " cannot be null");
            }
            else if(data.get(requiredDataFromRequest[i]).getClass().equals(dataTypes[i])){
                errors.add(requiredDataFromRequest[i] + " cannot be of type " + dataTypes[i].getName());
            }
        }

        // If any errors were found, throw our custom exception
        if (!errors.isEmpty()) {
            throw new InvalidRequestDataException("Request contains invalid data.", errors);
        }
    }

}
