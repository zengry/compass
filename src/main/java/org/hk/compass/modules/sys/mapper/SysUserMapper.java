package org.hk.compass.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.hk.compass.modules.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser getUserByName(String name);
    IPage<SysUser> selectPageVo(Page page,
        @Param("username") String username,
        @Param("userId") Long userId);
}
