package org.hk.compass.modules.sys.service.impl;

import org.hk.compass.modules.sys.entity.SysConfig;
import org.hk.compass.modules.sys.mapper.SysConfigMapper;
import org.hk.compass.modules.sys.service.ISysConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置信息表 服务实现类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

}
