package oophomework.device.deviceutilse.comporators;

import oophomework.device.Device;

import java.util.Comparator;

public class ArmComparator implements Comparator<Device> {
    @Override
    public int compare(Device o1, Device o2) {
        String sub1 = o1.getProcessor().getArchitecture();
        String sub2 = o2.getProcessor().getArchitecture();
        return sub1.compareTo(sub2);
    }
}
