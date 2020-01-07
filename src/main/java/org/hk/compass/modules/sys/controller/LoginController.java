package org.hk.compass.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.hk.compass.constants.Constants;
import org.hk.compass.modules.sys.entity.SysUser;
import org.hk.compass.modules.sys.param.LoginForm;
import org.hk.compass.modules.sys.service.ISysCaptchaService;
import org.hk.compass.modules.sys.service.ISysUserService;
import org.hk.compass.modules.sys.service.ISysUserTokenService;
import org.hk.compass.util.PasswordHelper;
import org.hk.compass.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author zengry
 * @description
 * @since 2019/12/27
 */
@Api(description = "登录管理接口")
@RestController
public class LoginController extends AbstractController{

    @Autowired
    private ISysCaptchaService sysCaptchaService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysUserTokenService sysUserTokenService;

    /**
     * 验证码
     */
    @ApiOperation(value = "获取登录验证码图片")
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);

        // TODO  https://www.jianshu.com/p/4edd1775b983
        IOUtils.closeQuietly(out);
    }



    @PostMapping("/sys/login")
    public R login(@RequestBody  LoginForm loginForm){
        // 校验验证码
        boolean captcha = sysCaptchaService.validate(loginForm.getUuid(), loginForm.getCaptcha());
        if (!captcha) {
            return R.error("验证码不正确");
        }

        // 校验账号密码
        SysUser sysUser = sysUserService.getUserByName(loginForm.getUsername());
        if(null == sysUser){
            return R.error("账号不存在");
        }
        if(!sysUser.getPassword().equals(PasswordHelper
            .encyptPassword(loginForm.getPassword(), sysUser.getSalt()))){
            return R.error("密码不正确");
        }
        if(Constants.Disable.equals(sysUser.getStatus())){
            return R.error("账号已被锁定,请联系管理员");
        }

        return sysUserTokenService.generatorToken(sysUser.getId());
    }


    @PostMapping("/sys/logout")
    public R logout(){
        return sysUserTokenService.logout(getUserId());
    }

}
