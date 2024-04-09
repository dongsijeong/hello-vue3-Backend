package demo.mo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class CheckInputException extends RuntimeException {
    private String message;

    private String fields;
    /**
     * 初期化
     * @param pMessage
     */
    public CheckInputException(final String pMessage) {
        this.message = pMessage;
    }

    /**
     * 初期化
     * @param pMessage
     * @param pfieds
     */
    public CheckInputException(final String pMessage, final String pfieds) {
        this.message = pMessage;
        this.fields = pfieds;
    }

}
