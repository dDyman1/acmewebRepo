package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;

public class SatusExtDecorator extends StatusDecorator {
    public SatusExtDecorator(StatusInterface status) {
        super(status);

        // Obtain current status of server
        super.setStatusDesc(status.getStatusDesc() + ServerManager.getServerExtensionStatus());

    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public String getContentHeader() {
        return super.getContentHeader();
    }

    @Override
    public String getStatusDesc() {
        return super.getStatusDesc();
    }
}
