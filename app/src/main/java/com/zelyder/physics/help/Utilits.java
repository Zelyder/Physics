package com.zelyder.physics.help;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Utilits {

    private boolean isFull;
    private final String re = Pattern.quote("?");
    private final String rePlus = Pattern.quote("+");

    private String charsToSting(char[] chars, int length) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(chars[i]);
        }
        return str.toString();
    }

    public String toUnknown(String formula) {
        Log.d("LOL","До: " + formula);
        for (int i = 0; i < formula.length(); i++) {
            if (isNormal2(formula.charAt(i))) {
                if (formula.charAt(i) == '#') {
                    StringBuilder multiChars = new StringBuilder();
                    boolean isFinish = false;
                    for (int j = i + 1; !isFinish; j++) {
                        if (j < formula.length()) {
                            if (formula.charAt(j) == '#') {
                                isFinish = true;
                            } else if (isNormal2(formula.charAt(j))){
                                    multiChars.append(formula.charAt(j));
                            }
                        } else {
                            isFinish = true;
                        }

                        i = j;
                    }
                    if(Objects.equals(multiChars.toString(), "cosα")
                            || Objects.equals(multiChars.toString(), "sinα")
                            || Objects.equals(multiChars.toString(), "sinφ")
                            || Objects.equals(multiChars.toString(), "const")){
                        formula = formula.replaceFirst("#" + multiChars.toString() + "#", "?");
                    }else
                    formula = formula.replaceFirst(multiChars.toString(), "?");
                } else {
                    if (i >= formula.length()) {
                        return formula;
                    } else {
                        if (formula.charAt(i) == '+') {
                            formula = formula.replaceFirst(rePlus, "?");
                        } else {
                            formula = formula.replaceFirst(String.valueOf(formula.charAt(i)), "?");
                        }
                    }
                }
            }
        }
        Log.d("LOL","После: " + formula);
        return formula;
    }

    public String addChar(String formula, String ch) {
        boolean isfind = false;
        for (int i = 0; !isfind; i++) {
            if (i >= formula.length()) {
                isfind = true;
                isFull = true;
            } else if (formula.charAt(i) == '?') {
                formula = formula.replaceFirst(re, ch);
                isfind = true;
                checkFull(formula);
            }
        }
        return formula;
    }

    public String delChar(String formula) {
        boolean isfind = false;
        boolean isFinish;
        boolean isSubscript;
        formula = reverse(formula);
        for (int i = 0; !isfind; i++) {
            if (i >= formula.length()) {
                isfind = true;
            } else if (isNormal2(formula.charAt(i))) {
                if (formula.charAt(i) == '#') {

                    StringBuilder multiChars = new StringBuilder();
                    isFinish = false;
                    isSubscript = false;
                    multiChars.append(formula.charAt(i));
                    for (int j = i + 1; !isFinish; j++) {
                        if (j < formula.length()) {
                            if (formula.charAt(j) == '#') {
                                multiChars.append(formula.charAt(j));
                                isFinish = true;
                            } else {
                                if (formula.charAt(j) != '?' && isNormal2(formula.charAt(j))) {
                                    multiChars.append(formula.charAt(j));
                                } else if (formula.charAt(j) == '₀' || formula.charAt(j) == 'ₓ'
                                        || formula.charAt(j) == '+' || formula.charAt(j) == '₁'
                                        || formula.charAt(j) == '₂' || formula.charAt(j) == 'ₘ'
                                        || formula.charAt(j) == 'ₐ' || formula.charAt(j) == 'ₖ'
                                        || formula.charAt(j) == 'ₚ' || formula.charAt(j) == '₃'
                                        || formula.charAt(j) == 'ₑ') {
                                    isSubscript = true;
                                }
                            }
                        } else {
                            isFinish = true;
                        }
                        i = j;
                    }
                    if (multiChars.length() != 0 && !Objects.equals(multiChars.toString(), "##")) {
                        String output = multiChars.toString();
                        if(isSubscript){
                            output = output.replace("#","");
                        }
                        if (Objects.equals(output, "+")) {
                            formula = formula.replaceFirst(rePlus, "?");
                        }else {
                            formula = formula.replaceFirst(output, "?");
                        }
                        isfind = true;
                    }

                } else {
                    if (i > formula.length()) {
                        return formula;
                    } else {
                        if (formula.charAt(i) == '+') {
                            formula = formula.replaceFirst(rePlus, "?");
                        } else {
                            formula = formula.replaceFirst(String.valueOf(formula.charAt(i)), "?");
                        }
                        isfind = true;
                    }
                }
            }
        }
        return reverse(formula);
    }

    private void checkFull(String formula) {
        boolean isfind = false;
        char[] chars = formula.toCharArray();
        for (int i = 0; i < formula.length(); i++) {
            if (chars[i] == '?') {
                isfind = true;
            }
        }
        isFull = !isfind;
    }

    public boolean isFull() {
        return isFull;
    }

    public boolean equals(String checkableStr, String correctStr) {
        String[] correctArr = correctStr.split("[=]");
        String[] checkableArr = checkableStr.split("[=]");

        String cleanStr = clearStr(correctStr);
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < cleanStr.length(); i++) {
            if (cleanStr.charAt(i) != '/' && cleanStr.charAt(i) != '+' && cleanStr.charAt(i) != '('
                    && cleanStr.charAt(i) != ')') {
                if (cleanStr.charAt(i) == '#') {
                    StringBuilder multiChars = new StringBuilder();

                    for (int j = i + 1; cleanStr.charAt(j) != '#'; j++) {
                        multiChars.append(cleanStr.charAt(j));
                        i = j + 1;
                    }
                    map.put(multiChars.toString(), i + 1);

                } else {
                    map.put(String.valueOf(cleanStr.charAt(i)), i + 1);
                }
            }
        }
        try {
            return Objects.equals(calculate(map, correctArr[0]), calculate(map, checkableArr[0]))
                    && Objects.equals(calculate(map, correctArr[1]), calculate(map, checkableArr[1]));
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    private String clearStr(String string) {
        if (string.contains("_")) {
            string = string.replace("_", "");
        }
        if (string.contains(" ")) {
            string = string.replace(" ", "");
        }
        if (string.contains("|")) {
            string = string.replace("|", "");
        }
        if (string.contains("~")) {
            string = string.replace("~", "");
        }
        if (string.contains("=")) {
            string = string.replace("=", "");
        }
        if (string.contains("Ѫ")) {
            string = string.replace("Ѫ", "");
        }
        if (string.contains("Ѩ")) {
            string = string.replace("Ѩ", "");
        }
        if (string.contains("₁")) {
            string = string.replace("₁", "");
        }
        if (string.contains("₂")) {
            string = string.replace("₂", "");
        }
        if (string.contains("ₘ")) {
            string = string.replace("ₘ", "");
        }
        if (string.contains("ₐ")) {
            string = string.replace("ₐ", "");
        }
        if (string.contains("ₖ")) {
            string = string.replace("ₖ", "");
        }
        if (string.contains("ₚ")) {
            string = string.replace("ₚ", "");
        }
        if (string.contains("₃")) {
            string = string.replace("₃", "");
        }
        if (string.contains("ₑ")) {
            string = string.replace("ₑ", "");
        }
        if (string.contains("◤")) {
            string = string.replace("◤", "");
        }
        if (string.contains("◢")) {
            string = string.replace("◢", "");
        }
        if (string.contains("◇")) {
            string = string.replace("◇", "");
        }
        return string;
    }

    private float calculate(Map<String, Integer> map, String string) {
        StringBuilder outputStr = new StringBuilder();
        PPN ppn = new PPN();

        for (int i = 0; i < string.length(); i++) {
            if (isNormal(string.charAt(i))) {
                if (string.charAt(i) == '#') {
                    StringBuilder multiChars = new StringBuilder();
                    for (int j = i + 1; string.charAt(j) != '#'; j++) {
                        multiChars.append(string.charAt(j));
                        i = j + 1;
                    }
                    if (map.containsKey(multiChars.toString())) {
                        outputStr.append(map.get(multiChars.toString()));
                    } else {
                        outputStr.append("999");
                    }

                } else if (string.charAt(i) == '+') {
                    outputStr.append('+');
                } else if (string.charAt(i) == '-') {
                    outputStr.append('-');
                }else if (string.charAt(i) == '(') {
                    outputStr.append('(');
                }else if (string.charAt(i) == ')') {
                    outputStr.append(')');
                }else if (string.charAt(i) == '²') {
                    outputStr.append('²');
                }else if (string.charAt(i) == 'Δ') {
                    outputStr.append('Δ');
                } else if (string.charAt(i) != '/' && !map.containsKey(String.valueOf(string.charAt(i)))) {
                    outputStr.append("999");
                } else {
                    if (map.containsKey(String.valueOf(string.charAt(i)))) {
                        outputStr.append(map.get(String.valueOf(string.charAt(i))));
                    }
                }

                if (outputStr.length() > 0 && i + 1 < string.length()
                        && isNormal(string.charAt(i + 1)) && string.charAt(i) != '+'
                        && string.charAt(i) != '-' && string.charAt(i) != '('
                        && string.charAt(i) != ')' && string.charAt(i) != 'Δ') {
                    if (string.charAt(i) == '/') {
                        outputStr.append('/');
                    } else if (string.charAt(i + 1) != '/' && string.charAt(i + 1) != '+'
                            && string.charAt(i + 1) != '-' && string.charAt(i + 1) != '²'
                            && string.charAt(i + 1) != '(' && string.charAt(i + 1) != ')') {
                        outputStr.append('*');
                    }
                }
            }
        }
        Log.d("LOL",string);
        Log.d("LOL",outputStr.toString());
        Log.d("LOL","почситали: " + ppn.eval(outputStr.toString()));
        return ppn.eval(outputStr.toString());
    }

    private boolean isNormal(char ch) {
        return ch != '~' && ch != ' ' && ch != '_' && ch != '|' && ch != 'Ѩ' && ch != 'Ѫ'
                && ch != '◤' && ch != '◢' && ch != '◇';
    }

    private boolean isNormal2(char ch) {
        return ch != '=' && ch != '/' && ch != '_' && ch != ' ' && ch != '?' && ch != '|'
                && ch != '~' && ch != '₀' && ch != 'ₓ' && ch != '(' && ch != ')' && ch != 'Ѩ'
                && ch != 'Ѫ' && ch != '₁' && ch != '₂' && ch != 'ₘ' && ch != 'ₐ' && ch != 'ₖ'
                && ch != 'ₚ' && ch != '₃' && ch != 'ₑ' && ch != '◤' && ch != '◢' && ch != '◇';
    }

    private String reverse(String string) {
        StringBuilder builder = new StringBuilder(string);
        builder.reverse();
        string = builder.toString();
        return string;
    }
}
