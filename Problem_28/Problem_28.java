class Solution {
    public String minRemoveToMakeValid(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Integer> stch = new Stack<Integer>();
        for(int i=0;i<s.length();i++){
            if (s.charAt(i) == '(') {
                stch.push(i);
            } if (s.charAt(i) == ')') {
                if (stch.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stch.pop();
                }
            }
        }
        while(stch.size()>0){
            indexesToRemove.add(stch.pop());
        }
         StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
