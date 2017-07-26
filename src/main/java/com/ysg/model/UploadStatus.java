package com.ysg.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thaslim on 22/04/17.
 */
public class UploadStatus {

    @Getter
    @Setter
    private int processed;

    @Getter
    @Setter
    private List<Error> errors;

    public UploadStatus() {
        errors = new ArrayList<>();
    }

    public static class Error {

        @Getter
        @Setter
        private String name;

        @Getter
        @Setter
        private String status;

        public Error() {
        }

        public Error(String name, String status) {
            this.name = name;
            this.status = status;
        }
    }
}
