package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CodeTest {
	
	public static void main(String[] args) {
		CodeTest main = new CodeTest();
		int[] arr1 = {};
		System.out.println(main.solution(arr1));
		System.out.println(Arrays.toString(arr1));
		int[] arr2 = {1, 5, 3, 3, 7};
		System.out.println(main.solution(arr2));
		System.out.println(Arrays.toString(arr2));
		int[] arr3 = {1, 3, 5, 3, 4};
		System.out.println(main.solution(arr3));
		System.out.println(Arrays.toString(arr3));
		int[] arr4 = {1, 3, 5};
		System.out.println(main.solution(arr4));
		System.out.println(Arrays.toString(arr4));
		
		System.out.println(main.solution(""));
		System.out.println(main.solution("We test coders. Give us a try?"));
		System.out.println(main.solution("Forget   CVs..Save time . x x"));
	}
	
	public boolean solution(int[] a) {
		if (a == null || a.length == 0) {
			return false;
		}
		Set<Integer> swap = new HashSet<Integer>();
		for (int i = 0; i < a.length; i++) {
			int v1 = a[i];
			for (int j = i + 1; j < a.length; j++) {
				int v2 = a[j];
				if (v1 > v2) {
					swap.add(Integer.valueOf(v2));
				}
			}
			if (swap.size() > 1) {
				return false;
			}
		}
		Arrays.sort(a);
		return true;
	}
	
	public int solution(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int longest = 0;
		for (String sentence : s.split("[\\.\\?\\!]")) {
			if (sentence.length() == 0) {
				continue;
			}
			int words = 0;
			for (String word : sentence.replaceAll(" ", "|").split("\\|")) {
				if (word.length() == 0) {
					continue;
				}
				words++;
			}
			if (words > longest) {
				longest = words;
			}
		}
		return longest;
	}
}
