package oophomework;


import oophomework.device.Device;
import oophomework.memory.Memory;
import oophomework.processors.ProcessorArm;
import oophomework.processors.ProcessorX86;
import oophomework.processors.cachetype.CacheType;
import oophomework.utils.Constants;

public class Main {
    public static void main(String[] args) {
        Memory memory = new Memory();
//        memory.memoryCell = new String[]{"Hello", "world", null, "!", "I", "25", null, null, "collections"};
        ProcessorArm processorArm = new ProcessorArm(2.5, Constants.x_32, CacheType.L1);
        ProcessorX86 processorX86 = new ProcessorX86(3.2,Constants.x_64,CacheType.L3);
//
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
        String [] data = {"Some data1","Some data2","Some data3","Some data4","Some data5","Some data6","Some data7"};
        Device device = new Device(processorArm,memory);
        device.save(data);
        for (String s :device.readAll()) {
            System.out.println(s);
        }
        System.out.println(device.getSystemInfo());
    }
}
