import java.util.Arrays;

class Ex1_1_3 {
    public static void main(String[] args) {
        if (args.length == 3) {
            int[] nums = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
            boolean allEqual = nums[0] == nums[1] && nums[1] == nums[2];
            System.out.println(allEqual ? "equal": "not equal");
        } else {
            System.out.println("Error: incorrect arguments (3 integers required)");
        }
    }
}
