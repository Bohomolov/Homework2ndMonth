package oophomework;


import oophomework.device.Device;
import oophomework.device.deviceutilse.DeviceUtils;
import oophomework.memory.Memory;
import oophomework.processors.ProcessorArm;
import oophomework.processors.ProcessorX86;
import oophomework.processors.cachetype.CacheType;

import static oophomework.utils.Constants.x32;
import static oophomework.utils.Constants.x64;

public class Main {
    public static void main(String[] args) {
        Device device_0 = new Device(new ProcessorArm(1.5,x64,CacheType.L3),new Memory());
        Device device_1 = new Device(new ProcessorArm(2.5,x32,CacheType.L2),new Memory());
        Device device_2 = new Device(new ProcessorArm(3.5,x64,CacheType.L3),new Memory());
        Device device_3 = new Device(new ProcessorArm(0.5,x32,CacheType.L1),new Memory());
        Device device_4 = new Device(new ProcessorX86(2.0,x32,CacheType.L4),new Memory());
        Device device_5 = new Device(new ProcessorX86(1.0,x64,CacheType.L3),new Memory());
        Device device_6 = new Device(new ProcessorX86(1.5,x32,CacheType.L2),new Memory());
        Device device_7 = new Device(new ProcessorX86(2.0,x32,CacheType.L1),new Memory());
        Device device_8 = new Device(new ProcessorX86(1.5,x64,CacheType.L4),new Memory());
        Device device_9 = new Device(new ProcessorArm(1.8,x64,CacheType.L3),new Memory());
        Device[] devices = {device_0,device_1,device_2,device_3,device_4,device_5,device_6,device_7,device_8,device_9};
        DeviceUtils deviceUtils = new DeviceUtils();
        deviceUtils.filterByArch(devices,"x86");
        deviceUtils.deviceOutput(devices);

//        System.out.println(memory.readLast());
//        System.out.println(memory.removeLast());
//        System.out.println(memory.save("!!!"));
//        System.out.println(memory.readLast());
//        System.out.println(memory.getMemoryInfo());
//
//        System.out.println(processorArm.getDetails());
//        System.out.println(processorArm.dataProcess("daTa"));
//        System.out.println(processorArm.dataProcess(1000));
//
//        System.out.println(processorX86.getDetails());
//        System.out.println(processorX86.dataProcess("data"));
//        System.out.println(processorX86.dataProcess(1005));
//        String [] data = {"Some data1","Some data2","Some data3","Some data4","Some data5","Some data6","Some data7"};
//        Device device = new Device(processorArm,memory);
//        device.save(data);
//        memory.removeLast();
//        System.out.println(memory.readLast());
//        device.dataProcessing();
//        for (String s :device.readAll()) {
//            System.out.println(s);
//        }
//        System.out.println(device.getSystemInfo());
    }
}
