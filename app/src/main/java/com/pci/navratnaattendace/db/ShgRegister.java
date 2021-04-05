package com.pci.navratnaattendace.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "shgRegister")
public class ShgRegister {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "shgRegId")
    int shgRegId;
    @ColumnInfo(name = "shgName")
    String shgName;
    @ColumnInfo(name = "Village")
    String shgVillage;
    @ColumnInfo(name = "reportingMonth")
    int month;
    @ColumnInfo(name = "reportingYear")
    int year;
    @ColumnInfo(name = "Q6")
    int shgQ6;
    @ColumnInfo(name = "Q7S1")
    int q7S1;
    @ColumnInfo(name = "Q7S2")
    int q7S2;
    @ColumnInfo(name = "Q7S3")
    int q7S3;
    @ColumnInfo(name = "Q7S4")
    int q7S4;
    @ColumnInfo(name = "Q7S5")
    int q7S5;
    @ColumnInfo(name = "Q7S6")
    int q7S6;
    @ColumnInfo(name = "Q7S7")
    int q7S7;
    @ColumnInfo(name = "Q7S8")
    int q7S8;
    @ColumnInfo(name = "Q7S9")
    int q7S9;
    @ColumnInfo(name = "Q7S10")
    int q7S10;
    @ColumnInfo(name = "Q7S11")
    int q7S11;
    @ColumnInfo(name = "Q7S12")
    int q7S12;
    @ColumnInfo(name = "Q7S13")
    int q7S13;
    @ColumnInfo(name = "Q7S14")
    int q7S14;
    @ColumnInfo(name = "Q7S15")
    int q7S15;
    @ColumnInfo(name = "Q8A")
    int shgQ8A;
    @ColumnInfo(name = "Q8B")
    int shgQ8B;
    @ColumnInfo(name = "Q9A")
    int shgQ9A;
    @ColumnInfo(name = "Q9B")
    int shgQ9B;
    @ColumnInfo(name = "Q9C")
    int shgQ9C;
    @ColumnInfo(name = "Q10")
    int shgQ10;
    @ColumnInfo(name = "Q11")
    int shgQ11;
    @ColumnInfo(name = "Q12")
    int shgQ12;
    @ColumnInfo(name = "Q13")
    int shgQ13;
    @ColumnInfo(name = "Q14")
    int shgQ14;
    @ColumnInfo(name = "Q15")
    int shgQ15;
    @ColumnInfo(name = "Q16")
    int shgQ16;
    @ColumnInfo(name = "Q17")
    int shgQ17;

    @Ignore
    public ShgRegister() {
    }

    public ShgRegister(int shgRegId, String shgName, String shgVillage, int month, int year, int shgQ6, int q7S1, int q7S2, int q7S3, int q7S4, int q7S5, int q7S6, int q7S7, int q7S8, int q7S9, int q7S10, int q7S11, int q7S12, int q7S13, int q7S14, int q7S15, int shgQ8A, int shgQ8B, int shgQ9A, int shgQ9B, int shgQ9C, int shgQ10, int shgQ11, int shgQ12, int shgQ13, int shgQ14, int shgQ15, int shgQ16, int shgQ17) {
        this.shgRegId = shgRegId;
        this.shgName = shgName;
        this.shgVillage = shgVillage;
        this.month = month;
        this.year = year;
        this.shgQ6 = shgQ6;
        this.q7S1 = q7S1;
        this.q7S2 = q7S2;
        this.q7S3 = q7S3;
        this.q7S4 = q7S4;
        this.q7S5 = q7S5;
        this.q7S6 = q7S6;
        this.q7S7 = q7S7;
        this.q7S8 = q7S8;
        this.q7S9 = q7S9;
        this.q7S10 = q7S10;
        this.q7S11 = q7S11;
        this.q7S12 = q7S12;
        this.q7S13 = q7S13;
        this.q7S14 = q7S14;
        this.q7S15 = q7S15;
        this.shgQ8A = shgQ8A;
        this.shgQ8B = shgQ8B;
        this.shgQ9A = shgQ9A;
        this.shgQ9B = shgQ9B;
        this.shgQ9C = shgQ9C;
        this.shgQ10 = shgQ10;
        this.shgQ11 = shgQ11;
        this.shgQ12 = shgQ12;
        this.shgQ13 = shgQ13;
        this.shgQ14 = shgQ14;
        this.shgQ15 = shgQ15;
        this.shgQ16 = shgQ16;
        this.shgQ17 = shgQ17;
    }

    public int getShgRegId() {
        return shgRegId;
    }

    public String getShgName() {
        return shgName;
    }

    public String getShgVillage() {
        return shgVillage;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getShgQ6() {
        return shgQ6;
    }

    public int getQ7S1() {
        return q7S1;
    }

    public int getQ7S2() {
        return q7S2;
    }

    public int getQ7S3() {
        return q7S3;
    }

    public int getQ7S4() {
        return q7S4;
    }

    public int getQ7S5() {
        return q7S5;
    }

    public int getQ7S6() {
        return q7S6;
    }

    public int getQ7S7() {
        return q7S7;
    }

    public int getQ7S8() {
        return q7S8;
    }

    public int getQ7S9() {
        return q7S9;
    }

    public int getQ7S10() {
        return q7S10;
    }

    public int getQ7S11() {
        return q7S11;
    }

    public int getQ7S12() {
        return q7S12;
    }

    public int getQ7S13() {
        return q7S13;
    }

    public int getQ7S14() {
        return q7S14;
    }

    public int getQ7S15() {
        return q7S15;
    }

    public int getShgQ8A() {
        return shgQ8A;
    }

    public int getShgQ8B() {
        return shgQ8B;
    }

    public int getShgQ9A() {
        return shgQ9A;
    }

    public int getShgQ9B() {
        return shgQ9B;
    }

    public int getShgQ9C() {
        return shgQ9C;
    }

    public int getShgQ10() {
        return shgQ10;
    }

    public int getShgQ11() {
        return shgQ11;
    }

    public int getShgQ12() {
        return shgQ12;
    }

    public int getShgQ13() {
        return shgQ13;
    }

    public int getShgQ14() {
        return shgQ14;
    }

    public int getShgQ15() {
        return shgQ15;
    }

    public int getShgQ16() {
        return shgQ16;
    }

    public int getShgQ17() {
        return shgQ17;
    }
}
