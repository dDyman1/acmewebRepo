package com.acme.statusmgr.beans;

public interface StatusInterface {

    public long getId();

    public String getContentHeader();

    public String getStatusDesc();

    public void setStatusDesc(String desc);
}
