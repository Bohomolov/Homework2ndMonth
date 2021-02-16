package secondmounthlastpractice.string;

public class StringValidator {

    public boolean isStringCorrect(String string) {

        if (string == null || string.length() < 2) {
            return false;
        }

        if (string.charAt(0) == ']' || string.charAt(0) == ')' || string.charAt(0) == '}') {
            return false;
        }

        String validationString = string;
        validationString = validationString.replaceAll("[()\\[\\]{}]", "");

        if (validationString.length() > 0) {
            return false;
        }

        boolean result = false;

        for (int i = 0; i < string.length(); i++) {
            char symbol = string.charAt(i);
            if (symbol == '(' ||symbol == '{' || symbol == '[') {
                result = charValidation(symbol, i, string);
            }
        }

        return result;

    }

    private boolean charValidation(char symbol, int index, String string) {
        if (index == string.length()){
            return false;
        }
        char currentChar = string.charAt(index);
        if (symbol == '(' && currentChar == ')') {
            return true;
        } else if (symbol == '[' && currentChar == ']') {
            return true;
        } else if (symbol == '{' && currentChar == '}') {
            return true;
        }
        return charValidation(symbol, index +1, string);
    }
}
