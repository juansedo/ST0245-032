/**
*
*
* @author juansedo, LizOriana1409
*/
class CodingBat {
    
            //Array-2
    
    public int sum13(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 13) i++;
            else sum += nums[i];
        }
        return sum;
    }
       
    public int sum67(int[] nums) {
        int sum = 0;
        boolean able = true;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == 6) able = false;
            else if(nums[i] == 7 && !able) able = true;
            else if (able) sum += nums[i];
        }
        return sum;
    }
    
            //Array-3
    
    public int[] fix45(int[] nums) {
        int i = 0;
        while(i + 1 < nums.length) {
            if(nums[i] == 4 && nums[i+1] != 5) {
                int j = 0;
                while (j < nums.length) {
                    if (nums[j] == 5) {
                        if (j < 1 || nums[j-1] != 4) {
                            int aux = nums[i+1];
                            nums[i+1] = nums[j];
                            nums[j] = aux;
                            break;
                        }
                    }
                j++;
                }
            }
            i++;
        }
        return nums;
    }
}
