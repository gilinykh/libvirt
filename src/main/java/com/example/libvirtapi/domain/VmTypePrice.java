package com.example.libvirtapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VmTypePrice {
    private int perHour;
    private int perDay;
}
