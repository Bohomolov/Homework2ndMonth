package oophomework.processors;

import oophomework.processors.cachetype.CacheType;

public class ProcessorArm extends Processor {

    private final String architecture = "ARM";

    public ProcessorArm(double frequency, int bitCapacity, CacheType cache) {
        super(frequency, bitCapacity, cache);
    }

    public String dataProcess(String data) {
        return data.toLowerCase();
    }

   public String dataProcess(long data) {
        return "Data equals:" + data;
    }

}
