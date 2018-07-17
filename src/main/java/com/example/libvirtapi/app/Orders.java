package com.example.libvirtapi.app;

import com.example.libvirtapi.ports.rest.request.VmTypeOrderRequest;
import com.example.libvirtapi.domain.Customer;
import com.example.libvirtapi.domain.ex.VmTypeOverbookedException;
import com.example.libvirtapi.domain.ex.VmTypeMissingException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface Orders {
    void create(@NotNull @Valid VmTypeOrderRequest request, @NotNull Customer customer) throws VmTypeMissingException, VmTypeOverbookedException;
}
