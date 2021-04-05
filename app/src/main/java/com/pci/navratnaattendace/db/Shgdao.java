package com.pci.navratnaattendace.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface Shgdao {
    @Insert
    public long insertShg(Shg shg);

    @Query("select * from shg")
    public List<Shg> getShgTable();

    @Query("select shgName from shg")
    public List<String> getShgsNames();

    @Query("select * from shg where shgId ==:Id")
    public Shg getShgTable(long Id);

    @Delete
    public void deleteShg(Shg shg);
}
