package net.lamberplatform.region;

import java.io.Serial;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public class NotAllowedRegionException extends Exception {

    @Serial
    private static final long serialVersionUID = -2764024239261175438L;

    public NotAllowedRegionException(String message) {
        super(message);
    }
}
