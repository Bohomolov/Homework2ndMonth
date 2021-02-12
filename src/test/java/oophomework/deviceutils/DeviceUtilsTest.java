package oophomework.deviceutils;

import oophomework.device.Device;
import oophomework.device.deviceutilse.DeviceUtils;
import oophomework.memory.Memory;
import oophomework.processors.processorse.ProcessorArm;
import oophomework.processors.processorse.ProcessorX86;
import oophomework.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    void filterByArchTest0(Device[] devices, Device[] expected, String arch) {
        Device[] actual = deviceUtils.filterByArch(devices, arch);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void filterByArchTestExceptionsIncorrectArch() {
        Device[] devicesIncorrectArch = new Device[10];
        Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L2), new Memory());
        devicesIncorrectArch[0] = device_0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByArch(devicesIncorrectArch, "rrr");
        }, Constants.INCORRECT_ARCH);
    }

    @Test
    public void filterByArchTestExceptionsArrayIsNull() {
        Device[] devicesNull = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByArch(devicesNull, "arm");
        }, Constants.ARRAY_IS_NULL);
    }

    @Test
    public void filterByArchTestExceptionsEmptyString() {
        Device[] devicesIncorrectArch = new Device[10];
        Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L2), new Memory());
        devicesIncorrectArch[0] = device_0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByArch(devicesIncorrectArch, "");
        }, Constants.ARRAY_IS_NULL);
    }

    //==================================================== by total memory =====================================================
    static Stream<Arguments> filterByTotalMemoryTest() {

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

        Device[] devicesTestLess5 = {device_0, device_1, device_2, device_3, device_4, device_5, device_6, device_7};
        Device[] devicesTestMore5 = {device_0, device_1, device_2, device_3, device_4, device_5, device_6, device_7};
        Device[] devicesTestLess5Exp = {null, device_1, null, null, null, device_5, null, null};
        Device[] devicesTestMore5Exp = {device_0, null, device_2, null, device_4, null, device_6, device_7};
        Device[] devicesTestArrayNull = {null};
        Device[] devicesTestArrayNullExp = {null};

        return Stream.of(
                Arguments.arguments(devicesTestLess5, devicesTestLess5Exp, "less", 5),
                Arguments.arguments(devicesTestMore5, devicesTestMore5Exp, "more", 5),
                Arguments.arguments(devicesTestArrayNull, devicesTestArrayNullExp, "less", 1)
        );
    }

    @ParameterizedTest(name = "Filter by total memory. Input data: {0}, {1}")
    @MethodSource("filterByTotalMemoryTest")
    void filterByTotalMemoryTestMain(Device[] devices, Device[] expected, String pointer, int value) {
        Device[] actual = deviceUtils.filterByTotalMemory(devices, pointer, value);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void filterByTotalMemoryTestExceptionsIncorrectNonPositiveValue() {
        Device[] devicesIncorrectArch = new Device[10];
        Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L2), new Memory());
        devicesIncorrectArch[0] = device_0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByTotalMemory(devicesIncorrectArch, "less", -5);
        }, Constants.INCORRECT_ARCH);
    }

    @Test
    public void filterByTotalMemoryTestExceptionsArrayIsNull() {
        Device[] devicesNull = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByTotalMemory(devicesNull, "less", 8);
        }, Constants.ARRAY_IS_NULL);
    }

    @Test
    public void filterByTotalMemoryTestExceptionsIncorrectPointer() {
        Device[] devicesNull = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByTotalMemory(devicesNull, "leddss", 8);
        }, Constants.ARRAY_IS_NULL);
    }

    @Test
    public void filterByTotalMemoryTestExceptionsEmptyString() {
        Device[] devicesIncorrectArch = new Device[10];
        Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L2), new Memory());
        devicesIncorrectArch[0] = device_0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByTotalMemory(devicesIncorrectArch, "", 5);
        }, Constants.ARRAY_IS_NULL);
    }

    //===================================================== filterByOccupiedMemory ========================
    static Stream<Arguments> filterByOccupiedMemoryTest() {

        Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L2), new Memory());
        Device device_1 = new Device(new ProcessorX86(1.0, Constants.x64, L1), new Memory());
        Device device_2 = new Device(new ProcessorX86(2.0, Constants.x64, L3), new Memory());
        Device device_3 = new Device(new ProcessorArm(2.0, Constants.x64, L4), new Memory());
        Device device_4 = new Device(new ProcessorArm(2.0, Constants.x32, L3), new Memory());
        Device device_5 = new Device(new ProcessorArm(3.0, Constants.x64, L3), new Memory());
        Device device_6 = new Device(new ProcessorX86(3.0, Constants.x64, L3), new Memory());
        Device device_7 = new Device(new ProcessorX86(3.0, Constants.x64, L3), new Memory());

        String[] data = new String[]{"Some data1", "Some data2", "Some data3", "Some data4", "Some data5", "Some data6", "Some data7", "Some data8", "Some data9"};
        String[] data1 = new String[]{null, null, "Some data3"};
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

        Device[] devicesTestLess50 = {device_0, device_1, device_2, device_3, device_4, device_5, device_6, device_7};
        Device[] devicesTestMore50 = {device_0, device_1, device_2, device_3, device_4, device_5, device_6, device_7};
        Device[] devicesTestLess50Exp = {null, device_1, null, null, null, null, null, null};
        Device[] devicesTestMore50Exp = {device_0, null, device_2, device_3, device_4, device_5, device_6, device_7};
        Device[] devicesTestArrayNull = {null};
        Device[] devicesTestArrayNullExp = {null};

        return Stream.of(
                Arguments.arguments(devicesTestLess50, devicesTestLess50Exp, "less", 50.0),
                Arguments.arguments(devicesTestMore50, devicesTestMore50Exp, "more", 50.0),
                Arguments.arguments(devicesTestArrayNull, devicesTestArrayNullExp, "less", 1)
        );
    }

    @ParameterizedTest(name = "filterByOccupiedMemoryTest. Input data: {0}, {1}")
    @MethodSource("filterByOccupiedMemoryTest")
    void filterByOccupiedMemoryTestMain(Device[] devices, Device[] expected, String pointer, double value) {
        Device[] actual = deviceUtils.filterByOccupiedMemory(devices, pointer, value);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void filterByOccupiedMemoryTestExceptionsIncorrectNonPositiveValue() {
        Device[] devicesIncorrectArch = new Device[10];
        Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L2), new Memory());
        devicesIncorrectArch[0] = device_0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByOccupiedMemory(devicesIncorrectArch, "less", -5);
        }, Constants.INCORRECT_ARCH);
    }

    @Test
    public void filterByOccupiedMemoryTestExceptionsArrayIsNull() {
        Device[] devicesNull = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByOccupiedMemory(devicesNull, "less", 8);
        }, Constants.ARRAY_IS_NULL);
    }

    @Test
    public void filterByOccupiedMemoryTestExceptionsIncorrectPointer() {
        Device[] devicesNull = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByOccupiedMemory(devicesNull, "leddss", 8);
        }, Constants.ARRAY_IS_NULL);
    }

    @Test
    public void filterByOccupiedMemoryTestExceptionsEmptyString() {
        Device[] devicesIncorrectArch = new Device[10];
        Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L2), new Memory());
        devicesIncorrectArch[0] = device_0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByOccupiedMemory(devicesIncorrectArch, "", 5);
        }, Constants.ARRAY_IS_NULL);
    }
    //===================================== filterByProcessor ==================================================
    static Stream<Arguments> filterByProcessorTest() {

        Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L2), new Memory());
        Device device_1 = new Device(new ProcessorX86(1.0, Constants.x64, L1), new Memory());
        Device device_2 = new Device(new ProcessorX86(2.0, Constants.x64, L3), new Memory());
        Device device_3 = new Device(new ProcessorArm(2.0, Constants.x64, L4), new Memory());
        Device device_4 = new Device(new ProcessorArm(2.0, Constants.x32, L3), new Memory());
        Device device_5 = new Device(new ProcessorArm(3.0, Constants.x64, L3), new Memory());
        Device device_6 = new Device(new ProcessorX86(3.0, Constants.x64, L3), new Memory());
        Device device_7 = new Device(new ProcessorX86(3.0, Constants.x64, L3), new Memory());

        String[] data = new String[]{"Some data1", "Some data2", "Some data3", "Some data4", "Some data5", "Some data6", "Some data7", "Some data8", "Some data9"};
        String[] data1 = new String[]{null, null, "Some data3"};
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

        Device[] devicesTestF = {device_0, device_1, device_2, device_3, device_4, device_5, device_6, device_7};
        Device[] devicesTestB = {device_0, device_1, device_2, device_3, device_4, device_5, device_6, device_7};
        Device[] devicesTestC = {device_0, device_1, device_2, device_3, device_4, device_5, device_6, device_7};
        Device[] devicesTestFExp = {null, null, null, null, null, device_5, device_6, device_7};
        Device[] devicesTestBExp = {null, device_1, device_2, device_3, null, device_5, device_6, device_7};
        Device[] devicesTestCExp = {null, null, null, device_3, null, null, null, null};
        Device[] devicesTestArrayNull = {null};
        Device[] devicesTestArrayNullExp = {null};

        return Stream.of(
                Arguments.arguments(devicesTestF, devicesTestFExp, "frequency", "3.0"),
                Arguments.arguments(devicesTestB, devicesTestBExp, "bit capacity", "64"),
                Arguments.arguments(devicesTestC, devicesTestCExp, "cache", "L4"),
                Arguments.arguments(devicesTestArrayNull, devicesTestArrayNullExp, "cache", "L1")
        );
    }

    @ParameterizedTest(name = "filterByProcessorTest. Input data: {0}, {1}")
    @MethodSource("filterByProcessorTest")
    void filterByProcessorTestMain(Device[] devices, Device[] expected, String pointer, String value) {
        Device[] actual = deviceUtils.filterByProcessor(devices, pointer,value );
        assertArrayEquals(expected, actual);
    }

    @Test
    public void filterByProcessorTestExceptionsIncorrectNonPositiveValue() {
        Device[] devicesIncorrectArch = new Device[10];
        Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L2), new Memory());
        devicesIncorrectArch[0] = device_0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByProcessor(devicesIncorrectArch, "less", "");
        }, Constants.INCORRECT_ARCH);
    }

    @Test
    public void filterByProcessorTestExceptionsArrayIsNull() {
        Device[] devicesNull = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByProcessor(devicesNull, "less", "8");
        }, Constants.ARRAY_IS_NULL);
    }

    @Test
    public void filterByProcessorTestExceptionsIncorrectPointer() {
        Device[] devicesNull = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByProcessor(devicesNull, "leddss", "8");
        }, Constants.ARRAY_IS_NULL);
    }

    @Test
    public void filterByProcessorTestExceptionsEmptyString() {
        Device[] devicesIncorrectArch = new Device[10];
        Device device_0 = new Device(new ProcessorArm(1.5, Constants.x32, L2), new Memory());
        devicesIncorrectArch[0] = device_0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            deviceUtils.filterByProcessor(devicesIncorrectArch, "", "");
        }, Constants.ARRAY_IS_NULL);
    }
}