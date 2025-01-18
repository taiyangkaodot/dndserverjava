package com.zy.dndserver.login;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;

@ProtobufClass
public class RegisterReq {
    String username;
    String password;
}
