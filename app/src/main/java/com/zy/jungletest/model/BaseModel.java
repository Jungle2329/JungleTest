package com.zy.jungletest.model;

/**
 * Created by Jungle on 2018/3/22.
 */

public class BaseModel {

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
    private String newsCount;
    private String rewardGold;
    private String isVerifyphone;
    private IsUpdateTipBean isUpdateTip;
    private String myCount;
    private FuncConfBean funcConf;
    private String isShop;
    private String relnameAuthState;
    private String haveSellerInfo;
    private String isGag;
    private ActVipBean actVip;
    private String secLevel;
    private String isLive;

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

    public String getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(String newsCount) {
        this.newsCount = newsCount;
    }

    public String getRewardGold() {
        return rewardGold;
    }

    public void setRewardGold(String rewardGold) {
        this.rewardGold = rewardGold;
    }

    public String getIsVerifyphone() {
        return isVerifyphone;
    }

    public void setIsVerifyphone(String isVerifyphone) {
        this.isVerifyphone = isVerifyphone;
    }

    public IsUpdateTipBean getIsUpdateTip() {
        return isUpdateTip;
    }

    public void setIsUpdateTip(IsUpdateTipBean isUpdateTip) {
        this.isUpdateTip = isUpdateTip;
    }

    public String getMyCount() {
        return myCount;
    }

    public void setMyCount(String myCount) {
        this.myCount = myCount;
    }

    public FuncConfBean getFuncConf() {
        return funcConf;
    }

    public void setFuncConf(FuncConfBean funcConf) {
        this.funcConf = funcConf;
    }

    public String getIsShop() {
        return isShop;
    }

    public void setIsShop(String isShop) {
        this.isShop = isShop;
    }

    public String getRelnameAuthState() {
        return relnameAuthState;
    }

    public void setRelnameAuthState(String relnameAuthState) {
        this.relnameAuthState = relnameAuthState;
    }

    public String getHaveSellerInfo() {
        return haveSellerInfo;
    }

    public void setHaveSellerInfo(String haveSellerInfo) {
        this.haveSellerInfo = haveSellerInfo;
    }

    public String getIsGag() {
        return isGag;
    }

    public void setIsGag(String isGag) {
        this.isGag = isGag;
    }

    public ActVipBean getActVip() {
        return actVip;
    }

    public void setActVip(ActVipBean actVip) {
        this.actVip = actVip;
    }

    public String getSecLevel() {
        return secLevel;
    }

    public void setSecLevel(String secLevel) {
        this.secLevel = secLevel;
    }

    public String getIsLive() {
        return isLive;
    }

    public void setIsLive(String isLive) {
        this.isLive = isLive;
    }

    public static class IsUpdateTipBean {
        /**
         * level :
         * grouptitle :
         */

        private String level;
        private String grouptitle;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getGrouptitle() {
            return grouptitle;
        }

        public void setGrouptitle(String grouptitle) {
            this.grouptitle = grouptitle;
        }
    }

    public static class FuncConfBean {
        /**
         * packet : {"display":""}
         */

        private PacketBean packet;

        public PacketBean getPacket() {
            return packet;
        }

        public void setPacket(PacketBean packet) {
            this.packet = packet;
        }

        public static class PacketBean {
            /**
             * display :
             */

            private String display;

            public String getDisplay() {
                return display;
            }

            public void setDisplay(String display) {
                this.display = display;
            }
        }
    }

    public static class ActVipBean {
        /**
         * gaid :
         * url :
         * iconBig :
         * iconSmall :
         * title :
         * actType :
         * vipActShow : 0
         */

        private String gaid;
        private String url;
        private String iconBig;
        private String iconSmall;
        private String title;
        private String actType;
        private String vipActShow;

        public String getGaid() {
            return gaid;
        }

        public void setGaid(String gaid) {
            this.gaid = gaid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIconBig() {
            return iconBig;
        }

        public void setIconBig(String iconBig) {
            this.iconBig = iconBig;
        }

        public String getIconSmall() {
            return iconSmall;
        }

        public void setIconSmall(String iconSmall) {
            this.iconSmall = iconSmall;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getActType() {
            return actType;
        }

        public void setActType(String actType) {
            this.actType = actType;
        }

        public String getVipActShow() {
            return vipActShow;
        }

        public void setVipActShow(String vipActShow) {
            this.vipActShow = vipActShow;
        }
    }
}
