package org.hk.compass.modules.sys.mapper;

import org.apache.ibatis.annotations.Param;
import org.hk.compass.modules.sys.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    List<String> selectUserMenus(@Param("userId") Long userId);
}
