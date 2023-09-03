package com.putoet.day4;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

record Passport(String byr, String iyr, String eyr, String hgt, String hcl, String ecl, String pid, String cid) {
    public static final String BYR = "byr";
    public static final String IYR = "iyr";
    public static final String EYR = "eyr";
    public static final String HGT = "hgt";
    public static final String HCL = "hcl";
    public static final String ECL = "ecl";
    public static final String PID = "pid";
    public static final String CID = "cid";

    public Map<String, String> fields() {
        final var fields = new HashMap<String, String>();
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

    public static Passport of(@NotNull Map<String, String> fields) {
        return new Passport(
                fields.get(BYR),
                fields.get(IYR),
                fields.get(EYR),
                fields.get(HGT),
                fields.get(HCL),
                fields.get(ECL),
                fields.get(PID),
                fields.get(CID)
        );
    }
}
