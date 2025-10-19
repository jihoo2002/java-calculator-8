package calculator.util;

public enum ErrorMessage {
    EMPTY_VALUE("입력 값이 비어있습니다."),
    INVALID_NUMBER("양수 이외의 다른 값이 전달되었습니다."),
    INVALID_DEFAULT_DELIMITER("기본 구분자의 형식이 잘못되어 있습니다."),
    INVALID_CUSTOM_DELIMITER_FORMAT("커스텀 구분자의 형식이 잘못되어 있습니다."),
    MULTIPLE_CUSTOM_DELIMITERS_NOT_ALLOWED("커스텀 구분자는 하나여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
