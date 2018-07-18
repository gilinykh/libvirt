package com.example.libvirtapi.app;

import com.example.libvirtapi.domain.VmTypeId;
import com.example.libvirtapi.domain.VmTypeStocked;
import com.example.libvirtapi.domain.ex.VmTypeOverbookedException;
import com.example.libvirtapi.domain.ex.VmTypeMissingException;
import com.example.libvirtapi.domain.ex.VmTypeStockDepletedException;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// Учет vm проверяющий правила
@AllArgsConstructor
public class ObservantVmInventory implements VmInventory {

    private VmInventory vmInventory;

    @Override
    @Transactional
    public void allocate(VmTypeId vmTypeId) throws VmTypeMissingException, VmTypeStockDepletedException {
        VmTypeStocked vmTypeStocked = vmInventory.stocked(vmTypeId).orElseThrow(() -> new VmTypeMissingException(vmTypeId));

        if (vmTypeStocked.isFullyAllocated()) {
            throw new VmTypeStockDepletedException(vmTypeId);
        }

        vmInventory.allocate(vmTypeId);
    }

    @Override
    @Transactional
    public void reserve(VmTypeId vmTypeId) throws VmTypeOverbookedException, VmTypeMissingException {
        VmTypeStocked vmTypeStocked = vmInventory.stocked(vmTypeId).orElseThrow(() -> new VmTypeMissingException(vmTypeId));

        if (vmTypeStocked.isFullyReserved()) {
            throw new VmTypeOverbookedException(vmTypeId);
        }

        vmInventory.reserve(vmTypeId);
    }

    @Override
    public Optional<VmTypeStocked> stocked(VmTypeId vmTypeId) {
        return vmInventory.stocked(vmTypeId);
    }
}
