package com.mace.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Dept implements Serializable {

    /**
     * 部门ID
     */
    private Integer eptno;

    /**
     * 部门名称
     */
    private String dname;

    /**
     * 部门位置
     */
    private String loc;

    /**
     * DEPT
     */
    private static final long serialVersionUID = 1L;

    public Dept(Integer eptno, String dname, String loc) {
        this.eptno = eptno;
        this.dname = dname;
        this.loc = loc;
    }

    public Dept() {
    }

    public Integer getEptno() {
        return eptno;
    }

    public void setEptno(Integer eptno) {
        this.eptno = eptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dept{");
        sb.append("eptno=").append(eptno);
        sb.append(", dname='").append(dname).append('\'');
        sb.append(", loc='").append(loc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}