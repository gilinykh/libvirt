package com.example.libvirtapi.domain;

import java.util.Optional;

public interface VmTypeRepository {
    Optional<VmType> get(VmTypeId vmTypeId);
}
