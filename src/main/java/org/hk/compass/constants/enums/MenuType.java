package org.hk.compass.constants.enums;

/**
 * @author zengry
 * @description 菜单类型枚举
 * @since 2019/12/30
 */
public enum MenuType {

    /**
     * 目录
     */
    CATALOG(0),
    /**
     * 菜单
     */
    MENU(1),
    /**
     * 按钮
     */
    BUTTON(2);

    private int value;

    MenuType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
