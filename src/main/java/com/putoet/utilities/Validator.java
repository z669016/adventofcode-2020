package com.putoet.utilities;

import org.jetbrains.annotations.NotNull;

public interface Validator<T> {
    boolean isValid(@NotNull T toValidate);
}
