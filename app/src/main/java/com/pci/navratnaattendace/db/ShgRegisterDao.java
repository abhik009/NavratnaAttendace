package com.pci.navratnaattendace.db;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ShgRegisterDao {
    @Insert
    public long insert(ShgRegister shgRegister);
}
