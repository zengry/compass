package org.hk.compass.modules.sys.param;

import lombok.Data;
import org.hk.compass.validator.group.AddGroup;
import org.hk.compass.validator.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author zengry
 * @description
 * @since 2020/1/2
 */
@Data
public class RoleDataParam implements Serializable {
    private static final long serialVersionUID = 7779841988007484775L;

    @NotNull(message = "角色ID不能为空", groups = {UpdateGroup.class})
    private Long roleId;

    @NotBlank(message = "角色名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String roleName;
    private String remark;
    private List<Long> menuIdList;
    private Long createUserId;
}
