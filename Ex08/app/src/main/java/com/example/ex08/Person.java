package com.example.ex08;

import java.util.ArrayList;

public class Person {
    String name , id , diploma , supplement;
    ArrayList<String> hobby ;

    public Person() {
    }

    public Person(String name, String id, String diploma, String supplement, ArrayList<String> hobby) {
        this.name = name;
        this.id = id;
        this.diploma = diploma;
        this.supplement = supplement;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getSupplement() {
        return supplement;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

    public String getHobby() {

        if (hobby.isEmpty()) {
            return "Không có sở thích";
        }

        StringBuilder hobbyString = new StringBuilder();
        for (int i = 0; i < hobby.size(); i++) {
            hobbyString.append(hobby.get(i));
            if (i < hobby.size() - 1) {
                hobbyString.append(" - ");
            }
        }
        return hobbyString.toString();
    }

    public void setHobby(ArrayList<String> hobby) {
        this.hobby = hobby;
    }
}
