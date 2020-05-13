package xyz.hydrion.care.domain.form;

import javax.validation.constraints.NotEmpty;

public class UserForm {
    @NotEmpty(message = "用户id不能为空")
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
