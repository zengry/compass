package org.hk.compass.modules.sys.param;

import lombok.Data;
import lombok.NonNull;
import org.hk.compass.validator.group.AddGroup;
import org.hk.compass.validator.group.UpdateGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zengry
 * @description
 * @since 2020/1/2
 */

@Data
public class UserDataForm implements Serializable {
    private static final long serialVersionUID = 4168842899263370155L;

    @NotNull(message = "用户ID不能为空", groups = {UpdateGroup.class})
    private Long id;

    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    @NotBlank(message = "密码不能为空", groups = AddGroup.class)
    private String password;
    private String salt;

    @NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message = "邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;

    private String mobile;
    private Integer status;
    private Long createUserId;
    private Date createTime;
    private List<Long> roleIdList;
}
