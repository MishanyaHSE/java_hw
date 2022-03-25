package serializable.classes;

import serializer.IgnoreNull;
import serializer.JsonElement;
import serializer.JsonSerializable;

@JsonSerializable
@IgnoreNull
public class Person {
    @JsonElement
    private String name;
    @JsonElement(fieldName = "AGE")
    private String age;
    @JsonElement
    private String nullString;

    private String notAnnotatedString;

    public Person(String name, String age, String nullString, String notAnnotatedString) {
        this.name = name;
        this.age = age;
        this.nullString = nullString;
        this.notAnnotatedString = notAnnotatedString;
    }
}
