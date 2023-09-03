package com.putoet.day4;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

class EnhancedPassportValidator extends PassportValidator {
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
    public boolean isValid(@NotNull Passport passport) {
        var valid = super.isValid(passport);
        if (valid) {
            final var fields = passport.fields();
            return fields.entrySet().stream()
                    .filter(entry -> !entry.getKey().equals(Passport.CID))
                    .allMatch(entry -> validators.get(entry.getKey()).test(entry.getValue()));
        }

        return false;
    }

    public static boolean byrValidator(@NotNull String byr) {
        if (byr.length() == 4) {
            try {
                final var value = Integer.parseInt(byr);
                return value >= 1920 && value <= 2002;
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public static boolean iyrValidator(@NotNull String iyr) {
        if (iyr.length() == 4) {
            try {
                final var value = Integer.parseInt(iyr);
                return value >= 2010 && value <= 2020;
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public static boolean eyrValidator(@NotNull String eyr) {
        if (eyr.length() == 4) {
            try {
                final var value = Integer.parseInt(eyr);
                return value >= 2020 && value <= 2030;
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public static boolean hgtValidator(@NotNull String hgt) {
        if (hgt.length() > 2) {
            try {
                final var value = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
                final var metric = hgt.substring(hgt.length() - 2);
                return (metric.equals("cm") && value >= 150 && value <= 193) ||
                       (metric.equals("in") && value >= 59 && value <= 76);
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public static boolean hclValidator(@NotNull String hcl) {
        return hcl.matches("#[0-9a-f]{6}");
    }

    private static final Set<String> VALID_ECL = Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
    public static boolean eclValidator(@NotNull String ecl) {
        return VALID_ECL.contains(ecl);
    }

    public static boolean pidValidator(@NotNull String pid) {
        return pid.matches("[0-9]{9}") && Integer.parseInt(pid) > 0;
    }
}
