package com.pack.java.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    static void checkMagazineAlternate(String[] magazine,
                              String[] note) {
        List<String> magazineList = new CopyOnWriteArrayList<>(magazine);
        List<String> noteList = new CopyOnWriteArrayList<>(note);
        
        for(String tempWord : magazineList) {
            if(noteList.contains(tempWord)) {
                noteList.remove(tempWord);
            }
        }
        
        if(noteList.isEmpty()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
    
    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine,
                              String[] note) {
        List<String> magazineList = new CopyOnWriteArrayList<>(magazine);
        List<String> noteList = new CopyOnWriteArrayList<>(note);
        
        if (noteList.stream().filter(word -> {
                if(magazineList.contains(word)) {
                    magazineList.remove(word);
                    return true;
                } else {
                    return false;
                }
        }).count() == note.length) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
    
    // Complete the substrCount function below.
    /*static long substrCount(String s) {
        s.split("");
    }*/
    
    //abce
    //dec
    
 // Complete the makeAnagram function below.
    static int makeAnagram(String string1, String string2) {

        int count = 0;
//        Set<Character> c1Set =  string1.toCharArray();
        for(char c: string1.toCharArray()) {
            
        }
        
        return count;
    }
    
 // Complete the isValid function below.
    static String isValid(String input) {
        String result = "";
        
        if(Arrays.stream(input.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).values().stream().distinct().count() > 1) {
            result = "NO";
        } else {
            result = "YES";
        }
        
        System.out.println(result);
        return result;
    }
    
    static int alternatingCharacters(String s) {

        String currentChar = null;
        int count = 0;
        List<String> characterList = new CopyOnWriteArrayList<>(s.split(""));
        for(String temp : characterList) {
            if(currentChar == null) {
                currentChar = temp;
            } else if(currentChar.equals(temp)) {
                count++;
                characterList.remove(temp);
            } else {
                currentChar = temp;
            }
        }
        System.out.println(count);
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt"));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

//            int result = alternatingCharacters(s);
//            String result = isValid(s);
//            long result = substrCount(s);

//            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
