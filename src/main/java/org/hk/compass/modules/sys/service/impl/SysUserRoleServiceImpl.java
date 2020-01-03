package org.hk.compass.modules.sys.service.impl;

import org.hk.compass.modules.sys.entity.SysUserRole;
import org.hk.compass.modules.sys.mapper.SysUserRoleMapper;
import org.hk.compass.modules.sys.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {



    @Override
    public Set<String> getUserPerms(Long userId) {
        List<String> perms = this.baseMapper.selectUserMenus(userId);
        Set<String> result = new HashSet<>();
        perms.forEach(p->{
            // 使用逗号分隔，每一个 perms
            result.addAll(Arrays.asList(p.trim().split(",")));
        });

        return result;
    }
}
