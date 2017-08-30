import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;


public class SpellingBee {

	private static final String DICTIONARY_FILE = "dictionary.txt";
	private static final String ALPHABET_FILE = "alphabet.txt";
	private static final int MINIMUM_WORD_LENGTH = 5;
	private static final int NUMBER_OF_CHARACTERS = 7;
	private static final int MINIMUM_ANSWER_WORDS = 15;
	private static final int MAXIMUM_ANSWER_WORDS = 27;
	
	private static ArrayList<String> dictionary;
	private static ArrayList<Character> alphabet;
	
	private static void readWords() {
		dictionary = new ArrayList<String>();
		
		try {
		BufferedReader br = new BufferedReader(new FileReader(DICTIONARY_FILE));
		String line;
		while((line = br.readLine()) != null) {
			if (line.length() >= MINIMUM_WORD_LENGTH && !dictionary.contains(line))
				dictionary.add(line);
		}
		br.close();
		br = new BufferedReader(new FileReader(ALPHABET_FILE));
		while((line = br.readLine()) != null) {
				alphabet.add(line.charAt(0));
		}
		br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	public static void main(String[] args) {
		readWords();
		ArrayList<ArrayList<Character>> threePointCharacterLists = new ArrayList<ArrayList<Character>>();
		ArrayList<String> threePointWords = new ArrayList<String>();
		
		for (int i = 0 ; i < dictionary.size(); i++) {
			String word = dictionary.get(i);
			ArrayList<Character> uniqueCharacters = new ArrayList<Character>();
			for (int l = 0; l < word.length(); l++) {
				Character c = word.charAt(l);
				if (!uniqueCharacters.contains(c)) {
					uniqueCharacters.add(c);
				}
			}
			if (uniqueCharacters.size() == NUMBER_OF_CHARACTERS) {
				threePointCharacterLists.add(uniqueCharacters);
				threePointWords.add(word);
			}
		}
		
		Random rand = new Random();
		boolean finished = false;
		ArrayList<Character> characterList = new ArrayList<Character>();
		ArrayList<String> answerList = new ArrayList<String>();
		Character middle = new Character('A');
		while(!finished)
		{
			int characterListIndex = Math.abs(rand.nextInt()) % threePointCharacterLists.size();
			characterList = threePointCharacterLists.get(characterListIndex);
			
			for (int u = 0; u < characterList.size(); u++) {
				middle = characterList.get(u);
				
				answerList = new ArrayList<String>();
				
				for (int i = 0 ; i < dictionary.size(); i++) {
					String word = dictionary.get(i);
					boolean wordIsValid = true;
					for (int l = 0; l < word.length(); l++) {
						if (word.indexOf(middle) == -1)
							wordIsValid = false;
						if (!characterList.contains(word.charAt(l))) {
							wordIsValid = false;
						}
					}
					if (wordIsValid) {
						answerList.add(word);
					}
				}
			}
			
			 
			if (answerList.size() > MINIMUM_ANSWER_WORDS && answerList.size() < MAXIMUM_ANSWER_WORDS && middle != 'Y')
				break;
		}
		
		ArrayList<String> threePointAnswers = new ArrayList<String>();
		int threePointWordCount = 0;
		for (int i = 0; i < answerList.size(); i++)  {
			String word = answerList.get(i);
			if (threePointWords.contains(word))
				threePointAnswers.add(word);
		}
		
		System.out.println("Total Words: " + answerList.size());
		System.out.println("Three Point Words: " + threePointAnswers.size());
		
		for (int i = 0; i < threePointAnswers.size(); i++)  {
			String word = threePointAnswers.get(i);
			System.out.println(word);
			answerList.remove(word);
		}
		
		System.out.println("Non-three-point-words:");		
		
		for (int i = 0; i < answerList.size(); i++)  {
			System.out.println(answerList.get(i));
		}
		
		for (int i = 0; i < 20; i++)  {
			System.out.println();
		}
		
		System.out.print(middle + "+");
		characterList.remove(middle);
		int i = 0;
		while (characterList.size() > 0)  {
			int j = Math.abs(rand.nextInt()) % characterList.size();
			System.out.print(characterList.get(j));
			characterList.remove(j);
		}
		System.out.println();
		System.out.println("Total Words: " + answerList.size());
		System.out.println("Three Point Words: " + threePointAnswers.size());
		
		
	}

}
