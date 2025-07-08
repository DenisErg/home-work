package sk.edenis.homeworkunion.utility;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import sk.edenis.homeworkunion.exception.InvalidFieldFormatException;
import sk.edenis.homeworkunion.exception.MissingFieldException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class FieldValidatorUtil {

    public static void validateString(String label, String field) {
        if (field == null || field.trim().isEmpty()) {
            throw new MissingFieldException("Pole " + label + " musí byť vyplnené!");
        }
    }

    public static String validateTextField(String label, String field) {
        validateString(label, field);
        if (!field.matches("^[\\p{L} ]+$")) {
            throw new InvalidFieldFormatException("Pole " + label + " nie je valídne!");
        }

        return field;
    }

    public static Integer validateInteger(String label, String field) {
        validateString(label, field);
        try {
            return Integer.parseInt(field.replace(" ", ""));
        } catch (NumberFormatException e) {
            throw new InvalidFieldFormatException("Pole " + label + "musí obsahovať len čísla!");
        }
    }

    public static Date validateDate(String label, String field) {
        validateString(label, field);

        if (!field.trim().matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new InvalidFieldFormatException("Dátum musí mať formát RRRR-MM-DD!");
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            return sdf.parse(field.trim());

        } catch (ParseException e) {
            throw new InvalidFieldFormatException("Dátum nemá platný formát!");
        }
    }

    public static Boolean validateBoolean(String label, String field) {
        validateString(label, field);

        String value = field.replace(" ", "").toLowerCase();

        if (!"true".equals(value) && !"false".equals(value)) {
            throw new InvalidFieldFormatException("Hodnota " + label + " musí byť 'true' alebo 'false'!");
        }

        return Boolean.parseBoolean(field);
    }

    public static UUID validateUUID(String label, String uuid) {
        validateString(label, uuid);
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new InvalidFieldFormatException("Id nie je valídne!");
        }
    }

    public static void validateContractMap(Map<String, Object> contractMap, List<String> fields) {
        for (String field : fields) {
            if (!contractMap.containsKey(field)) {
                throw new MissingFieldException("Chýbajúce pole " + field);
            }
        }
    }
}
