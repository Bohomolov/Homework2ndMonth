package oophomework.processors;

public abstract class Processor {
    private final double frequency;
    private final int bitCapacity;
    private final String cache;

    protected String architecture;

    public double getFrequency() {
        return frequency;
    }

    public int getBitCapacity() {
        return bitCapacity;
    }

    public String getCache() {
        return cache;
    }

    public Processor(double frequency, int bitCapacity, String cache) {
        this.frequency = frequency;
        this.bitCapacity = bitCapacity;
        this.cache = cache;
    }

    public String getArchitecture() {
        return architecture;
    }

    protected abstract String dataProcess(String data);

    protected abstract String dataProcess(long data);

    public String getDetails() {
        return "Frequency: " + frequency + ',' +
                "Cache: " + cache + ',' +
                "Bit capacity: " + bitCapacity + ';';
    }
}
