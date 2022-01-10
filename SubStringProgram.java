package ProblemStatementIV;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class SubStringProgram {

	final int MAX_SENT_LEN = 104;
	final int MAX_WORD_COUNT = 5000;
	final int MAX_WORD_LEN = 30;
	
	/**
	 * @param words - list of words
	 * @param permutation - OUT - generated permutation string  
	 * @return - list of all permutations
	 */
	public TreeSet<String> getPermutations(ArrayList<String> words, String permutation){
		TreeSet<String> permutations = new TreeSet<String>();
		
		// if word count reaches to 0, permutation string is ready
		// so add it to the permutations list.
		if(words.size() == 0){
			// collect only unique permutations. 
			permutations.add(permutation);
			return permutations;
		}
		
		// Keep tempStr fixed and make permutations of remaining words by recursion. 
		for (int i=0; i<words.size(); i++)  {
			String tempStr = words.get(i);
			ArrayList<String> wordsTemp = new ArrayList<String>(words);
			wordsTemp.remove(i);
			permutations.addAll(getPermutations(wordsTemp, permutation + tempStr));
		}
		return permutations;
	}

	/**
	 * @param s - string that need to scan 
	 * @param permutations - words that need to be searched in string 
	 * @return - array of indices of occurrences. 
	 */
	public int[] getIndices(String s, TreeSet<String> permutations){
		
		int []indices = new int[s.length()];
		
		for(int i = 0; i<indices.length; i++){
			indices[i] = -1;
		}
		
		int substringLen = permutations.first().length();
		if(s.length() < substringLen)
		{
			return null;
		}
		int index = 0;
		// loop through string and get the substring one by one of substringLen
		// check if it present in permutations list.
		for(int i=0; i < s.length() - substringLen; i++){
			
			String tempString = s.substring(i, i + substringLen);
			if(permutations.contains(tempString)){
				indices[index++] = i;
			}
		}
		return indices;
	}

	public static void main(String[] args) {

		SubStringProgram subStringProgramObj = new SubStringProgram();
		
		System.out.println("Enter String :");
		Scanner scan = new Scanner(System.in);
		String s= scan.nextLine();
		
		ArrayList<String> wordsList = new ArrayList<String>();
		
		System.out.println("Enter how many words :");
		int wordCount = scan.nextInt();
		
		//Check work count 
		if(wordCount < 1 || wordCount > subStringProgramObj.MAX_WORD_COUNT){
			System.out.println("Words count has to be between 1 to 5000");
			scan.close();
			return;
		}
		
		//consuming the <enter> from input above  
		scan.nextLine(); 
		
		System.out.println("Enter Words :");
		for(int i =0;i < wordCount; i++)
		{
			String word = scan.nextLine();
			wordsList.add(word);
		}
		scan.close();
		
		// check string length condition
		if(s.length() < 1 || s.length() > subStringProgramObj.MAX_SENT_LEN){
			System.out.println("String length has to be between 1 to 104");
			return;
		}
				
		int wordlength = wordsList.get(0).length();
		for(String word:wordsList){
			if(word.length() < 1 || word.length() > subStringProgramObj.MAX_WORD_LEN){
				System.out.println("Word length has to be between 1 to 30");
				return;
			}
			if(word.length() != wordlength){
				System.out.println("All Words has to be of same length");
				return;
			}
		}
		
		// no need to go further if wordlength * wordCount is greater than string length
		if(wordlength * wordCount > subStringProgramObj.MAX_SENT_LEN){
			System.out.println("[]");
			return;
		}
		
		String permutations1 = "";
		TreeSet<String> permutations = subStringProgramObj.getPermutations(wordsList, permutations1);
				
// 		To print all permutations
//		int i = 1;
//		for(String str:permutations){
//			System.out.println(i++ + " " + str);
//		}
	
		// Find the indices of each permutation in string.
		int []indices = subStringProgramObj.getIndices(s, permutations);
		if(indices == null){
			System.out.println("[]");
		}else{
			System.out.print("[ ");
			for (int index = 1; index < indices.length; index++){
				if(indices[index - 1] != -1){
					System.out.print(indices[index-1] );
				}else{
					break;
				}
				
				if(indices[index] != -1){
					System.out.print(",");
				}
			}
		}
		System.out.print( " ]" );
	}
}
