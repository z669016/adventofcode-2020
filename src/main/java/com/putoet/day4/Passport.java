package com.putoet.day4;

import com.putoet.day2.Password;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Passport {
    public static final String BYR = "byr";
    public static final String IYR = "iyr";
    public static final String EYR = "eyr";
    public static final String HGT = "hgt";
    public static final String HCL = "hcl";
    public static final String ECL = "ecl";
    public static final String PID = "pid";
    public static final String CID = "cid";

    private String byr; // (Birth Year)
    private String iyr; // (Issue Year)
    private String eyr; // (Expiration Year)
    private String hgt; // (Height)
    private String hcl; // (Hair Color)
    private String ecl; // (Eye Color)
    private String pid; // (Passport ID)
    private String cid; // (Country ID)

    public Map<String,String> fields() {
        final Map<String, String> fields = new HashMap<>();
        if (byr != null) fields.put(BYR, byr);
        if (iyr != null) fields.put(IYR, iyr);
        if (eyr != null) fields.put(EYR, eyr);
        if (hgt != null) fields.put(HGT, hgt);
        if (hcl != null) fields.put(HCL, hcl);
        if (ecl != null) fields.put(ECL, ecl);
        if (pid != null) fields.put(PID, pid);
        if (cid != null) fields.put(CID, cid);

        return fields;
    }

    public static Passport of(Map<String,String> fields) {
        final Passport passport = new Passport();
        passport.byr = fields.get(BYR);
        passport.iyr = fields.get(IYR);
        passport.eyr = fields.get(EYR);
        passport.hgt = fields.get(HGT);
        passport.hcl = fields.get(HCL);
        passport.ecl = fields.get(ECL);
        passport.pid = fields.get(PID);
        passport.cid = fields.get(CID);

        return passport;
    }
}
