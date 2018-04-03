package jit.wxs.common.utils;

import java.io.Serializable;

/**
 * 上传图片返回值
 *
 * @version 1.0
 * @author 入云龙
 * @date 2015年7月22日下午2:09:02
 */
public class PictureResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上传图片返回值，成功：0	失败：1
     */
    private Integer error;
    /**
     * 回显图片使用的url
     */
    private String url;
    /**
     * 错误时的错误消息
     */
    private String message;

    public PictureResult() {

    }

    public static PictureResult ok(String url) {
        return new PictureResult(0,url,null);
    }

    public static PictureResult error(String errorMessage) {
        return new PictureResult(0,null,errorMessage);
    }

    private PictureResult(Integer state, String url, String errorMessage) {
        this.url = url;
        this.error = state;
        this.message = errorMessage;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
