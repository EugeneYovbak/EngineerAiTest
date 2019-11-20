package com.example.engineeraitest.domain.model;

import java.util.List;
import java.util.Objects;

public class User {

    private String name;
    private String image;
    private List<String> items;

    public User(String name, String image, List<String> items) {
        this.name = name;
        this.image = image;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                image.equals(user.image) &&
                items.equals(user.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, image, items);
    }
}
