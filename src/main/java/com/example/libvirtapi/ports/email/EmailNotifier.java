package com.example.libvirtapi.ports.email;

import com.example.libvirtapi.app.Notifier;
import com.example.libvirtapi.domain.Customer;
import com.example.libvirtapi.domain.VmType;
import org.springframework.stereotype.Service;

@Service
public class EmailNotifier implements Notifier {
    @Override
    public void orderSuccess(Customer customer, VmType vmType) {
        throw new UnsupportedOperationException("not implemented!");
    }
}
