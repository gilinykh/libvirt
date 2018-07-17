package com.example.libvirtapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

// "запасы" конкретного типа ВМ
@Getter
@AllArgsConstructor
public class VmTypeStocked {

    private VmTypeId vmTypeId;
    private int stocked;
    private int reserved;
    private int allocated;

    public boolean isFullyAllocated() {
        return allocated >= stocked;
    }

    public boolean isFullyReserved() {
        return reserved >= stocked;
    }
}
