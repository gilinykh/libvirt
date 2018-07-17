package com.example.libvirtapi.app;

import com.example.libvirtapi.adapters.request.VmTypeOrderRequest;
import com.example.libvirtapi.domain.Customer;
import com.example.libvirtapi.domain.VmType;
import com.example.libvirtapi.domain.VmTypeRepository;
import com.example.libvirtapi.domain.ex.VmTypeAlreadyReservedException;
import com.example.libvirtapi.domain.ex.VmTypeMissingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;

// todo: исполнение заявок через публикацию событий
@Slf4j
@Service
@Validated
@AllArgsConstructor
public class AsyncOrders implements Orders {

    private Notifier notifier;
    private Libvirt libvirt;
    private VmInventory vmInventory;
    private VmTypeRepository vmTypes;

    @Override
    @Transactional
    public void create(@NotNull @Valid VmTypeOrderRequest request, @NotNull Customer customer) throws VmTypeMissingException, VmTypeAlreadyReservedException {
        VmType vmType = vmTypes.get(request.getType()).orElseThrow(() -> new VmTypeMissingException(request.getType()));

        vmInventory.reserve(vmType);

        CompletableFuture
                .supplyAsync(() -> {
                    libvirt.create(vmType);
                    return vmType;
                })
                .exceptionally(ex -> {
                    log.error("VM cannot be created!", ex);
                    return null;
                })
                .thenAccept(_vmType -> {
                    vmInventory.allocate(_vmType);
                    notifier.orderSuccess(customer, _vmType);
                });

    }
}
