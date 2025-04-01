package com.formacion.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

//Ignora campos que llegan y no tenemos declarados
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserApiResponse {
	
    private List<User> users; // Lista de usuarios

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private int id;
        private String firstName;
        private String lastName;
        private String maidenName;
        private int age;
        private String gender;
        private String email;
        private String phone;
        private String username;
        private String password;
        private String birthDate;
        private String image;
        private String bloodGroup;
        private double height;
        private double weight;
        private String eyeColor;
        private Hair hair;
        private String ip;
        private Address address;
        private String macAddress;
        private String university;
        private Bank bank;
        private Company company;

        
        @Data
        public static class Hair {
            private String color;
            private String type;

        }
        @Data
        public static class Address {
            private String address;
            private String city;
            private String state;
            private String stateCode;
            private String postalCode;
            private Coordinates coordinates;
            private String country;

            @Data
            public static class Coordinates {
                private double lat;
                private double lng;

            }
        }
        @Data
        public static class Bank {
            private String cardExpire;
            private String cardNumber;
            private String cardType;
            private String currency;
            private String iban;
        }
        @Data
        public static class Company {
            private String department;
            private String name;
            private String title;
            private Address address;

        }
    }
}