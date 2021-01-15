package oophomework.processors;

import oophomework.processors.cachetype.CacheType;

public abstract class Processor {
    private final double frequency;
    private final int bitCapacity;
    private final CacheType cache;

    public Processor(double frequency, int bitCapacity, CacheType cache) {
        this.frequency = frequency;
        this.bitCapacity = bitCapacity;
        this.cache = cache;
    }

    protected abstract String dataProcess(String data);

    protected abstract String dataProcess(long data);

    public String getDetails() {
        return "Frequency: " + frequency + ',' +
                "\nCache: " + cache + ',' +
                "\nBit capacity: " + bitCapacity + '.';
    }
}
