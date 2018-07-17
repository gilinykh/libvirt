package com.example.libvirtapi.app;

import com.example.libvirtapi.domain.VmType;

public interface Libvirt {
    void create(VmType vmType);
}
