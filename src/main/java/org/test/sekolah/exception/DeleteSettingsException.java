package org.test.sekolah.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteSettingsException extends RuntimeException {
    List<String> listOfResponses;

    public DeleteSettingsException(List<String> listOfResponses, String s){
        super(s);
        this.listOfResponses = listOfResponses;
    }
}
