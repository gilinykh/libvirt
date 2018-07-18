package com.example.libvirtapi.app;

import com.example.libvirtapi.domain.VmTypeId;
import com.example.libvirtapi.domain.VmTypeStocked;
import com.example.libvirtapi.domain.ex.VmTypeOverbookedException;
import com.example.libvirtapi.domain.ex.VmTypeMissingException;
import com.example.libvirtapi.domain.ex.VmTypeStockDepletedException;

import java.util.Optional;

public interface VmInventory {
    void allocate(VmTypeId vmTypeId) throws VmTypeMissingException, VmTypeStockDepletedException;

    void reserve(VmTypeId vmTypeId) throws VmTypeOverbookedException, VmTypeMissingException;

    Optional<VmTypeStocked> stocked(VmTypeId vmTypeId);
}
