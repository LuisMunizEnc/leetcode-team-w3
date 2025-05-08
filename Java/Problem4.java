class Solution {
    boolean isMatch(String s, String p) {
        Boolean[][] save = new Boolean[s.length()+1][p.length()+1];
        return match(save,s,p,0,0);
    }

    private boolean match(Boolean[][] save, String s, String p,int i, int j){
        if(save[i][j] != null) 
            return save[i][j];
        if(j == p.length()){
            save[i][j] = (i == s.length());
            return save[i][j];
        }
            

        boolean firstMatch = 
            (i < s.length() && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)));
        
        if(j + 1 < p.length() && p.charAt(j+1) == '*'){
            boolean zeroOccurrences = match(save,s,p,i,j+2);
            boolean oneOrMoreOccurrences = firstMatch && match(save,s,p,i+1, j);
            save[i][j] = zeroOccurrences || oneOrMoreOccurrences;
        }else{
            save[i][j] = firstMatch && match(save,s,p,i+1,j+1);
        }

        return save[i][j];
    }
}