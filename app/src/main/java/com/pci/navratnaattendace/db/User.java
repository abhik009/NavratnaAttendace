package com.pci.navratnaattendace.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @ColumnInfo
    String district;

    @ColumnInfo
    String block;

    @ColumnInfo
    String panchyat;

    @ColumnInfo
    String village;

    @ColumnInfo
    String voName;

    @ColumnInfo
    String cmName;

    @PrimaryKey
    @ColumnInfo
    @NonNull
    String cmMobile;

    @Ignore
    public User() { }

    public User(String district, String block, String panchyat, String village, String voName, String cmName, String cmMobile) {
        this.district = district;
        this.block = block;
        this.panchyat = panchyat;
        this.village = village;
        this.voName = voName;
        this.cmName = cmName;
        this.cmMobile = cmMobile;
    }

    public String getDistrict() {
        return district;
    }

    public String getBlock() {
        return block;
    }

    public String getPanchyat() {
        return panchyat;
    }

    public String getVillage() {
        return village;
    }

    public String getVoName() {
        return voName;
    }

    public String getCmName() {
        return cmName;
    }

    public String getCmMobile() {
        return cmMobile;
    }
}
