package com.pci.navratnaattendace.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface Userdao {
    @Insert
    public long insert(User user);

    @Query("select * from user Limit 1")
    public User getUserTable();
}
