package com.iceihehe.cm.web.controller;

import com.iceihehe.cm.utils.common.RespCode;
import com.iceihehe.cm.web.dto.GwResp;
import com.iceihehe.cm.web.pojo.LoginReqData;
import com.iceihehe.cm.web.pojo.LoginRespData;
import com.iceihehe.cm.web.pojo.TaskListReqData;
import com.iceihehe.cm.web.pojo.TaskListRespData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthenticatingController {

    @RequestMapping(path = "/api/login", method = RequestMethod.POST)
    public GwResp<LoginRespData> loginApi(@Valid LoginReqData loginReqData) {
        GwResp<LoginRespData> resp = new GwResp<>();
        List<LoginRespData> loginRespDataList = new ArrayList<>();
        resp.setData(loginRespDataList);
        resp.setTotal(0);

        String username = loginReqData.getUsername();
        String password = loginReqData.getPassword();

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
        } catch (Exception e) {
            resp.setCode(RespCode.LOGIN_ERROR.getCode());
            return resp;
        }
        return resp;
    }
}