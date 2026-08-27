class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        StringBuilder result = new StringBuilder();
        if (dfs(0, freq, target, result, true)) {
            return result.toString();
        }
        return "";
    }

    private boolean dfs(int idx, int[] freq, String target, StringBuilder result, boolean tight) {
        if (idx == target.length()) return true;

        int start = 0;
        if (tight) start = target.charAt(idx) - 'a';

        for (int c = start; c < 26; c++) {
            if (freq[c] == 0) continue;

            result.append((char)(c + 'a'));
            freq[c]--;

            boolean nextTight = tight && (c == target.charAt(idx) - 'a');

            if (dfs(idx + 1, freq, target, result, nextTight)) {
                if (!nextTight || result.toString().compareTo(target.substring(0, idx + 1)) > 0) {
                    return true;
                }
            }

            result.deleteCharAt(result.length() - 1);
            freq[c]++;
        }
        return false;
    }
}