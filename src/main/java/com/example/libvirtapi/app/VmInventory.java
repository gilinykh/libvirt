package com.example.libvirtapi.app;

import com.example.libvirtapi.domain.VmType;
import com.example.libvirtapi.domain.ex.VmTypeAlreadyReservedException;

public interface VmInventory {
    void allocate(VmType vmType);

    void reserve(VmType vmType) throws VmTypeAlreadyReservedException;
}
