package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@AllArgsConstructor
@Data
@Service
public class RequestValidations {
    private static final Pattern NAME_PATTERN = Pattern.compile(
            "^[a-zA-Z\\s]+$"
    );

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    private static final Pattern IS_DIGITS_PATTERN = Pattern.compile("^\\d+$");
    private final View error;

    public void validateName(Map<String, Object> DTOmap, List<String> errors, String nameVar) {
        if (DTOmap.containsKey(nameVar)) {
            Object name = DTOmap.get(nameVar);
            if(name != null && name.getClass() == String.class) {
                if (!NAME_PATTERN.matcher(name.toString()).matches()) {
                    errors.add("Invalid " + nameVar + ": " + nameVar + " should contain only letters and shouldn't be null");
                }
                if (name.toString().length() < 2 || name.toString().length() > 50) {
                    errors.add("Invalid " +  nameVar + ": " + nameVar + " Name should be between 2 and 50 characters");
                }
            }

        }
        else{
            errors.add("'" + nameVar + "' field is required");
        }
    }

    public void validateEmail(Map<String, Object> DTOmap, List<String> errors) {
        if (DTOmap.containsKey("email")) {
            Object email = DTOmap.get("email");
            if(email != null && email.getClass() == String.class){
                if (!EMAIL_PATTERN.matcher(email.toString()).matches()) {
                    errors.add("Invalid email format");
                }
            }
            else{
                errors.add("email should be a String");
            }
        }
        else{
            errors.add("'email' field is required");
        }

    }

    public void validatePositiveInteger(Map<String, Object> DTOmap, String idName, List<String> errors, boolean allowZero) {
        if (DTOmap.containsKey(idName)) {
            Object id = DTOmap.get(idName);
            if(id != null && id.getClass() == Integer.class){
                if (Integer.parseInt(id.toString()) < 0 || (Integer.parseInt(id.toString()) == 0 && !allowZero)) {
                    errors.add("Invalid " + idName + ": " + idName + " should be a positive integer " + (allowZero ? "(zero allowed)" : "(zero not allowed)"));
                }
            }
            else{
                errors.add(idName + " should be a Integer");
            }
        }
        else{
            errors.add("'" + idName + "' field is required");
        }
    }

    public void validateDate(Map<String, Object> DTOmap, String dateName, List<String> errors) {
        if(DTOmap.containsKey(dateName)){
            Object date = DTOmap.get(dateName);
            if(date != null && date.getClass() == String.class){
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                try {
                    LocalDate.parse(date.toString().trim(), dateFormatter);
                } catch (DateTimeParseException e) {
                    errors.add("Invalid " + dateName + ": " + dateName + " should be a valid date of form dd/MM/yyyy");
                }
            }
            else{
                errors.add(dateName + " should be a String");
            }
        }
        else{
            errors.add("'" + dateName + "' field is required");
        }
    }

    public void ValidatePositiveDouble(Map<String,Object> DTOmap, String numberName, List<String> errors) {
        if (DTOmap.containsKey(numberName)) {
            Object number = DTOmap.get(numberName);
            if(number != null && number.getClass() == Double.class){
                if((Double) number <= 0){
                    errors.add(numberName + " should be positive");
                }
            }
            else {
                errors.add(numberName + " should be a Double");
            }
        }
        else{
            errors.add("'" + numberName + "' field is required");
        }
    }
}
