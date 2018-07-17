package com.example.libvirtapi.ports.rest.representation;

import com.example.libvirtapi.domain.VmType;
import com.example.libvirtapi.domain.VmTypeAvailability;
import com.example.libvirtapi.domain.VmTypeItem;
import com.example.libvirtapi.domain.VmTypePrice;
import lombok.Getter;

// {
//   "type": {
//     "name": "AI-2",
//     "server": "SuperMicro SuperServer 4029GP-TRT2",
//     "gpu": {
//       "type": "Nvidia GTX1080Ti",
//       "ips": ["001232:25:0.1", "004758:72:0.1"]
//     },
//     "cpu": {
//       "type": "Dual Intel Xeon Gold 6152 Processor",
//       "count": 2
//     },
//     "ram": 256,
//     "ssd": 1000
//   },
//   "status": "IDLE",
//   "price": {
//     "hour": 1,
//     "day": 20
//   }
// }
@Getter
public class VmTypeItemRepresentation {

    private VmType vmType;
    private VmTypeAvailability status;
    private VmTypePrice price;

    public VmTypeItemRepresentation(VmTypeItem vmTypeItem) {
        this.vmType = vmTypeItem.getVmType();
        this.status = vmTypeItem.getStatus();
        this.price = vmTypeItem.getPrice();
    }
}
