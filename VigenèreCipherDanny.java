
import java.util.*; //import
import java.io.*;

/** This program takes a file and a keyword then either encrypts or decrypts the file
 * and returns the content encrypted or decrypted file in a new file to the user. 
 * First the program ask if the user wants to encrypt or decrypt. Then user enters
 * a file name at which the program will encrypt or decrypt. Then the user enters a key
 * word that will change how the program encrypts or decrypts the file. The program
 * goes through the file line by line then shifts each character to encrypt or 
 * decrypt each character which will add up and encrypt or decrypt the file.
 * 
 * @author dannyliu
 */
public class VigenèreCipherDanny {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);


		System.out.print("Would you like to encrypt or decrypt? (1 = encrypt, 2 = decrypt): ");
		int direct = in.nextInt();

		//Checks if the user wants to encrypt or decrypt, then provides a specified prompt
		if (direct == 1) {
			System.out.print("Please enter the file name to be encoded: ");
		}
		else {
			System.out.print("Please enter the file name to be decoded: ");
		}

		//File import code
		String filename = in.next();
		//Ask for file name, and save in a variable, filename
		Scanner fileIn = new Scanner(new File(filename));
		FileWriter out = new FileWriter ("outputFileDanny.txt");
		BufferedWriter bw = new BufferedWriter(out);
		PrintWriter outputFile = new PrintWriter(bw);

		System.out.print("Please enter a keyword: ");
		String keyword = in.next(); //frank

		//Reads a file
		String line;
		//loops through whole file, line by line
		while (fileIn.hasNextLine()){ 
			//reads a line and saves in variable line
			line = fileIn.nextLine(); 
			//call cipher
			String output = cipher(line, direct, getKeyArray(keyword));
			//out.write(output + "\n");
			outputFile.println(output);	
		}
		outputFile.close();

		System.out.println("Your encrypted/decrpted file is outputFileDanny.txt");
	}


	/** Calls on the shiftRight or shiftLeft to either encrypt or 
	 * decrypt a word. 1 for encrypt 2 for decrypt
	 * @return The encrypted or decrypted word
	 */
	public static String cipher(String word, int direction, int[] keynums) {
		String newWord = "";

		//If the user wants to encrypt a word
		if (direction == 1) {
			//Loops so each letter of the word is encrypted
			for (int i = 0; i < word.length(); i++) {
				//Adds on the encrypted letter to the overall encrypted phrase
				newWord += shiftRight(word.charAt(i), keynums[i % keynums.length]);
			}
		}
		//If the user wants to decrypt a word
		else {
			//Loops so each letter of the word is decrypted
			for (int j = 0; j < word.length(); j++) {
				//Adds on the decrypted letter to the overall decrpted phrase
				newWord += shiftLeft(word.charAt(j), keynums[j % keynums.length]);
			}
		}
		return newWord;
	}


	/** This method finds how far away each character in the key word is from "a"
	 *For example the letter "d" is 3 away from "a" so the value added would be 3
	 * @return An array with the how far away the corresponding letter is from "a"
	 */
	public static int[] getKeyArray(String keyword){
		int[] key = new int [keyword.length()];

		//Find then add how far the character is from "a" to an array
		for (int k = 0; k < keyword.length(); k++) {
			key[k] = keyword.charAt(k) - 'a';
		}
		return key;
	}


	/** Encrypts a letter by shifting it "right" by a specified amount of characters
	 * @return Returns the encrypted word
	 */
	public static char shiftRight(char letter, int shift){
		int en = letter;
		//Add "shift" to the value so the "letter" shifts a certain amount to the right
		en = en + shift;	

		//if the letter's value is larger than 126 subtract 95 so the shift loops around the ASCII table
		if (en > 126) {
			//Subtracts 26 so the shift loops around the alphabet
			en = en - 95;
		}
		return (char) en;
	}


	/** Decrypts a letter by shifting it "left" a specified amount of characters
	 * @return Returns the decrypted word
	 */
	public static char shiftLeft(char letter, int shift){
		int de = letter;
		//Subtract "shift" to the value so the "letter" shifts a certain amount to the left
		de = de - shift;	

		//if the letter's value is smaller than 32 add 95 so the shift loops back around the ASCII table
		if (de < 32) {
			//Adds 26 so the shift loops back around the alphabet
			de = de + 95;
		}
		return (char) de;
	}

}
