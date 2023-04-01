package com.example.utaeventtracker;

import com.google.gson.annotations.SerializedName;

public class User {

        @SerializedName("username")
        private String username;

        @SerializedName("password")
        private String password;

        @SerializedName("phone_number")
        private String phoneNumber;

        @SerializedName("email")
        private String email;

        @SerializedName("address")
        private String address;

        @SerializedName("zipcode")
        private String zipcode;

        public User(String username, String password, String phoneNumber, String email, String address,String zipcode) {
            this.username = username;
            this.password = password;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.address = address;
            this.zipcode=zipcode;
        }
    }


