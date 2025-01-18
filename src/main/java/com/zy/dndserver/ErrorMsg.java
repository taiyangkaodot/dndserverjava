package com.zy.dndserver;

import com.iohao.game.action.skeleton.core.exception.MsgExceptionInfo;

import java.io.Serializable;

public enum ErrorMsg  implements MsgExceptionInfo{
    loginFail(100,"账户或密码错误"),
    registerFail(101,"注册失败"),
    binduidFail(102,"绑定uid失败");
    final int code;
    final String msg;
    ErrorMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public int getCode() {
        return code;
    }
}
