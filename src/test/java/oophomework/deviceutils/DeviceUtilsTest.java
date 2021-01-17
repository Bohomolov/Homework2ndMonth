package oophomework.deviceutils;

import oophomework.device.Device;
import oophomework.device.deviceutilse.DeviceUtils;
import oophomework.memory.Memory;
import oophomework.processors.ProcessorArm;
import oophomework.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static oophomework.utils.Constants.L3;

class DeviceUtilsTest {
    private final DeviceUtils deviceUtils;
    private final Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L3), new Memory());
    private final Device[] devices = {device_0};
    private final Device[] devices_1 = null;

    public DeviceUtilsTest() {
        deviceUtils = new DeviceUtils();
    }

    @Test
    public void deviceUtilsTest_Exceptions_0() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.sortByArch(devices, "rrr");
        }, Constants.INCORRECT_ARCH);
    }

    @Test
    public void deviceUtilsTest_Exceptions_1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.sortByArch(devices_1, "arm");
        }, Constants.ARRAY_IS_NULL);
    }

}