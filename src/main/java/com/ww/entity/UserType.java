package com.ww.entity;


/**
 * Created by ww on 16/12/25.
 */
public enum UserType {
    ORIDNARY("普通",1),
    VIP("vip",2),
    GOLDEN("金牌",3);
    private String title;
    private int value;

    UserType(String title, int value) {
        this.title = title;
        this.value = value;
    }

    public static UserType valueOf(int value){
        UserType[]types=UserType.values();
        for (UserType type:types) {
            if(type.value==value){
                return type;
            }
        }
        String msg=String.format("用户类型value:%d",value);
        throw new RuntimeException("异常"+msg);
    }

}
