package com.example.libvirtapi.ports.libvirt;

import com.example.libvirtapi.app.Libvirt;
import com.example.libvirtapi.domain.VmType;
import org.springframework.stereotype.Service;

@Service
public class JnaLibvirt implements Libvirt {
    @Override
    public void create(VmType vmType) {
        throw new UnsupportedOperationException("not implemented!");
    }
}
