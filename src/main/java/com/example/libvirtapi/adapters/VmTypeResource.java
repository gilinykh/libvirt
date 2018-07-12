package com.example.libvirtapi.adapters;

import com.example.libvirtapi.adapters.representation.VmTypeItemRepresentation;
import com.example.libvirtapi.app.VmTypeCatalog;
import com.example.libvirtapi.domain.VmTypeItem;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/vm-types")
@AllArgsConstructor
public class VmTypeResource {

    private VmTypeCatalog vmTypeCatalog;

    @GetMapping
    public ResponseEntity<Collection<VmTypeItemRepresentation>> list() {
        Collection<VmTypeItem> vmTypes = vmTypeCatalog.items();
        return ResponseEntity.ok(vmTypes.stream().map(VmTypeItemRepresentation::new).collect(toList()));
    }
}
