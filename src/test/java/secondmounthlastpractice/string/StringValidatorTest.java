package secondmounthlastpractice.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringValidatorTest {
    private final StringValidator stringValidator;

    StringValidatorTest() {
        stringValidator = new StringValidator();
    }

    static Stream<Arguments> isStringCorrectTest(){
        String str1 = ")(){{}}[]";
        String str2 = "(){{}}[]";
        String str3 = "(){{}}[](";
        String str4 = "{(){{}}[]((())){}]";
        String str5 = "[(){{}}[]((())){}]";
        String str6 = "";
        String str7 = "(";
        String str8 = ")(";
        String str9 = "[)";
        String str10 = "[(]{}";
        String str11 = "[][}";

        return Stream.of(
                Arguments.arguments(str5, true),
                Arguments.arguments(str1, false),
                Arguments.arguments(str2, true),
                Arguments.arguments(str3, false),
                Arguments.arguments(str4, false),
                Arguments.arguments(str6, false),
                Arguments.arguments(str7, false),
                Arguments.arguments(str8, false),
                Arguments.arguments(str9, false),
                Arguments.arguments(str10, false),
                Arguments.arguments(str11, false)
        );
    }
    @ParameterizedTest(name = "isStringCorrect. {0},{1}")
    @MethodSource("isStringCorrectTest")
    void isStringCorrectTestMain(String string, boolean expected){
        boolean actual = stringValidator.isStringCorrect(string);
        assertEquals(expected,actual);
    }

}