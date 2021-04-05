package com.pci.navratnaattendace.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Attendance.class, ShgRegister.class, Shg.class, Member.class}, version = 2, exportSchema = false)
public abstract class AppsDatabase extends RoomDatabase {

    public abstract Userdao userdao();
    public abstract Attendancedao attendancedao();
    public abstract ShgRegisterDao shgRegisterdao();
    public abstract Shgdao shgdao();
    public abstract Memberdao memberdao();

    private static AppsDatabase instance;

    public static synchronized AppsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppsDatabase create(Context context) {
        return Room.databaseBuilder(context,
                AppsDatabase.class,
                "Navratnadb")
                .fallbackToDestructiveMigration()
                .setJournalMode(JournalMode.TRUNCATE)
                .build();
    }
}
