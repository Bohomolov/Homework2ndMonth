package oophomework.device;

import oophomework.memory.Memory;
import oophomework.processors.Processor;

public class Device extends oophomework.device.parentdevise.Device {

    //1.void dataProcessing() – преобразование всех данных, записанных в памяти, во что преобразовываться?
    //2.Создать несколько экземпляров класса, это в мейне?

    public Device(Processor processor, Memory memory) {
        super(processor, memory);
    }

    public void save(String[] data) {
        memory.memoryCell = data;
    }

    public String[] readAll(){
        return memory.memoryCell;
    }

    public String getSystemInfo() {
        return "Processor: " + processor + "\nMemory: " + memory;
    }


}
