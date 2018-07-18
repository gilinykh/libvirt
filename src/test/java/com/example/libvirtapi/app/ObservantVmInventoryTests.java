package com.example.libvirtapi.app;

import com.example.libvirtapi.domain.VmTypeId;
import com.example.libvirtapi.domain.ex.VmTypeOverbookedException;
import com.example.libvirtapi.domain.ex.VmTypeStockDepletedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ObservantVmInventoryTests {

    @Autowired
    @Qualifier("observantVmInventory")
    private VmInventory vmInventory;

    @Test
    @SqlGroup({
            @Sql("/testdata/vmtype-records.sql"),
            @Sql("/testdata/inventory-records.sql")
    })
    public void givenStockedVmType_whenReserving_thenReserved() throws Exception {
        VmTypeId vmTypeId = VmTypeId.from("AI-2");
        assertEquals(0, vmInventory.stocked(vmTypeId).get().getReserved());

        vmInventory.reserve(vmTypeId);

        assertEquals(1, vmInventory.stocked(vmTypeId).get().getReserved());
    }

    @Test
    @SqlGroup({
            @Sql("/testdata/vmtype-records.sql"),
            @Sql("/testdata/inventory-records.sql")
    })
    public void givenStockedVmType_whenAllocating_thenAllocated() throws Exception {
        VmTypeId vmTypeId = VmTypeId.from("AI-2");
        assertEquals(0, vmInventory.stocked(vmTypeId).get().getAllocated());

        vmInventory.allocate(vmTypeId);

        assertEquals(1, vmInventory.stocked(vmTypeId).get().getAllocated());
    }

    @Test(expected = VmTypeOverbookedException.class)
    @SqlGroup({
            @Sql("/testdata/vmtype-records.sql"),
            @Sql("/testdata/inventory-records.sql")
    })
    public void givenStockedVmType_whenOverReserving_thenError() throws Exception {
        VmTypeId vmTypeId = VmTypeId.from("AI-2");

        vmInventory.reserve(vmTypeId);
        vmInventory.reserve(vmTypeId);
    }

    @Test(expected = VmTypeStockDepletedException.class)
    @SqlGroup({
            @Sql("/testdata/vmtype-records.sql"),
            @Sql("/testdata/inventory-records.sql")
    })
    public void givenStockedVmType_whenOverAllocating_thenError() throws Exception {
        VmTypeId vmTypeId = VmTypeId.from("AI-2");

        vmInventory.allocate(vmTypeId);
        vmInventory.allocate(vmTypeId);
    }
}
