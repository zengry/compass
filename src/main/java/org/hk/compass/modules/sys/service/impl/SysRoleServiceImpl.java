package org.hk.compass.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.hk.compass.modules.sys.entity.SysRole;
import org.hk.compass.modules.sys.entity.SysRoleMenu;
import org.hk.compass.modules.sys.mapper.SysRoleMapper;
import org.hk.compass.modules.sys.param.RoleDataParam;
import org.hk.compass.modules.sys.param.RoleListParam;
import org.hk.compass.modules.sys.service.ISysRoleMenuService;
import org.hk.compass.modules.sys.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hk.compass.util.BeanCopyUtils;
import org.hk.compass.util.PageResult;
import org.hk.compass.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysRole> getRolesByUserId(Long userId) {
        return this.baseMapper.selectListByUserId(userId);
    }

    @Override
    public R getRoles(RoleListParam param) {
        Page page = new Page<SysRole>(param.getPage(), param.getLimit());
        IPage<SysRole> result = this.baseMapper.selectPageVo(page, param.getRoleName(), param.getUserId());
        return R.success().put("page", new PageResult(result));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R save(RoleDataParam param) {
        SysRole role = new SysRole();
        try{
            BeanCopyUtils.copyPropertiesIgnoreNull(param, role);
            role.setCreateTime(new Date());
        }catch (Exception e){
            log.error("role save error:{}", e.getMessage());
            return R.error(e.getMessage());
        }
        this.save(role);

        // save role menu
        saveRoleMenu(role.getId(), param.getMenuIdList());

        return R.success();
    }

    private void saveRoleMenu(Long roleId, List<Long> menuIdList) {
        if(!CollectionUtils.isEmpty(menuIdList)){
            List<SysRoleMenu> roleMenus = new ArrayList<>();
            menuIdList.forEach(menuId ->{
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenus.add(roleMenu);
            });
            sysRoleMenuService.saveBatch(roleMenus);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R update(RoleDataParam param) {
        SysRole role = this.getById(param.getRoleId());
        if(null == role){
            return R.error("查无记录");
        }

        try{
            BeanCopyUtils.copyPropertiesIgnoreNull(param, role);
        } catch (Exception e){
            log.error("role update error:{}", e.getCause());
            return R.error(e.getMessage());
        }
        this.updateById(role);

        if(!CollectionUtils.isEmpty(param.getMenuIdList())){
            sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq("role_id", role.getId()));
            saveRoleMenu(role.getId(), param.getMenuIdList());
        }

        return R.success();
    }

}
