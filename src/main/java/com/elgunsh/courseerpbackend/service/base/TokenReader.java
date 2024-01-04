package com.elgunsh.courseerpbackend.service.base;

public interface TokenReader <T> {

    T read(String token);

}
