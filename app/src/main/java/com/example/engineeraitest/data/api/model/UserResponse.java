package com.example.engineeraitest.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName("status")
    private boolean status;
    @SerializedName("data")
    private UserDataInnerResponse data;

    public boolean getStatus() {
        return status;
    }

    public UserDataInnerResponse getData() {
        return data;
    }

    //It was too late when I found out has_more field, so I've decided to leave it as it is
    public class UserDataInnerResponse {

        @SerializedName("users")
        private List<UserDataResponse> users;

        public List<UserDataResponse> getUsers() {
            return users;
        }
    }

    public class UserDataResponse {

        @SerializedName("name")
        private String name;
        @SerializedName("image")
        private String image;
        @SerializedName("items")
        private List<String> items;

        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }

        public List<String> getItems() {
            return items;
        }
    }
}
