package com.example.login_society;

public class complaint {

        private String description;
        private String date;


        public complaint() {
        }

        public complaint(String description, String date) {
            this.description = description;
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

