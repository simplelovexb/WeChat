package com.cniao5.wechat.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table REQUEST_MSG.
 */
public class RequestMsg {

    private Long id;
    private long memberid;
    private long contactid;
    private long requestid;
    private String requestmsg;
    /** Not-null value. */
    private java.util.Date requesttime;
    private String contactemail;
    private String contactname;
    private String contactheadbig;
    private String contactheadmid;
    private String contactheadsmall;
    private java.util.Date contactregistetime;
    private int status;

    public RequestMsg() {
    }

    public RequestMsg(Long id) {
        this.id = id;
    }

    public RequestMsg(Long id, long memberid, long contactid, long requestid, String requestmsg, java.util.Date requesttime, String contactemail, String contactname, String contactheadbig, String contactheadmid, String contactheadsmall, java.util.Date contactregistetime, int status) {
        this.id = id;
        this.memberid = memberid;
        this.contactid = contactid;
        this.requestid = requestid;
        this.requestmsg = requestmsg;
        this.requesttime = requesttime;
        this.contactemail = contactemail;
        this.contactname = contactname;
        this.contactheadbig = contactheadbig;
        this.contactheadmid = contactheadmid;
        this.contactheadsmall = contactheadsmall;
        this.contactregistetime = contactregistetime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getMemberid() {
        return memberid;
    }

    public void setMemberid(long memberid) {
        this.memberid = memberid;
    }

    public long getContactid() {
        return contactid;
    }

    public void setContactid(long contactid) {
        this.contactid = contactid;
    }

    public long getRequestid() {
        return requestid;
    }

    public void setRequestid(long requestid) {
        this.requestid = requestid;
    }

    public String getRequestmsg() {
        return requestmsg;
    }

    public void setRequestmsg(String requestmsg) {
        this.requestmsg = requestmsg;
    }

    /** Not-null value. */
    public java.util.Date getRequesttime() {
        return requesttime;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setRequesttime(java.util.Date requesttime) {
        this.requesttime = requesttime;
    }

    public String getContactemail() {
        return contactemail;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getContactheadbig() {
        return contactheadbig;
    }

    public void setContactheadbig(String contactheadbig) {
        this.contactheadbig = contactheadbig;
    }

    public String getContactheadmid() {
        return contactheadmid;
    }

    public void setContactheadmid(String contactheadmid) {
        this.contactheadmid = contactheadmid;
    }

    public String getContactheadsmall() {
        return contactheadsmall;
    }

    public void setContactheadsmall(String contactheadsmall) {
        this.contactheadsmall = contactheadsmall;
    }

    public java.util.Date getContactregistetime() {
        return contactregistetime;
    }

    public void setContactregistetime(java.util.Date contactregistetime) {
        this.contactregistetime = contactregistetime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
