package org.hk.compass.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.hk.compass.modules.sys.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> selectListByUserId(@Param("userId") Long userId);
    IPage<SysRole> selectPageVo(Page page,
        @Param("rolename") String rolename,
        @Param("userId") Long userId);
}
