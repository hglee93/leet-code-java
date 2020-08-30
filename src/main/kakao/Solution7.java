import java.util.*;

/**
 * 불량사용자
 */
class Solution7 {
    public boolean isCheck(String userId, String banId){

        if(userId.length() != banId.length()) { return false; }

        for(int i = 0; i < userId.length(); i++) {
            if(userId.charAt(i) == banId.charAt(i) || banId.charAt(i) == '*') { continue; }
            else if(userId.charAt(i) != banId.charAt(i)){
                return false;
            }
        }

        return true;
    }

    public List<List<String>> table = new ArrayList();

    public List<Set<String>> sets = new ArrayList();


    public void dfs(int depth, Set<String> set) {

        if(depth == table.size()) {
            for(int i = 0; i < sets.size(); i++) {
                if(sets.get(i).equals(set) == true) { return; }

            }
            Set<String> ele = new HashSet<String>();
            for(String id : set) { ele.add(id); }
            sets.add(ele);

            return;
        }

        List<String> userIdList = table.get(depth);

        for(int i = 0; i < userIdList.size(); i++) {
            String userId = userIdList.get(i);
            if(set.contains(userId) == false) {
                set.add(userId);
                dfs(depth + 1, set);
                set.remove(userId);
            }
        }

    }

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        for(int i = 0; i < banned_id.length; i++) {
            table.add(new ArrayList<String>());
            for(int j = 0; j < user_id.length; j++) {
                if(isCheck(user_id[j], banned_id[i]) == true) {
                    table.get(i).add(user_id[j]);
                }
            }
        }

        dfs(0, new HashSet<String>());
        return sets.size();
    }
}