package com.pci.navratnaattendace.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "attendance")
public class Attendance {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "attendanceId")
    int attendanceId;

    @ColumnInfo(name = "cm_mobile_number")
    String mNumber;

    @ColumnInfo(name = "shg_name")
    String shgName;

    @ColumnInfo(name = "attendace_date")
    String attendaceDate;

    @ColumnInfo(name = "member_name")
    String memberName;

    @Ignore
    public Attendance() { }

    public Attendance(int attendanceId, String mNumber, String shgName, String attendaceDate, String memberName) {
        this.attendanceId = attendanceId;
        this.mNumber = mNumber;
        this.shgName = shgName;
        this.attendaceDate = attendaceDate;
        this.memberName = memberName;
    }
}
