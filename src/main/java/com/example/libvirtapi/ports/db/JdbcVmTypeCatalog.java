package com.example.libvirtapi.ports.db;

import com.example.libvirtapi.app.VmTypeCatalog;
import com.example.libvirtapi.domain.VmTypeItem;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class JdbcVmTypeCatalog implements VmTypeCatalog {
    @Override
    public Collection<VmTypeItem> items() {
        throw new UnsupportedOperationException("not implemented!");
    }
}
