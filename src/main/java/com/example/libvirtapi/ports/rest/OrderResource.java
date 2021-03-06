package com.example.libvirtapi.ports.rest;

import com.example.libvirtapi.ports.rest.request.VmTypeOrderRequest;
import com.example.libvirtapi.app.Orders;
import com.example.libvirtapi.domain.Customer;
import com.example.libvirtapi.domain.ex.VmTypeOverbookedException;
import com.example.libvirtapi.domain.ex.VmTypeMissingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderResource {

    private Orders orders;

    @PostMapping(consumes = "application/vnd.genezix.order.vm+json")
    public ResponseEntity orderVm(VmTypeOrderRequest request) throws VmTypeMissingException, VmTypeOverbookedException {
        Customer customer = new Customer(); // fixme: клиент извлекается из аутентификационных данных
        orders.create(request, customer);
        return ResponseEntity.created(URI.create("")).build();
    }
}
