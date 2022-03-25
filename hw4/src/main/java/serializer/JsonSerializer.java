package serializer;

import java.lang.reflect.Field;

public class JsonSerializer {

    public boolean isSerializable(Object object) throws SerializingNullObjectException,
            SerializingNotAnnotatedObjectException {
        if(object == null) {
            throw new SerializingNullObjectException("Can not serialize null object");
        } else if (!object.getClass().isAnnotationPresent(JsonSerializable.class)) {
            throw new SerializingNotAnnotatedObjectException("Object is not annotated with @JsonSerializable.");

        }
        return true;
    }

    public String serializeToJson(Object object) throws SerializingNotAnnotatedObjectException,
            SerializingNullObjectException, IllegalAccessException {
        StringBuilder jsonString = new StringBuilder();
        if(isSerializable(object)) {
            jsonString.append("{ \n");
            Class<?> clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for(Field field: fields){
                boolean accessibleChanged = false;
                if(!field.isAccessible()) {
                    field.setAccessible(true);
                    accessibleChanged = true;
                }
                if(field.isAnnotationPresent(JsonElement.class)) {
                    String fieldName;
                    if(field.getAnnotation(JsonElement.class).fieldName().equals("")) {
                        fieldName = field.getName();
                    } else {
                        fieldName = field.getAnnotation(JsonElement.class).fieldName();
                    }
                    jsonString.append("\"").append(fieldName).append("\": ");
                    Object value = field.get(object);
                    if (value == null && !object.getClass().isAnnotationPresent(IgnoreNull.class)) {
                        jsonString.append("\"null\" \n");
                    } else if (value != null) {
                        jsonString.append("\"").append(value).append("\" \n");
                    } else {
                        jsonString.append("\n");
                    }
                }
                if(accessibleChanged){
                    field.setAccessible(false);
                }
            }
            jsonString.append("} \n");
        }
        return jsonString.toString();
    }
}
