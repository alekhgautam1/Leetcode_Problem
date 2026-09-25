import java.util.*;

class Solution {
    int ptr = 0;

    public List<String> braceExpansionII(String expression) {
        ptr = 0;
        Set<String> set = parseExpression(expression);
        List<String> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }

    // Parses a group of ',' separated items
    private Set<String> parseExpression(String expr) {
        Set<String> res = new TreeSet<>();
        Set<String> curGroup = null;

        while (ptr < expr.length() && expr.charAt(ptr) != '}') {
            if (expr.charAt(ptr) == ',') {
                ptr++;
                if (curGroup != null) {
                    res.addAll(curGroup);
                    curGroup = null;
                }
            } else {
                Set<String> next = parseFactor(expr);
                curGroup = combine(curGroup, next);
            }
        }

        if (curGroup != null) {
            res.addAll(curGroup);
        }
        return res;
    }

    // Parses single concatenated factors (e.g. letters or braced sub-expressions)
    private Set<String> parseFactor(String expr) {
        char c = expr.charAt(ptr);
        if (c == '{') {
            ptr++; // skip '{'
            Set<String> res = parseExpression(expr);
            ptr++; // skip '}'
            return res;
        } else {
            ptr++;
            Set<String> res = new HashSet<>();
            res.add(String.valueOf(c));
            return res;
        }
    }

    // Cartesian product of two set groups
    private Set<String> combine(Set<String> s1, Set<String> s2) {
        if (s1 == null) return s2;
        if (s2 == null) return s1;
        Set<String> res = new HashSet<>();
        for (String a : s1) {
            for (String b : s2) {
                res.add(a + b);
            }
        }
        return res;
    }
}