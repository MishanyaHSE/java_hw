package serializer;

import org.junit.Test;
import serializable.classes.Person;
import serializable.classes.Student;
import serializable.classes.Teacher;
import serializer.JsonSerializer;
import serializer.SerializingNotAnnotatedObjectException;
import serializer.SerializingNullObjectException;
import static org.junit.jupiter.api.Assertions.*;

public class JsonSerializerTest {
    JsonSerializer serializer = new JsonSerializer();

    @Test
    public void correctSerializingPersonObject() throws SerializingNotAnnotatedObjectException,
            SerializingNullObjectException, IllegalAccessException {
        Person person = new Person("Oleg", "15", null, "AAAAAAAA");
        String personInJson = serializer.serializeToJson(person);
        String expectedString = "{ \n" +
                "\"name\": \"Oleg\" \n" +
                "\"AGE\": \"15\" \n" +
                "\"nullString\": \n" +
                "} \n";
        assertEquals(personInJson, expectedString);
    }

    @Test
    public void throwExceptionWhenTryClassWithoutAnnotation() throws SerializingNotAnnotatedObjectException,
            SerializingNullObjectException, IllegalAccessException {
        Teacher teacher = new Teacher("88");
        assertThrows(SerializingNotAnnotatedObjectException.class,
                () -> {
                    serializer.serializeToJson(teacher);
                });
    }

    @Test
    public void throwExceptionWhenObjectIsNull() throws SerializingNotAnnotatedObjectException,
            SerializingNullObjectException, IllegalAccessException {
        Person person = null;
        assertThrows(SerializingNullObjectException.class,
                () -> {
                    serializer.serializeToJson(person);
                });
    }

    @Test
    public void correctSerializingObjectWithoutNoNullAnnotation() throws SerializingNotAnnotatedObjectException,
            SerializingNullObjectException, IllegalAccessException {
        Student student = new Student("5.7", null);
        String personInJson = serializer.serializeToJson(student);
        String expectedString = "{ \n" +
                "\"gpa\": \"5.7\" \n" +
                "\"nullString\": \"null\" \n" +
                "} \n";
        assertEquals(personInJson, expectedString);
    }
}
