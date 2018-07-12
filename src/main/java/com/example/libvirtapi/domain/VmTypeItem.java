package com.example.libvirtapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VmTypeItem {

    private VmType vmType;
    private VmTypeAvailability status;
    private VmTypePrice price;
}