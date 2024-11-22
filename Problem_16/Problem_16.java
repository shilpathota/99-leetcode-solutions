class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        
        int wordIndex = 0; 
        int abbrIndex = 0; 
        int n = word.length();
        
        while (abbrIndex < abbr.length()) {
            char c = abbr.charAt(abbrIndex);
            
            if (Character.isDigit(c)) {
               
                if (c == '0') return false; 
                int num = 0;
                
                while (abbrIndex < abbr.length() && Character.isDigit(abbr.charAt(abbrIndex))) {
                    num = num * 10 + (abbr.charAt(abbrIndex) - '0'); 
                    abbrIndex++;
                }
                wordIndex += num; 
            } else {
                
                if (wordIndex >= n || word.charAt(wordIndex) != c) {
                    return false; 
                }
                wordIndex++;
                abbrIndex++;
            }
        }
        
        return wordIndex == n;
    }
}
