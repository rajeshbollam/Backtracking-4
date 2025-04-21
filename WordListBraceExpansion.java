//The approach here is to group the characters within braces together and characters which are not enclosed into brace will also be grouped
//Now we explore all the possibilites from all groups using backtracking
//Time complexity: O(k^(n/k)) where k is the avg. length of the groups and n is the effective length of the string without braces and commas
//Space Complexity: O(k^(n/k)) to convert result into a string array
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution{
    public String[] expand(String s){
        List<String> result = new ArrayList<>();
        List<List<Character>> groups = new ArrayList<>();
        int i = 0;
        while(i < s.length()){
            List<Character> group = new ArrayList<>();
            char ch = s.charAt(i);
            if(ch == '{'){
                i++;
                while(s.charAt(i)!= '}'){
                    if(s.charAt(i) != ','){
                        group.add(s.charAt(i));
                    }
                    i++; 
                }
            } else{
                group.add(ch);
            }
            i++;
            Collections.sort(group);
            groups.add(group);
        }
        StringBuilder sb = new StringBuilder();
        backtrack(groups, 0, sb, result);
        String[] strArr = new String[result.size()];
        for(int k = 0; k< result.size(); k++){
            strArr[k] = result.get(k);
        }
        return strArr;
    }

    private void backtrack(List<List<Character>> groups, int idx, StringBuilder path, List<String> result){
        //base
        if(idx == groups.size()){
            result.add(path.toString());
            return;
        }
        //logic
        List<Character> group = groups.get(idx);
        for(int i = 0; i<group.size(); i++){
            char ch = group.get(i);
            int l = path.length();
            //action
            path.append(ch);
            //recurse
            backtrack(groups, idx+1, path, result);

            //backtrack
            path.setLength(l);
        }
    }
}