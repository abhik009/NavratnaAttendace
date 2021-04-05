package com.pci.navratnaattendace.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "member")
public class Member {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "memId")
    int memberId;
    @ColumnInfo(name = "memDistrict")
    String district;
    @ColumnInfo(name = "memBlock")
    String block;
    @ColumnInfo(name = "memVo")
    String vo;
    @ColumnInfo(name = "memShg")
    String memShg;
    @ColumnInfo(name = "memVillage")
    String memVillage;
    @ColumnInfo(name = "memName")
    String memName;
    @ColumnInfo(name = "memAge")
    int memAge;
    @ColumnInfo(name = "Q1")
    int Q1;
    @ColumnInfo(name = "Q2")
    int Q2;
    @ColumnInfo(name = "Q3")
    int Q3;
    @ColumnInfo(name = "Q4")
    int Q4;
    @ColumnInfo(name = "Q5")
    int Q5;
    @ColumnInfo(name = "Q6")
    int Q6;
    @ColumnInfo(name = "Q7")
    int Q7;
    @ColumnInfo(name = "Q8")
    int Q8;
    @ColumnInfo(name = "Q9")
    int Q9;
    @ColumnInfo(name = "Q10")
    int Q10;
    @ColumnInfo(name = "Q11")
    int Q11;
    @ColumnInfo(name = "Q12")
    int Q12;
    @ColumnInfo(name = "Q13")
    int Q13;
    @ColumnInfo(name = "Q14")
    int Q14;
    @ColumnInfo(name = "Q15")
    int Q15;
    @ColumnInfo(name = "Q16")
    int Q16;
    @ColumnInfo(name = "Q17")
    int Q17;
    @ColumnInfo(name = "Q18")
    int Q18;
    @ColumnInfo(name = "MobileNo")
    String memMobile;

    @Ignore
    public Member() { }

    public Member(int memberId, String district, String block, String vo, String memShg, String memVillage, String memName, int memAge, int Q1, int Q2, int Q3, int Q4, int Q5, int Q6, int Q7, int Q8, int Q9, int Q10, int Q11, int Q12, int Q13, int Q14, int Q15, int Q16, int Q17, int Q18, String memMobile) {
        this.memberId = memberId;
        this.district = district;
        this.block = block;
        this.vo = vo;
        this.memShg = memShg;
        this.memVillage = memVillage;
        this.memName = memName;
        this.memAge = memAge;
        this.Q1 = Q1;
        this.Q2 = Q2;
        this.Q3 = Q3;
        this.Q4 = Q4;
        this.Q5 = Q5;
        this.Q6 = Q6;
        this.Q7 = Q7;
        this.Q8 = Q8;
        this.Q9 = Q9;
        this.Q10 = Q10;
        this.Q11 = Q11;
        this.Q12 = Q12;
        this.Q13 = Q13;
        this.Q14 = Q14;
        this.Q15 = Q15;
        this.Q16 = Q16;
        this.Q17 = Q17;
        this.Q18 = Q18;
        this.memMobile = memMobile;
    }

    public int getMemberId() {
        return memberId;
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
    public String getMemShg() {
        return memShg;
    }
    public String getMemVillage() {
        return memVillage;
    }
    public String getMemName() {
        return memName;
    }
    public int getMemAge() {
        return memAge;
    }
    public int getQ1() {
        return Q1;
    }
    public int getQ2() {
        return Q2;
    }
    public int getQ3() {
        return Q3;
    }
    public int getQ4() {
        return Q4;
    }
    public int getQ5() {
        return Q5;
    }
    public int getQ6() {
        return Q6;
    }
    public int getQ7() {
        return Q7;
    }
    public int getQ8() {
        return Q8;
    }
    public int getQ9() {
        return Q9;
    }
    public int getQ10() {
        return Q10;
    }
    public int getQ11() {
        return Q11;
    }
    public int getQ12() {
        return Q12;
    }
    public int getQ13() {
        return Q13;
    }
    public int getQ14() {
        return Q14;
    }
    public int getQ15() {
        return Q15;
    }
    public int getQ16() {
        return Q16;
    }
    public int getQ17() {
        return Q17;
    }
    public int getQ18() {
        return Q18;
    }
    public String getMemMobile() {
        return memMobile;
    }
}
