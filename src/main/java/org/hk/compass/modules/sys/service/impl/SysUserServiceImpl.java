package org.hk.compass.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.hk.compass.modules.sys.entity.SysUser;
import org.hk.compass.modules.sys.entity.SysUserRole;
import org.hk.compass.modules.sys.mapper.SysUserMapper;
import org.hk.compass.modules.sys.param.UserDataForm;
import org.hk.compass.modules.sys.param.UserListParam;
import org.hk.compass.modules.sys.service.ISysUserRoleService;
import org.hk.compass.modules.sys.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hk.compass.util.BeanCopyUtils;
import org.hk.compass.util.PageResult;
import org.hk.compass.util.PasswordHelper;
import org.hk.compass.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Override
    public SysUser getUserByName(String name) {
        return this.baseMapper.getUserByName(name);
    }

    @Override
    public R resetPassword(Long userId, String before, String after) {
        SysUser sysUser = this.baseMapper.selectOne(new QueryWrapper<SysUser>()
                .eq("id", userId).eq("password", before));
        if(null == sysUser){
            return R.error("旧密码不正确");
        }
        sysUser.setPassword(after);
        return (this.saveOrUpdate(sysUser)) ? R.success() : R.error();
    }

    @Override
    public R pageQueryUserList(UserListParam param) {
        Page page = new Page<SysUser>(param.getPage(), param.getLimit());
        IPage<SysUser> result = this.baseMapper.selectPageVo(page, param.getUsername(), param.getUserId());
        return R.success().put("page", new PageResult(result));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R save(UserDataForm dataForm) {
        SysUser user = new SysUser();
        try{
            BeanCopyUtils.copyPropertiesIgnoreNull(dataForm, user);
            user.setCreateTime(new Date());
            String salt = RandomStringUtils.randomAlphanumeric(20);
            user.setSalt(salt);
            user.setPassword(PasswordHelper.encyptPassword(dataForm.getPassword(), salt));
        }catch (Exception e){
            log.error("save: bean copy error : {}", e.getCause());
            return R.error(e.getMessage());
        }

        // save user
        this.save(user);

        // save user role
        saveUserRole(user.getId(), dataForm.getRoleIdList());

        return R.success();
    }

    private void saveUserRole(Long userId, List<Long> roleIdList) {
        if(!CollectionUtils.isEmpty(roleIdList)){
            List<SysUserRole> list = new ArrayList<>();
            roleIdList.forEach(roleId->{
                SysUserRole userRole = new SysUserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(userId);
                list.add(userRole);
            });
            sysUserRoleService.saveBatch(list);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R update(UserDataForm dataForm) {
        SysUser user = this.baseMapper.selectById(dataForm.getId());
        if(null == user){
            return R.error("查无此人");
        }

        try{
            // 需要更新密码
            if(!StringUtils.isBlank(dataForm.getPassword())){
                dataForm.setPassword(PasswordHelper.encyptPassword(dataForm.getPassword(), user.getSalt()));
            }
            BeanCopyUtils.copyPropertiesIgnoreNull(dataForm, user);
        }catch (Exception e){
            log.error("update: bean copy error : {}", e.getCause());
            return R.error(e.getMessage());
        }

        // update user
        this.updateById(user);

        // update user-role
        if(!CollectionUtils.isEmpty(dataForm.getRoleIdList())){
            sysUserRoleService.remove(new QueryWrapper<SysUserRole>().eq("user_id", user.getId()));
            saveUserRole(user.getId(), dataForm.getRoleIdList());
        }

        return  R.success();
    }

    @Override
    public R delete(List<Long> userIds) {
        return this.removeByIds(userIds) ? R.success() : R.error();
    }

}
