package oophomework.processors.processorse;

import oophomework.processors.ProcessorBase;

public class ProcessorX86 extends ProcessorBase {

    private final String architecture = "x86";

    public ProcessorX86(double frequency, int bitCapacity, String cache) {
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
