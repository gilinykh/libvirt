package com.example.libvirtapi.domain.ex;

import com.example.libvirtapi.domain.VmTypeId;

public class VmTypeOverbookedException extends Exception {
    public VmTypeOverbookedException(VmTypeId id) {
        super("All available VMs of type [" + id.getValue() + "] are booked.");
    }
}
