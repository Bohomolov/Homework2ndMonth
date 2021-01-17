package oophomework.device.deviceutilse.comporators;

import oophomework.device.Device;

import java.util.Comparator;

public class X86Comparator implements Comparator<Device> {
    @Override
    public int compare(Device o1, Device o2) {
        String sub1 = o1.getProcessor().getArchitecture();
        String sub2 = o2.getProcessor().getArchitecture();
        if (sub1.equalsIgnoreCase("x86") && sub2.equalsIgnoreCase("arm")) {
            return -1;
        } else if (sub1.equalsIgnoreCase("x86") && sub2.equalsIgnoreCase("x86")) {
            return 0;
        } else {
            return 1;
        }
    }
}
