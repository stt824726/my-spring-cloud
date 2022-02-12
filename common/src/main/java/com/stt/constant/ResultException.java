package com.stt.constant;

/**
 * 业务异常，若只传入msg不会有栈信息，一般用来回滚数据
 */
public final class ResultException extends RuntimeException {

    public ResultException(String msg) {
        super(msg, null, false, false);
    }

    public ResultException(String msg, Throwable throwable) {
        super(msg, throwable, false, true);
    }

    /**
     * 包含message, 可指定是否记录异常
     * @param msg
     * @param recordStackTrace
     */
    public ResultException(String msg, boolean recordStackTrace) {
        super(msg, null, false, recordStackTrace);
    }
}
