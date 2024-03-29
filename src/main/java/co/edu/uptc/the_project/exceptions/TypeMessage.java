package co.edu.uptc.the_project.exceptions;

import org.springframework.http.HttpStatus;

public enum TypeMessage {
    NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "Not Found", 404),
    NOT_SAVED(HttpStatus.BAD_REQUEST.value(), "Not Saved", 410),
    INFORMATION_INCOMPLETE(HttpStatus.BAD_REQUEST.value(), "Infomration Incomplete", 430),
    NOT_FOUND_FILE(HttpStatus.BAD_REQUEST.value(), "Not Found file", 420),
    SAVED(HttpStatus.OK.value(), "Saved", 210);

    public final String message;
    public final int code;
    public final int codeHttp;

    private TypeMessage(int codeHttp, String message, int code) {
        {
            this.message = message;
            this.code = code;
            this.codeHttp = codeHttp;
        }

    }

}