package tr.gov.hmb.survey.exception;

import lombok.Data;

@Data
public class UserException extends Exception {

    private final int code;
    private final String message;

    /**
     * Kullanicilar icin kullanilacak olan exception sinifi.
     * @param code
     * @param message
     */
    public UserException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
