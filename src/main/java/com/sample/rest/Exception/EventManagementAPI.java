package com.sample.rest.Exception;

import org.springframework.http.HttpStatus;

public class EventManagementAPI extends  RuntimeException{
        private HttpStatus status;
        private String message;

        public EventManagementAPI(HttpStatus status, String message) {
            this.status = status;
            this.message = message;
        }

        public EventManagementAPI(String message, HttpStatus status, String message1) {
            super(message);
            this.status = status;
            this.message = message1;
        }

        public HttpStatus getStatus() {
            return status;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

