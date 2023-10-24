package com.ssafy.hanol.member.domain;


import lombok.Getter;


@Getter
public class Token {

    private String token;
    public Token(String token) {
        this.token = token;
    }

    public static Token of(String token){
        return new Token(token);
    }


}
