package com.pci.navratnaattendace.db;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface Attendancedao {
    @Insert
    public long insert(Attendance attendance);
}
