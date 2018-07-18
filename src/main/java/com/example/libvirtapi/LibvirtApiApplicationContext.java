package com.example.libvirtapi;

import com.example.libvirtapi.app.*;
import com.example.libvirtapi.domain.VmTypeRepository;
import com.example.libvirtapi.ports.db.JdbcVmInventory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@EnableTransactionManagement(proxyTargetClass = true)
public class LibvirtApiApplicationContext {

    @Bean
    public ObservantVmInventory observantVmInventory(JdbcVmInventory jdbcVmInventory) {
        return new ObservantVmInventory(jdbcVmInventory);
    }

    @Bean
    public AsyncOrders asyncOrders(Libvirt libvirt, ObservantVmInventory observantVmInventory, VmTypeRepository vmTypeRepository, Notifier notifier) {
        return new AsyncOrders(libvirt, observantVmInventory, vmTypeRepository, notifier);
    }
}
