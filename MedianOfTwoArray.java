package ProblemStatementII;

import java.util.Scanner;

public class MedianOfTwoArray {
	
	public static void main(String[] args) {

		final int MAX_ARRAY_LEN = 1000;
		float medianValue = 0;
		int medianPos;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter size of 1st array :");
		int lengthOfArr1 = scan.nextInt();
		
		if(lengthOfArr1 < 0 || lengthOfArr1 > MAX_ARRAY_LEN){
			System.out.println("Array length has to be in between 0-1000");
			scan.close();
			return;
		}
		
		int[] arr1 = new int[lengthOfArr1];
		if(lengthOfArr1 > 0){
			System.out.println("Enter array 1 entries :");
		}
		for(int i =0;i < lengthOfArr1; i++)
		{
			arr1[i] = scan.nextInt();
		}
		
		System.out.println("Enter size of 2nd array :");
		int lengthOfArr2 = scan.nextInt();
		
		if(lengthOfArr2 < 0 || lengthOfArr2 > MAX_ARRAY_LEN){
			System.out.println("Array length has to be in between 0-1000");
			scan.close();
			return;
		}
		
		int[] arr2 = new int[lengthOfArr2];
		if(lengthOfArr2 > 0){
			System.out.println("Enter array 2 entries :");
		}
		for(int i =0;i < lengthOfArr2; i++)
		{
			arr2[i] = scan.nextInt();
		}
		scan.close();
				
		if(lengthOfArr1 < 0 || lengthOfArr1 > MAX_ARRAY_LEN 
				|| lengthOfArr2 < 0 || lengthOfArr2 > MAX_ARRAY_LEN){
			System.out.println("Array length has to be in between 0-1000");
			return;
		}
		
		// Merged array length can be till median position as we just want to get median.
		int mergeArrLen = (lengthOfArr1 + lengthOfArr2)/2 + 1;
		medianPos = ((lengthOfArr1 + lengthOfArr2)/2);
		
		int mergedArr[] = new int[mergeArrLen];
		int index1 = 0;
		int index2 = 0;
		
		if(lengthOfArr1 == 0){
			mergedArr = arr2;
		}else if(lengthOfArr2 == 0){
			mergedArr = arr1;
		}else{
			// Merge both sorted arrays till median position only
			// It has O(n/2) time complexity
			for (int i = 0; i < (mergeArrLen) ; i++){
				
				// Array 1 elements are over
				if(index1 >= lengthOfArr1){
					mergedArr[i] = arr2[index2];
					index2++;
					continue;
				}
				// Array 2 elements are over
				if(index2 >= lengthOfArr2){
					mergedArr[i] = arr1[index1];
					index1++;
					continue;
				}
				
				// Compare and put elements in sorted order in merged array
				if(arr1[index1] <= arr2[index2])
				{
					mergedArr[i] = arr1[index1];
					index1++;
				}else{
					mergedArr[i] = arr2[index2];
					index2++;
				}
			}
		}
		// Find the middle element
		// if single them return it
		// if 2 then get average of middle two 
		if((lengthOfArr1 + lengthOfArr2)%2 == 0){
			medianValue = (float)(mergedArr[medianPos - 1]+ mergedArr[medianPos])/ (float)2;
		}else{
			medianValue = mergedArr[medianPos];
		}
		
		System.out.print(medianValue);
	}

}
