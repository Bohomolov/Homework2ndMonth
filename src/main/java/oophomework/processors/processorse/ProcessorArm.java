package oophomework.processors.processorse;

import oophomework.processors.ProcessorBase;

public class ProcessorArm extends ProcessorBase {
    private final String architecture = "Arm";

    public ProcessorArm(double frequency, int bitCapacity, String cache) {
        super(frequency, bitCapacity, cache);
        super.architecture = this.architecture;
    }

    public String dataProcess(String data) {
        return data.toLowerCase();
    }

    public String dataProcess(long data) {
        return "Data equals:" + data;
    }

}
