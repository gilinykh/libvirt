package com.example.libvirtapi.domain.ex;

import com.example.libvirtapi.domain.VmTypeId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class VmTypeStockDepletedException extends Exception {
    public VmTypeStockDepletedException(VmTypeId id) {
        super("All available VMs of type [" + id.getValue() + "] are in use. Cannot allocate new one.");
    }
}
