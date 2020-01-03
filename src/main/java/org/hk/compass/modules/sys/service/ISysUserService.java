package org.hk.compass.modules.sys.service;

import org.hk.compass.modules.sys.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.hk.compass.modules.sys.param.UserDataForm;
import org.hk.compass.modules.sys.param.UserListParam;
import org.hk.compass.util.R;

import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 获取用户
     * @param name
     * @return
     */
    SysUser getUserByName(String name);

    /**
     * 修改密码
     * @param userId
     * @param before 旧密码
     * @param after 新密码
     * @return
     */
    R resetPassword(Long userId, String before, String after);

    /**
     * 获取用户列表
     * @return
     */
    R pageQueryUserList(UserListParam param);


    /**
     * 保存用户信息
     * @param dataForm
     * @return
     */
    R save(UserDataForm dataForm);

    /**
     * 更新用户信息
     * @param dataForm
     * @return
     */
    R update(UserDataForm dataForm);

    /**
     * 删除用户信息
     * @param userIds
     * @return
     */
    R delete(List<Long> userIds);

}
