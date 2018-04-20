package com.zy.jungletest.model;

/**
 * Created by Jungle on 2018/3/22.
 */

public class BaseModel<T> {

    /**
     * status : 1
     * msg : 成功
     * newsCount : 34
     * rewardGold : 0
     * isVerifyphone : 1
     * isUpdateTip : {"level":"","grouptitle":""}
     * myCount : 3
     * funcConf : {"packet":{"display":""}}
     * isShop : 1
     * relnameAuthState : 1
     * haveSellerInfo : 1
     * isGag : 1
     * actVip : {"gaid":"","url":"","iconBig":"","iconSmall":"","title":"","actType":"","vipActShow":"0"}
     * secLevel : 1
     * isLive : 1
     */

    private int status;
    private String msg;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
