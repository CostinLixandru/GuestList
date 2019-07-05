
public class MinimalDif {
	public static int minDiference(int[] A) {
		int minSum = -1;
		int[] right = new int[A.length];
		int[] left = new int[A.length];
		int sum = 0; 
		for(int i=0;i<A.length;i++) {
			sum += A[i];
			left[i]=sum;
		}
		sum = 0;
		for(int i=A.length-1;i>=0;i--) {
			sum += A[i];
			right[i]=sum;
		}
		for(int i=0; i<A.length-1; i++) {
			if(Math.abs(left[i]-right[i])<minSum || minSum == -1) {
				minSum = Math.abs(left[i]-right[i+1]);
			}
		}
		return minSum;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {3,1,2,4,3};
		System.out.println(minDiference(a));
	}

}
