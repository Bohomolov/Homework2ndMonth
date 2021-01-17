package oophomework.device.deviceutilse;

import oophomework.device.Device;
import oophomework.device.deviceutilse.comporators.ArmComparator;
import oophomework.device.deviceutilse.comporators.X86Comparator;
import oophomework.utils.Constants;

import java.util.Arrays;

public class DeviceUtils {

    public void deviceOutput(Device[] devices) {
        if (devices == null) {
            throw new IllegalArgumentException(Constants.ARRAY_IS_NULL);
        }
        for (Device d : devices) {
            if (d != null) {
                System.out.println(d);
            }
        }
    }

    public Device[] filterByArch(Device[] devices, String arch) {
        if (isCorrectArch(arch)) {
            throw new IllegalArgumentException(Constants.INCORRECT_ARCH);
        } else if (devices == null) {
            throw new IllegalArgumentException(Constants.ARRAY_IS_NULL);
        }

        if (arch.equalsIgnoreCase("arm")) {
            for (int i = 0; i < devices.length; i++) {
                if (devices[i].getProcessor().getArchitecture().equalsIgnoreCase("x86")) {
                    devices[i] = null;
                }
            }
        } else {
            for (int i = 0; i < devices.length; i++) {
                if (devices[i].getProcessor().getArchitecture().equalsIgnoreCase("arm")) {
                    devices[i] = null;
                }
            }
        }

        return devices;
    }

    public Device[] sortByArch(Device[] devices, String arch) {
        if (isCorrectArch(arch)) {
            throw new IllegalArgumentException(Constants.INCORRECT_ARCH);
        } else if (devices == null) {
            throw new IllegalArgumentException(Constants.ARRAY_IS_NULL);
        }

        if (arch.equalsIgnoreCase("arm")) {
            Arrays.sort(devices, new ArmComparator());
        } else {
            Arrays.sort(devices, new X86Comparator());
        }

        return devices;
    }

    private boolean isCorrectArch(String arch) {
        if (!arch.equalsIgnoreCase("arm")) {
            return !arch.equalsIgnoreCase("x86");
        } else {
            return false;
        }
    }
}
