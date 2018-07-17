package com.example.libvirtapi.app;

import com.example.libvirtapi.domain.Customer;
import com.example.libvirtapi.domain.VmType;

public interface Notifier {
    void orderSuccess(Customer customer, VmType vmType);
}
