package com.example.libvirtapi.ports.db;

import com.example.libvirtapi.app.VmInventory;
import com.example.libvirtapi.domain.VmTypeId;
import com.example.libvirtapi.domain.VmTypeStocked;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JdbcVmInventory implements VmInventory {

    private static final BeanPropertyRowMapper<VmTypeStockedData> STOCKED_ROW_MAPPER = new BeanPropertyRowMapper<>(VmTypeStockedData.class);

    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void allocate(VmTypeId vmTypeId) {
        jdbcTemplate.update("UPDATE vm_type_inventory SET allocated = allocated + 1 WHERE type_id = ?", vmTypeId.getValue());
    }

    @Override
    @Transactional
    public void reserve(VmTypeId vmTypeId) {
        jdbcTemplate.update("UPDATE vm_type_inventory SET reserved = reserved + 1 WHERE type_id = ?", vmTypeId.getValue());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VmTypeStocked> stocked(VmTypeId vmTypeId) {
        List<VmTypeStockedData> result = jdbcTemplate.query(
                "SELECT type_id, stocked, reserved, allocated FROM vm_type_inventory WHERE type_id = ?",
                new Object[] {vmTypeId.getValue()},
                STOCKED_ROW_MAPPER);
        return result.size() == 0 ? Optional.empty() : Optional.of(result.get(0).asVmTypeStocked());
    }

    @Getter
    @Setter
    static class VmTypeStockedData {
        private String typeId;
        private int stocked;
        private int reserved;
        private int allocated;

        VmTypeStocked asVmTypeStocked() {
            return new VmTypeStocked(VmTypeId.from(typeId), stocked, reserved, allocated);
        }
    }
}
