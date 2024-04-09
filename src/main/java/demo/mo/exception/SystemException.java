package demo.mo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemException extends RuntimeException {
    private String message;
    /**
     * 初期化
     * @param pMessage
     */
    public SystemException(final String pMessage) {
        this.message = pMessage;
    }

}
