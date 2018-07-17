package com.example.libvirtapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class VmType {
    private VmTypeId id;
    private String name;
    private String cpuType;
    private int cpuCount;
    private int ram;
    private int ssd;
    private GpuInfo gpu;
    private String ip;

    @Getter
    @AllArgsConstructor
    public static class GpuInfo {
        private String type;
        private Collection<GpuUnitDetails> units;
    }

    @Getter
    @AllArgsConstructor
    public static class GpuUnitDetails {
        private String address;
    }
}
