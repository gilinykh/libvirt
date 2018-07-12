package com.example.libvirtapi.app;

import com.example.libvirtapi.domain.VmTypeItem;

import java.util.Collection;

public interface VmTypeCatalog {
    Collection<VmTypeItem> items();
}
