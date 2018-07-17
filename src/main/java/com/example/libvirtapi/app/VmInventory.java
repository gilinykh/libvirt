package com.example.libvirtapi.app;

import com.example.libvirtapi.domain.VmType;
import com.example.libvirtapi.domain.VmTypeStocked;
import com.example.libvirtapi.domain.ex.VmTypeOverbookedException;
import com.example.libvirtapi.domain.ex.VmTypeMissingException;
import com.example.libvirtapi.domain.ex.VmTypeStockDepletedException;

import java.util.Optional;

public interface VmInventory {
    void allocate(VmType vmType) throws VmTypeMissingException, VmTypeStockDepletedException;

    void reserve(VmType vmType) throws VmTypeOverbookedException, VmTypeMissingException;

    Optional<VmTypeStocked> stocked(VmType vmType);
}
