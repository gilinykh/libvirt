package com.example.libvirtapi.app;

import com.example.libvirtapi.domain.VmType;
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
    public void allocate(VmType vmType) throws VmTypeMissingException, VmTypeStockDepletedException {
        VmTypeStocked vmTypeStocked = vmInventory.stocked(vmType).orElseThrow(() -> new VmTypeMissingException(vmType.getId()));

        if (vmTypeStocked.isFullyAllocated()) {
            throw new VmTypeStockDepletedException(vmType.getId());
        }

        vmInventory.allocate(vmType);
    }

    @Override
    public void reserve(VmType vmType) throws VmTypeOverbookedException, VmTypeMissingException {
        VmTypeStocked vmTypeStocked = vmInventory.stocked(vmType).orElseThrow(() -> new VmTypeMissingException(vmType.getId()));

        if (vmTypeStocked.isFullyReserved()) {
            throw new VmTypeOverbookedException(vmType.getId());
        }

        vmInventory.reserve(vmType);
    }

    @Override
    public Optional<VmTypeStocked> stocked(VmType vmType) {
        return vmInventory.stocked(vmType);
    }
}
