package com.zy.dndserver.login;

import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.exception.MsgException;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import com.zy.dndserver.DndServerModule;
import com.zy.dndserver.ErrorMsg;
import com.zy.dndserver.entities.User;
import com.zy.dndserver.mongodbhandler.MongoDBhandler;
import dev.morphia.query.FindOptions;
import dev.morphia.query.filters.Filters;

import java.util.List;
import java.util.UUID;

@ActionController(LoginCmdModule.cmd)
public class LoginAction {

    @ActionMethod(LoginCmdModule.registerVerify)
    public RegisterRsp register(RegisterReq req, FlowContext flowContext)  {
        var username = req.username;
        var password = req.password;
        var datastore = MongoDBhandler.getInstance();
        //查询mongodb
        boolean exists = datastore.find(User.class)
                .filter(Filters.eq("username", username))
                .iterator(new FindOptions().limit(1))
                .hasNext();
        //必须不存在，否则抛出异常
        ErrorMsg.registerFail.assertTrue(!exists);

        //用户不存在,注册,存入数据库
        var user = new User(generateuid(),username,password);
        datastore.insert(user);
        var resp = new RegisterRsp();
        resp.gameCode = GameCode.LOGINSUCCESS;
        return resp;
    }
    @ActionMethod(LoginCmdModule.loginVerify)
    public LoginRsp login(LoginReq req, FlowContext flowContext)  {
        var username = req.username;
        var password = req.password;
        var datastore = MongoDBhandler.getInstance();
        var user = datastore.find(User.class)
                .filter(Filters.and(Filters.eq("username",username),Filters.eq("password",password)))
                .first();
        ErrorMsg.loginFail.assertNonNull(user);
        var loginResult = new LoginRsp();
        var uid = user.getUid();
        boolean success = flowContext.bindingUserId(uid);
        ErrorMsg.binduidFail.assertTrue(success);
        loginResult.gameCode = GameCode.LOGINSUCCESS;
        return loginResult;
    }
    public static long generateuid() {
        // 取UUID的mostSigBits或leastSigBits
        var uuid = UUID.randomUUID();
        return uuid.getMostSignificantBits(); // 或 uuid.getLeastSignificantBits();
    }
}
