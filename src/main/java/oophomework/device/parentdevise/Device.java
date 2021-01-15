package oophomework.device.parentdevise;

import oophomework.memory.Memory;
import oophomework.processors.Processor;

public abstract class Device {
    protected final Processor processor;
    protected final Memory memory;

    protected Device(Processor processor, Memory memory) {
        this.processor = processor;
        this.memory = memory;
    }
}
