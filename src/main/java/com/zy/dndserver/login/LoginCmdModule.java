package com.zy.dndserver.login;

import com.zy.dndserver.DndServerModule;

public interface LoginCmdModule {
    int cmd = DndServerModule.loginCmd;
    int loginVerify = 1;
    int registerVerify = 2;
}
