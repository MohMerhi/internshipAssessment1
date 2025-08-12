package com.example.demo.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                v = LocalDate.parse((String) v, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
}
