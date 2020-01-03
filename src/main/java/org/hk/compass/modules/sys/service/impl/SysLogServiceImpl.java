package org.hk.compass.modules.sys.service.impl;

import org.hk.compass.modules.sys.entity.SysLog;
import org.hk.compass.modules.sys.mapper.SysLogMapper;
import org.hk.compass.modules.sys.service.ISysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author zengry
 * @since 2019-12-30
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

}
