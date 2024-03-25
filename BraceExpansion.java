import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BraceExpansion {
    // TC: O(K ^ (L/K)) where K is average length of group and L is length of String
    // SC: O(K ^ (L/K)) where K is average length of group and L is length of String
    public String[] expand(String s) {
        List<List<Character>> groups = new ArrayList<>();
        int n = s.length();
        for (int pos = 0; pos < s.length(); pos++) {
            List<Character> group = new ArrayList<>();
            if (s.charAt(pos) != '{') {
                group.add(s.charAt(pos));
            } else {
                while (s.charAt(pos) != '}') {
                    if (s.charAt(pos) >= 'a' && s.charAt(pos) <= 'z') {
                        group.add(s.charAt(pos));
                    }
                    pos++;
                }
                Collections.sort(group);
            }
            groups.add(group);
        }
        List<String> res = new ArrayList<>();
        backtrack(res, groups, 0, new StringBuilder());
        String[] result = new String[res.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    private void backtrack(List<String> res, List<List<Character>> groups, int start, StringBuilder sb) {
        if (start == groups.size()) {
            res.add(sb.toString());
            return;
        }
        List<Character> group = groups.get(start);
        for (int i = 0; i < group.size(); i++) {
            sb.append(group.get(i));
            backtrack(res, groups, start + 1, sb);
            sb.setLength(sb.length() - 1);
        }
    }
}
