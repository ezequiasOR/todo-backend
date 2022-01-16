package com.ezequiasr.todo.utils;

import com.ezequiasr.todo.exception.RegisterNotFoundException;

import java.util.Optional;

public class Util {
    public static void checkEntityNotFound(Optional entity, String errorMessage) {
        if (entity.isEmpty()){
            throw new RegisterNotFoundException(errorMessage);
        }
    }
}
