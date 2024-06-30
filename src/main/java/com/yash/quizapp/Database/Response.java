package com.yash.quizapp.Database;

import lombok.Data;

@Data
public class Response {
    private int id;
    private String answer;

    public Response(int id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public Response() {
    }
}
