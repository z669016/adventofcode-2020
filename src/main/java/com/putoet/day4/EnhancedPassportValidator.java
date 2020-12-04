package com.putoet.day4;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class EnhancedPassportValidator extends PassportValidator {
    private final Map<String, Predicate<String>> validators = Map.of(
            Passport.BYR, EnhancedPassportValidator::byrValidator,
            Passport.IYR, EnhancedPassportValidator::iyrValidator,
            Passport.EYR, EnhancedPassportValidator::eyrValidator,
            Passport.HGT, EnhancedPassportValidator::hgtValidator,
            Passport.HCL, EnhancedPassportValidator::hclValidator,
            Passport.ECL, EnhancedPassportValidator::eclValidator,
            Passport.PID, EnhancedPassportValidator::pidValidator
    );

    @Override
    public boolean isValid(Passport passport) {
        boolean valid = super.isValid(passport);
        if (valid) {
            final Map<String,String> fields = passport.fields();
            return fields.entrySet().stream()
                    .filter(entry -> !entry.getKey().equals(Passport.CID))
                    .allMatch(entry -> validators.get(entry.getKey()).test(entry.getValue()));
        }

        return valid;
    }

    public static boolean byrValidator(String byr) {
        if (byr.length() == 4) {
            try {
                final int value = Integer.parseInt(byr);
                return value >= 1920 && value <= 2002;
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public static boolean iyrValidator(String iyr) {
        if (iyr.length() == 4) {
            try {
                final int value = Integer.parseInt(iyr);
                return value >= 2010 && value <= 2020;
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public static boolean eyrValidator(String eyr) {
        if (eyr.length() == 4) {
            try {
                final int value = Integer.parseInt(eyr);
                return value >= 2020 && value <= 2030;
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public static boolean hgtValidator(String hgt) {
        if (hgt.length() > 2) {
            try {
                final int value = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
                final String metric = hgt.substring(hgt.length() - 2);
                return (metric.equals("cm") && value >= 150 && value <= 193)
                        || (metric.equals("in") && value >= 59 && value <= 76);
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public static boolean hclValidator(String hcl) {
        return hcl.matches("#[0-9a-f]{6}");
    }

    private static final Set<String> VALID_ECL = Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
    public static boolean eclValidator(String ecl) {
        return VALID_ECL.contains(ecl);
    }

    public static boolean pidValidator(String pid) {
        return pid.matches("[0-9]{9}") && Integer.parseInt(pid) > 0;
    }
}
