package com.pci.navratnaattendace.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface Memberdao {
    @Insert
    public long insertMember(Member member);

    @Query("select * from member")
    public List<Member> getMemberTable();

    @Query("select * from member where memShg =:shgName")
    public List<Member> getShgMembers(String shgName);

    @Query("select memName from member where memShg =:shgName")
    public List<String> getMemberNames(String shgName);

    @Delete
    public void deleteMembers(Member member);

    @Query("Delete from member where memShg =:shgName")
    public void deleteShgMember(String shgName);

}
