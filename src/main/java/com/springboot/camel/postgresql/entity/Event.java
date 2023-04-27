package com.springboot.camel.postgresql.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "event")
public class Event {


    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    private int eventId;

    private String transId;

    public Event(int eventId, String transId, String transTms, String rcNum, String clientId, int eventCnt, String locationCd, String locationId1, String locationId2, String addrNbr) {
        this.eventId = eventId;

        this.transId = transId;
        this.transTms = transTms;
        this.rcNum = rcNum;
        this.clientId = clientId;
        this.eventCnt = eventCnt;
        this.locationCd = locationCd;
        this.locationId1 = locationId1;
        this.locationId2 = locationId2;
        this.addrNbr = addrNbr;
    }

    private String transTms;
    private String rcNum;
    private String clientId;
    private int eventCnt;
    private String locationCd;
    private String locationId1;
    private String locationId2;
    private String addrNbr;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getEventCnt() {
        return eventCnt;
    }

    public void setEventCnt(int eventCnt) {
        this.eventCnt = eventCnt;
    }

    public String getLocationCd() {
        return locationCd;
    }

    public void setLocationCd(String locationCd) {
        this.locationCd = locationCd;
    }

    public String getLocationId1() {
        return locationId1;
    }

    public void setLocationId1(String locationId1) {
        this.locationId1 = locationId1;
    }

    public String getLocationId2() {
        return locationId2;
    }

    public void setLocationId2(String locationId2) {
        this.locationId2 = locationId2;
    }

    public String getAddrNbr() {
        return addrNbr;
    }

    public void setAddrNbr(String addrNbr) {
        this.addrNbr = addrNbr;
    }



    public String getTransTms() {
        return transTms;
    }

    public void setTransTms(String transTms) {
        this.transTms = transTms;
    }

    public String getRcNum() {
        return rcNum;
    }

    public void setRcNum(String rcNum) {
        this.rcNum = rcNum;
    }


    public Event() {
    }




    @Override
    public String toString() {
        return "Book{" +
                "id=" + eventId +
                ", transId='" + transId + '\'' +
                ", transTms='" + transTms + '\'' +
                ", rcNum=" + rcNum + '\'' +
                ", clientId=" + clientId + '\'' +
                ", eventCnt=" + eventCnt + '\'' +
                ", locationCd=" + clientId + '\'' +
                ", locationId1=" + locationId1 + '\'' +
                ", locationId2=" + locationId2 + '\'' +
                ", addrNbr=" + addrNbr + '\'' +
                '}';
    }

}
