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
        } else if (arch.trim().equals("")) {
            throw new IllegalArgumentException(Constants.STRING_IS_EMPTY);
        } else if (devices == null) {
            throw new IllegalArgumentException(Constants.ARRAY_IS_NULL);
        }

        if (arch.equalsIgnoreCase("arm")) {
            for (int i = 0; i < devices.length; i++) {
                if (devices[i] != null && devices[i].getProcessor().getArchitecture().equalsIgnoreCase("x86")) {
                    devices[i] = null;
                }
            }
        } else {
            for (int i = 0; i < devices.length; i++) {
                if (devices[i] != null && devices[i].getProcessor().getArchitecture().equalsIgnoreCase("arm")) {
                    devices[i] = null;
                }
            }
        }

        return devices;
    }

    public Device[] filterByProcessor(Device[] devices, String parameter, String value) {
        if (parameter == null) {
            throw new IllegalArgumentException(Constants.STRING_NULL);
        } else if (isCorrectParameter(parameter)) {
            throw new IllegalArgumentException(Constants.INCORRECT_PARAMETER);
        } else if (value == null) {
            throw new IllegalArgumentException(Constants.STRING_NULL);
        } else if (isCorrectValue(value)) {
            throw new IllegalArgumentException(Constants.INCORRECT_VALUE);
        } else if (devices == null) {
            throw new IllegalArgumentException(Constants.ARRAY_IS_NULL);
        }

        if (parameter.equalsIgnoreCase("frequency")) {
            for (int i = 0; i < devices.length; i++) {
                if (devices[i] != null && devices[i].getProcessor().getFrequency() != Double.parseDouble(value)) {
                    devices[i] = null;
                }
            }
        } else if (parameter.equalsIgnoreCase("bit capacity")) {
            for (int i = 0; i < devices.length; i++) {
                if (devices[i] != null && devices[i].getProcessor().getBitCapacity() != Integer.parseInt(value)) {
                    devices[i] = null;
                }
            }
        } else {
            for (int i = 0; i < devices.length; i++) {
                if (devices[i] != null && !devices[i].getProcessor().getCache().equalsIgnoreCase(value)) {
                    devices[i] = null;
                }
            }
        }
        return devices;
    }

    public Device[] filterByTotalMemory(Device[] devices, String pointer, int value) {
        if (value < 0) {
            throw new IllegalArgumentException(Constants.NON_POSITIVE_VALUE);
        } else if (pointer == null) {
            throw new IllegalArgumentException(Constants.STRING_NULL);
        } else if (isCorrectPointer(pointer)) {
            throw new IllegalArgumentException(Constants.INCORRECT_POINTER);
        } else if (devices == null) {
            throw new IllegalArgumentException(Constants.ARRAY_IS_NULL);
        }
        if (pointer.equalsIgnoreCase("less")) {
            for (int i = 0; i < devices.length; i++) {
                if (devices[i] != null && devices[i].getMemory().getTotalMemory() >= value) {
                    devices[i] = null;
                }
            }
        } else {
            for (int i = 0; i < devices.length; i++) {
                if (devices[i] != null && devices[i].getMemory().getTotalMemory() <= value) {
                    devices[i] = null;
                }
            }
        }
        return devices;
    }

    public Device[] filterByOccupiedMemory(Device[] devices, String pointer, double value) {
        if (value < 0) {
            throw new IllegalArgumentException(Constants.NON_POSITIVE_VALUE);
        } else if (pointer == null) {
            throw new IllegalArgumentException(Constants.STRING_NULL);
        } else if (isCorrectPointer(pointer)) {
            throw new IllegalArgumentException(Constants.INCORRECT_POINTER);
        } else if (devices == null) {
            throw new IllegalArgumentException(Constants.ARRAY_IS_NULL);
        }
        if (pointer.equalsIgnoreCase("less")) {
            for (int i = 0; i < devices.length; i++) {
                if (devices[i] != null && devices[i].getMemory().getOccupiedMemory() >= value) {
                    devices[i] = null;
                }
            }
        } else {
            for (int i = 0; i < devices.length; i++) {
                if (devices[i] != null && devices[i].getMemory().getOccupiedMemory() <= value) {
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

    private boolean isCorrectParameter(String parameter) {
        if (!parameter.equalsIgnoreCase("frequency")) {
            if (!parameter.equalsIgnoreCase("bit capacity")) {
                return !parameter.equalsIgnoreCase("cache");
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    private boolean isCorrectValue(String value) {
        if (!value.matches(Constants.IS_CORRECT_VALUE_REGEX_1)) {
            return !value.matches(Constants.IS_CORRECT_VALUE_REGEX_2);
        } else {
            return false;
        }
    }

    private boolean isCorrectArch(String arch) {
        if (!arch.equalsIgnoreCase("arm")) {
            return !arch.equalsIgnoreCase("x86");
        } else {
            return false;
        }
    }

    private boolean isCorrectPointer(String pointer) {
        if (!pointer.equalsIgnoreCase("less")) {
            return !pointer.equalsIgnoreCase("more");
        }
        return false;
    }
}
