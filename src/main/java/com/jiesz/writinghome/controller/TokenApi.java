package com.jiesz.writinghome.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiesz.writinghome.common.Namespace;
import com.jiesz.writinghome.common.Regexp;
import com.jiesz.writinghome.entity.User;
import com.jiesz.writinghome.exception.BizException;
import com.jiesz.writinghome.service.IUserService;
import com.jiesz.writinghome.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jcl on 2019/1/2
 */
@Api(tags = "令牌")
@RestController
@RequestMapping(Namespace.API)
@Validated
public class TokenApi {

    @Resource
    private IUserService userService;

    @ApiOperation("获取令牌")
    @PostMapping(Namespace.TOKEN)
    public String getToken(
            @Pattern(regexp = Regexp.USERNAME, message = "账户或密码错误") @RequestParam @ApiParam(value = "用户名，必须由字母+数字组合，不能有中文和特殊符号，长度在5-20位之间", required = true) String username,
            @Pattern(regexp = Regexp.PWD, message = "账户或密码错误") @RequestParam @ApiParam(value = "密码", required = true) String pwd
    ) {
        //1.校验账号密码
        User user = userService.getOne(new QueryWrapper<>(User.builder().username(username).password(pwd).build()));
        if (null == user) {
            throw new BizException("账户或密码错误");
        }
        //2. 使用用户信息生成token
        String token = generateToken(user);
        return token;
    }

    /**
     * 生成token
     *
     * @return
     */
    private String generateToken(User user) {
        Map<String, String> data = new HashMap<>(8);
        data.put("userId", user.getUserId().toString());
        data.put("nickname", user.getNickname());
        data.put("email", user.getEmail());
        return TokenUtil.createToken(data);
    }


}
