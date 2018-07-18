package com.example.libvirtapi.app;

import com.example.libvirtapi.domain.ex.VmTypeStockDepletedException;
import com.example.libvirtapi.ports.rest.request.VmTypeOrderRequest;
import com.example.libvirtapi.domain.Customer;
import com.example.libvirtapi.domain.VmType;
import com.example.libvirtapi.domain.VmTypeRepository;
import com.example.libvirtapi.domain.ex.VmTypeOverbookedException;
import com.example.libvirtapi.domain.ex.VmTypeMissingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

// todo: исполнение заявок через публикацию событий
// todo: отслеживание состояния заявки; как узнать какая именно заявка из 2х зарезервировала ВМ?
@Slf4j
@Validated
@AllArgsConstructor
public class AsyncOrders implements Orders {

    private Libvirt libvirt;
    private VmInventory vmInventory;
    private VmTypeRepository vmTypes;
    private Notifier notifier;

    @Override
    @Transactional
    public void create(@NotNull @Valid VmTypeOrderRequest request, @NotNull Customer customer) throws VmTypeMissingException, VmTypeOverbookedException {
        VmType vmType = vmTypes.get(request.getType()).orElseThrow(() -> new VmTypeMissingException(request.getType()));

        vmInventory.reserve(vmType.getId());

        CompletableFuture<VmType> cf = CompletableFuture.supplyAsync(() -> {
            libvirt.create(vmType);
            return vmType;
        });

        cf.thenAccept(_vmType -> {
            try {
                vmInventory.allocate(_vmType.getId());
            } catch (VmTypeMissingException | VmTypeStockDepletedException e) {
                log.error(e.getMessage(), e);
                cf.completeExceptionally(new CompletionException("", e));
            }
            notifier.orderSuccess(customer, _vmType);
        }).exceptionally(ex -> {
            log.error("VM cannot be created!", ex);
            return null;
        });
    }
}
