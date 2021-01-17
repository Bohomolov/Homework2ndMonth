package oophomework.device;

import oophomework.memory.Memory;
import oophomework.processors.Processor;

public class Device extends oophomework.device.parentdevise.Device {


    public Device(Processor processor, Memory memory) {
        super(processor, memory);
    }

    public void save(String[] data) {
        memory.memoryCell = data;
    }

    public String[] readAll() {
        String[] temp = memory.memoryCell;
        for (String s : memory.memoryCell) {
            s = null;
        }
        return temp;
    }

    public void dataProcessing() {
        for (int i = 0; i < memory.memoryCell.length; i++) {
            if (memory.memoryCell[i] != null) {
                String temp = memory.memoryCell[i].toUpperCase();
                memory.memoryCell[i] = temp;
            }
        }
    }

    @Override
    public String toString() {
        return "Device{" +
                "architecture= " + processor.getArchitecture() +
                ',' + processor.getDetails() +
                 memory.getMemoryInfo() +
                '}';
    }

    public String getSystemInfo() {
        return "Processor: " + processor.getDetails() + "\nMemory: " + memory.getMemoryInfo();
    }


}
