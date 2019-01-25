public class TheresNoNum {
    public static void main(String[] args) {
        int[] nums = {1,4,3,5};
        int[] theres = {1,2,3,4,5};

        for (int i= 0; i< nums.length; i++) {
            for (int j = 0; j < theres.length; j++) {
                if (theres[j] == nums[i])
                    theres[j] = -1;
            }
        }
        for (int i = 0; i < theres.length; i++) {

            if (theres[i] != -1)
                System.out.println(theres[i]);
        }
    }
}
