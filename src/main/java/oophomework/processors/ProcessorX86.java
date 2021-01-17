package oophomework.processors;

import oophomework.processors.cachetype.CacheType;

public class ProcessorX86 extends Processor {

    private final String architecture = "x86";

    public ProcessorX86(double frequency, int bitCapacity, CacheType cache) {
        super(frequency, bitCapacity, cache);
        super.architecture = this.architecture;
    }

    public String dataProcess(String data) {
        return data.toUpperCase();
    }

    public String dataProcess(long data) {
        return "Data = " + data + '|';
    }
}
