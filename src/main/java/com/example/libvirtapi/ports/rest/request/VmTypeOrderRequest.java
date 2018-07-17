package com.example.libvirtapi.ports.rest.request;

import com.example.libvirtapi.domain.VmTypeId;
import lombok.Getter;

@Getter
public class VmTypeOrderRequest {
    private VmTypeId type;
}
