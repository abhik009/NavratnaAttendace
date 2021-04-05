package com.pci.navratnaattendace.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
//indices = {@Index(value = "shgName", unique = true)}
@Entity(tableName = "shg")
public class Shg implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "shgId")
    int shgId;

    @ColumnInfo
    String district;

    @ColumnInfo
    String block;

    @ColumnInfo
    String vo;

    @ColumnInfo
    String village;

    @ColumnInfo
    String shgName;

    @ColumnInfo
    int shgTMember;

    @Ignore
    public Shg() { }

    public Shg(int shgId, String district, String block, String vo, String village, String shgName, int shgTMember) {
        this.shgId = shgId;
        this.district = district;
        this.block = block;
        this.vo = vo;
        this.village = village;
        this.shgName = shgName;
        this.shgTMember = shgTMember;
    }

    public int getShgId() {
        return shgId;
    }

    public String getDistrict() {
        return district;
    }

    public String getBlock() {
        return block;
    }

    public String getVo() {
        return vo;
    }

    public String getVillage() {
        return village;
    }

    public String getShgName() {
        return shgName;
    }

    public int getShgTMember() {
        return shgTMember;
    }
}
