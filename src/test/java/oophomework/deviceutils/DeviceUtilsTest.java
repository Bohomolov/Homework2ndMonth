package oophomework.deviceutils;

import oophomework.device.Device;
import oophomework.device.deviceutilse.DeviceUtils;
import oophomework.memory.Memory;
import oophomework.processors.processorse.ProcessorArm;
import oophomework.processors.processorse.ProcessorX86;
import oophomework.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static oophomework.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DeviceUtilsTest {
    private final DeviceUtils deviceUtils;

    public DeviceUtilsTest() {
        deviceUtils = new DeviceUtils();
    }

    //==================================================== by arch =====================================================
    static Stream<Arguments> filterByArchTest() {

        Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L2), new Memory());
        Device device_1 = new Device(new ProcessorX86(1.0, Constants.x64, L1), new Memory());
        Device device_2 = new Device(new ProcessorX86(2.0, Constants.x64, L3), new Memory());
        Device device_3 = new Device(new ProcessorArm(2.0, Constants.x64, L4), new Memory());
        Device device_4 = new Device(new ProcessorArm(2.0, Constants.x32, L3), new Memory());
        Device device_5 = new Device(new ProcessorArm(3.0, Constants.x64, L3), new Memory());
        Device device_6 = new Device(new ProcessorX86(3.0, Constants.x64, L3), new Memory());
        Device device_7 = new Device(new ProcessorX86(3.0, Constants.x64, L3), new Memory());

        String[] data = new String[]{"Some data1", "Some data2", "Some data3", "Some data4", "Some data5", "Some data6", "Some data7", "Some data8", "Some data9"};
        String[] data1 = new String[]{"Some data1", "Some data2", "Some data3"};
        String[] data2 = new String[]{"Some data1", "Some data2", "Some data3", "Some data4", "Some data5", "Some data6"};
        String[] data3 = new String[]{"Some data1", null, "Some data3", "Some data4", "Some data5"};
        String[] data4 = new String[]{"Some data1", "Some data2", "Some data3", "Some data4", null, "Some data6"};
        String[] data5 = new String[]{"Some data1", "Some data2", "Some data3", "Some data4"};
        String[] data6 = new String[]{"Some data1", "Some data2", null, "Some data4", "Some data5", "Some data6", "Some data7", "Some data8"};
        String[] data7 = new String[]{"Some data1", "Some data2", null, "Some data4", "Some data5", "Some data6", "Some data7", "Some data8"};

        device_0.save(data);
        device_1.save(data1);
        device_2.save(data2);
        device_3.save(data3);
        device_4.save(data4);
        device_5.save(data5);
        device_6.save(data6);
        device_7.save(data7);

        Device[] devicesTestX86Array = {device_0, device_1, device_2, device_3, device_4, device_5, device_6, device_7};
        Device[] devicesTestArmArray = {device_0, device_1, device_2, device_3, device_4, device_5, device_6, device_7};
        Device[] devicesTestArrayX86Exp = {null, device_1, device_2, null, null, null, device_6, device_7};
        Device[] devicesTestArrayArmExp = {device_0, null, null, device_3, device_4, device_5, null, null};
        Device[] devicesTestArrayNull = {null};
        Device[] devicesTestArrayNullExp = {null};

        return Stream.of(
                Arguments.arguments(devicesTestX86Array, devicesTestArrayX86Exp, "x86"),
                Arguments.arguments(devicesTestArmArray, devicesTestArrayArmExp, "arm"),
                Arguments.arguments(devicesTestArrayNull, devicesTestArrayNullExp, "x86")
        );
    }

    @ParameterizedTest(name = "Filter by architecture. Input data: {0}, {1}")
    @MethodSource("filterByArchTest")
    void filterByArchTest_0(Device[] devices, Device[] expected, String arch) {
        Device[] actual = deviceUtils.filterByArch(devices, arch);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void filterByArchTest_Exceptions_incorrect_arch() {
        Device[] devicesIncorrectArch = new Device[10];
        Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L2), new Memory());
        devicesIncorrectArch[0] = device_0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByArch(devicesIncorrectArch, "rrr");
        }, Constants.INCORRECT_ARCH);
    }

    @Test
    public void filterByArchTest_Exceptions_array_is_null() {
        Device[] devicesNull = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByArch(devicesNull, "arm");
        }, Constants.ARRAY_IS_NULL);
    }

    @Test
    public void filterByArchTest_Exceptions_empty_string() {
        Device[] devicesIncorrectArch = new Device[10];
        Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L2), new Memory());
        devicesIncorrectArch[0] = device_0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByArch(devicesIncorrectArch, "");
        }, Constants.ARRAY_IS_NULL);
    }

    //==================================================== by arch =====================================================

}