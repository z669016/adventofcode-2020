package com.putoet.utilities;

public interface Validator<T> {
    boolean isValid(T toValidate);
}
