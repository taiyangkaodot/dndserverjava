package com.zy.dndserver;

import com.iohao.game.action.skeleton.core.IoGameGlobalSetting;
import com.iohao.game.action.skeleton.core.codec.JsonDataCodec;
import com.iohao.game.external.core.config.ExternalGlobalConfig;
import com.iohao.game.external.core.netty.simple.NettySimpleHelper;
import com.zy.dndserver.login.LoginLogicServer;

import java.util.List;

public class DndServer {
    public static void main(String[] args) {
        // 游戏对外服端口
        int port = 10100;
        // 游戏逻辑服
        var LoginLogicServer = new LoginLogicServer();
        //json
        IoGameGlobalSetting.setDataCodec(new JsonDataCodec());
        // 启动 对外服、网关服、逻辑服; 并生成游戏业务文档
        // 这三部分在一个进程中相互使用内存通信
        var accessAuthenticationHook = ExternalGlobalConfig.accessAuthenticationHook;
        // 表示登录才能访问业务方法
        accessAuthenticationHook.setVerifyIdentity(true);
        // 添加不需要登录（身份验证）也能访问的主路由（范围）
        accessAuthenticationHook.addIgnoreAuthCmd(DndServerModule.loginCmd);
        NettySimpleHelper.run(port, List.of(LoginLogicServer));
    }
}
