package serializable.classes;

import serializer.JsonElement;

public class Teacher {
    @JsonElement
    private String age;

    public Teacher(String age) {
        this.age = age;
    }
}
