package com.example.libvirtapi.ports.db;

import com.example.libvirtapi.domain.VmType;
import com.example.libvirtapi.domain.VmTypeId;
import com.example.libvirtapi.domain.VmTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcVmTypeRepository implements VmTypeRepository {
    @Override
    public Optional<VmType> get(VmTypeId vmTypeId) {
        throw new UnsupportedOperationException("not implemented!");
    }
}
