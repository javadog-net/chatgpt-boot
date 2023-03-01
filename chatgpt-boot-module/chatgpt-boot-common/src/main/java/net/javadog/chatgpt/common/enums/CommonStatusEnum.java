package net.javadog.chatgpt.common.enums;

import lombok.Getter;

/**
 * @author: hdx
 * @Date: 2022-08-08 16:03
 * @version: 1.0
 **/
@Getter
public enum CommonStatusEnum {
    // 是否
    NO( 0, "否"),
    YES(1, "是"),
    ;

    private final Integer code;
    private final String message;

    CommonStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
