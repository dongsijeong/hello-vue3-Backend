package demo.mo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {
    private String message;

    private boolean noLog = false;

    /**
     * 初期化
     * @param pMessage
     */
    public BusinessException(final String pMessage) {
        this.message = pMessage;
        this.noLog = false;
    }

    /**
     * 初期化
     * @param pMessage
     * @param logout
     */
    public BusinessException(final String pMessage, final boolean logout) {
        this.message = pMessage;
        this.noLog = logout;
    }

    /**
     * log出力フラグ出力する。
     * @return log出力フラグ
     */
    public boolean getNoLog() {
        return this.noLog;
    }
}
