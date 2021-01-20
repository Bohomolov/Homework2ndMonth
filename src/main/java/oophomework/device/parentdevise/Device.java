package oophomework.device.parentdevise;

import oophomework.memory.Memory;
import oophomework.processors.ProcessorBase;

public abstract class Device {
    protected final ProcessorBase processor;
    protected final Memory memory;

    protected Device(ProcessorBase processor, Memory memory) {
        this.processor = processor;
        this.memory = memory;
    }
    public ProcessorBase getProcessor(){
        return processor;
    }
    public Memory getMemory(){
        return memory;
    }
}
