import java.util.*;

class Solution8 {
    public int[] solution(String s) {

        s = s.substring(1, s.length() - 1);

        LinkedList<LinkedList<Integer>> sets = new LinkedList();

        int start = 0;
        int end = 0;
        int n = s.length();

        while(start < n && end < n) {
            while(s.charAt(start) == '{'
                    || s.charAt(start) == ','){   start++;    }

            while(s.charAt(end) != '}'){    end++;  }

            String set = s.substring(start, end);
            String[] array = set.split(",");

            LinkedList<Integer> setList = new LinkedList();

            for(int i = 0; i < array.length; i++) {
                setList.add(Integer.parseInt(array[i]));
            }

            sets.add(setList);

            start = end + 1;
            end = start;
        }

        Collections.sort(sets, new Comparator<LinkedList<Integer>>() {
            public int compare(LinkedList<Integer> l1, LinkedList<Integer> l2) {
                return Integer.compare(l1.size(), l2.size());
            }
        });

        int[] answer = new int[sets.size()];

        Set<Integer> hashset = new HashSet();

        for(int i = 0; i < sets.size(); i++) {
            List<Integer> set = sets.get(i);

            for(Integer ele : set) {
                if(hashset.contains(ele) == true) { continue; }
                else {
                    hashset.add(ele);
                    answer[i] = ele;
                }
            }
        }

        return answer;
    }
}