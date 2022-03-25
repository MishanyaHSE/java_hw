package serializable.classes;

import serializer.JsonElement;
import serializer.JsonSerializable;

@JsonSerializable
public class Student {
    @JsonElement
    private String gpa;
    @JsonElement
    private String nullString;

    public Student(String gpa, String nullString) {
        this.gpa = gpa;
        this.nullString = nullString;
    }
}
