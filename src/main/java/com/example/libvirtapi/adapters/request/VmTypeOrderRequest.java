package com.example.libvirtapi.adapters.request;

import com.example.libvirtapi.domain.VmTypeId;
import lombok.Getter;

@Getter
public class VmTypeOrderRequest {
    private VmTypeId type;
}
