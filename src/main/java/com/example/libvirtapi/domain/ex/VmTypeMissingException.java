package com.example.libvirtapi.domain.ex;

import com.example.libvirtapi.domain.VmTypeId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VmTypeMissingException extends Exception {
    public VmTypeMissingException(VmTypeId id) {
        super("vmType [" + id.getValue() + "] not found!");
    }
}
