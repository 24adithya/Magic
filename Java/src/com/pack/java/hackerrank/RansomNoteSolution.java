package com.pack.java.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.Stream;

/*
 * Check out the resources on the page's right side to learn more about hashing. The video tutorial is by Gayle Laakmann McDowell, author of the best-selling interview book Cracking the Coding Interview.

Harold is a kidnapper who wrote a ransom note, but now he is worried it will be traced back to him through his handwriting. He found a magazine and wants to know if he can cut out whole words from it and use them to create an untraceable replica of his ransom note. The words in his note are case-sensitive and he must use only whole words available in the magazine. He cannot use substrings or concatenation to create the words he needs.

Given the words in the magazine and the words in the ransom note, print Yes if he can replicate his ransom note exactly using whole words from the magazine; otherwise, print No.

For example, the note is "Attack at dawn". The magazine contains only "attack at dawn". The magazine has all the right words, but there's a case mismatch. The answer is .

Function Description

Complete the checkMagazine function in the editor below. It must print  if the note can be formed using the magazine, or .

checkMagazine has the following parameters:

magazine: an array of strings, each a word in the magazine
note: an array of strings, each a word in the ransom note
Input Format

The first line contains two space-separated integers,  and , the numbers of words in the  and the .. 
The second line contains  space-separated strings, each . 
The third line contains  space-separated strings, each .

Constraints

.
Each word consists of English alphabetic letters (i.e.,  to  and  to ).
Output Format

Print Yes if he can use the magazine to create an untraceable replica of his ransom note. Otherwise, print No.

Sample Input 0

6 4
give me one grand today night
give one grand today
Sample Output 0

Yes
Sample Input 1

6 5
two times three is not four
two times two is four
Sample Output 1

No
Explanation 1

'two' only occurs once in the magazine.

Sample Input 2

7 4
ive got a lovely bunch of coconuts
ive got some coconuts
Sample Output 2

No
Explanation 2

Harold's magazine is missing the word .
 */

public class RansomNoteSolution {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
    	//System.out.println(Stream.of(note).filter( word -> Arrays.asList(magazine).contains(word)).count());
    	if(Stream.of(note).filter( word -> {
    		Iterator<String> iterator =  Arrays.asList(magazine).iterator();
   		 while(iterator.hasNext()) {
   			if(iterator.next().contains(word)) {
   				iterator.remove();
   				return true;
   			} else {
   				return false;
   			}
   		 }
    		}).count() == note.length) {
    		System.out.println("YES");
    	} else {
    		System.out.println("NO");
    	}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//    	System.out.println(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"));

//        int q = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

//        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();
            String[] s1Array = s1.split(" ");

            String s2 = scanner.nextLine();
            String[] s2Array = s2.split(" ");

            checkMagazine(s1Array, s2Array);

            bufferedWriter.newLine();
//        }

        bufferedWriter.close();

        scanner.close();
    }
}
