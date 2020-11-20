package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

public abstract class StatusDecorator implements StatusInterface{

    StatusInterface statusI;
    public StatusDecorator(StatusInterface statusI) {
        this.statusI = statusI;
    }

    @Override
    public long getId() {
        return statusI.getId();
    }

    @Override
    public String getContentHeader() {
        return statusI.getContentHeader();
    }

    @Override
    public String getStatusDesc() {
        return statusI.getStatusDesc();
    }

    @Override
    public void setStatusDesc(String desc) {
        statusI.setStatusDesc(desc);
    }
}
