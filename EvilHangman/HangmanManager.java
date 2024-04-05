/*
@Author: Ornela Amouzou-Adoun
*/

import java.util.*;
public class HangmanManager
{
/**
* String currentPattern keeps track of the current pattern
*/
	private String currentPattern;
/**
* int guessCount keeps track of how many guesses the user has left
*/
	private int guessCount;
/**
* int wordLength is what the user selected as the length of the word
*/
	private int wordLength;
/**
* Set<String> currentWords keeps track of the current possibilities for words
*/
	private Set<String> currentWords;
/**
* SortedSet<Character> guessedLetters keeps track of the guessed letters
*/
	private SortedSet<Character> guessedLetters;
/**
* constructor
* @param List<String> dictionary is the dictionary of possible words
* @param int length is what the user selected as the length of the word
* @param int max is the maximum number of guesses the user has
*/
	public HangmanManager(List<String> dictionary, int length, int max)
	{
		if(length < 1 || max < 0)//if the input is not valid, throw an exception
		{
			throw new IllegalArgumentException("");
		}
		currentPattern = "-";//starts the pattern with a dash
		wordLength = length;
		guessCount = max;
		currentWords = new TreeSet<String>();//creates a Set to store the current words
		guessedLetters = new TreeSet<Character>();
		for(String word : dictionary)
		{
			if(word.length() == wordLength)
			{
				currentWords.add(word);//only adds words of the selected length
			}
		}
		for(int ii = 1; ii < length; ii++)
		{
			currentPattern += " -";//adds dashes to the pattern
		}
	}
/**
* Set<String> words returns what words are currently possible
* @return Set<String> currentWords is the set of possible words
*/
 	public Set<String> words()
 	{
		return currentWords;
 	}
/**
* int guessesLeft returns how many guesses the user has left
* @return int guessCount is how many guesses the user has left
*/
 	public int guessesLeft()
 	{
		return guessCount;
	}
/** 
* guesses returns the set of characters which has been guessed
* @return SortedSet<Character> guessedLetters is the set of letters which has been guessed
*/
 	public SortedSet<Character> guesses()
 	{
		return guessedLetters;
	}
/**
* pattern returns the current pattern, throws an exception if there are no words left
* @return String currentPattern is the current pattern
*/
 	public String pattern()
	{
		if(currentWords.isEmpty())
		{
			throw new IllegalStateException("No words possible.");//if there are no more words to choose from
		}
		return currentPattern;
	}
/**
* record records all of the user's guesses, and determines what set of words to choose from
* @param char guess is the user's current guess
* @return int occurences is how many words the user's guess is in
*/
 	public int record(char guess)
	{
		SortedMap<String, Integer> patternOccurences = new TreeMap<String, Integer>();
		SortedMap<String, SortedSet<String>> patternWords = new TreeMap<String, SortedSet<String>>();//creates a map to search the words
		int occurences = 0;
		if(guessCount < 1 || currentWords.isEmpty())
		{
			throw new IllegalStateException("");//if there are no words left
		}
		guessedLetters.add(guess);
		//checks the occurence of the "guess" character
		SortedSet<String> words = new TreeSet<String>();
		String newPattern = "";
		for(String word : currentWords)//goes through all the words in the current set
		{
			newPattern = currentPattern;
			occurences = 0;
			for(int jj = 0; jj < wordLength; jj++)//goes through each word
			{
				char[] charWord = word.toCharArray();
				if(charWord[jj] == guess)
				{
					occurences++;
					char[] charPattern = newPattern.toCharArray();
					charPattern[jj*2] = guess;//changes the pattern
					newPattern = "";
					for(int kk = 0; kk < charPattern.length; kk++)
					{
						newPattern += charPattern[kk];//rewrites the pattern
					}
				}
			}
			 if(patternWords.containsKey(newPattern))
 			{
 				words = patternWords.get(newPattern);
 				words.add(word);
 			}
 			else//if key is not found
 			{
 				//adding new pattern
 				SortedSet<String> tWords = new TreeSet<String>();
 				tWords.add(word);
 				words = tWords;
 				patternWords.put(newPattern, words);
 				patternOccurences.put(newPattern, occurences);
 			}
		}
		SortedSet<String> largestWords = new TreeSet<String>();
		String largestPattern = "";
		int largest = 0;
		occurences = 0;
		for(String pattern : patternWords.keySet())
		{
			words = patternWords.get(pattern);
			if(words.size() > largest)//if the size of the set is larger than the other group
			{
				largest = words.size();
				largestPattern = pattern;
				largestWords = words;
				occurences = patternOccurences.get(pattern);
			}
			else if(words.size() == largest)
			{
				if(patternOccurences.get(pattern) < occurences)
				{
					largestWords = words;
					largestPattern = pattern;
					occurences = patternOccurences.get(pattern);
				}
			}
		}
		if(occurences == 0)//if there are no occurences, the user will have one less guess
		{
			guessCount--;
		}
		currentWords = largestWords;
		currentPattern = largestPattern;
		return occurences;
	}
}