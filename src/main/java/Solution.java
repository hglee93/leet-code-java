public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int answer = 0;

        for(int i = 0; i < 32; i++) {

            int temp = n & (1 << i);
            if(i < 16) {
                temp = temp << (31 - (i * 2));
            } else {
                temp = temp >>> ((i * 2) - 31);
            }
            answer = answer | temp;
        }

        return answer;
    }

    public int[] twoSum(int[] nums, int target) {

        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(target == (nums[i] + nums[j])) {
                    int[] answer = new int[2];
                    answer[0] = i;
                    answer[1] = j;
                    return answer;
                }
            }
        }

        return null;
    }
}
