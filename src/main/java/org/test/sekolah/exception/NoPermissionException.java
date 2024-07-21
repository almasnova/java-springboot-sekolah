package org.test.sekolah.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoPermissionException extends RuntimeException {
    public NoPermissionException(String message) {
        super(message);
    }
}
