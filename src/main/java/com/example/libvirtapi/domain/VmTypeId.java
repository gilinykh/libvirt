package com.example.libvirtapi.domain;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class VmTypeId implements Serializable {
    private String value;

    private VmTypeId(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value == null");
        }
        this.value = value;
    }

    public static VmTypeId from(String value) {
        return new VmTypeId(value);
    }
}
