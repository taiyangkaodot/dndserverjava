package com.zy.dndserver.login;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;

@ProtobufClass
public class LoginReq {
    String username;
    String password;
}

