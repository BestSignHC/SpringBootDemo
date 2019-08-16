package com.hecheng.domain;

public class IntAlias {
    private Long aliasId = 0L;
    private String customId = "";
    private String bestSignID = "";
    private Long developerId = 0L;
    private Long ctime = 0L;
    private Long etime = 0L;

    public IntAlias() {
    }

    public Long getAliasId() {
        return aliasId;
    }

    public void setAliasId(Long aliasId) {
        this.aliasId = aliasId;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getBestSignID() {
        return bestSignID;
    }

    public void setBestSignID(String bestSignID) {
        this.bestSignID = bestSignID;
    }

    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getEtime() {
        return etime;
    }

    public void setEtime(Long etime) {
        this.etime = etime;
    }
}
