/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 * 		Uses advanced search for keywords 
 *</li><li>
 * 		Will transform statements as well as react to keywords
 *</li></ul>
 * @author Laurie White
 * @version April 2012
 *
 */
public class Magpie4
{
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hello, let's talk.";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";
		if (statement.length() == 0)
		{
			response = "Say something, please.";
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why so negative?";
		}
		else if (findKeyword(statement, "mother") >= 0
				|| findKeyword(statement, "father") >= 0
				|| findKeyword(statement, "sister") >= 0
				|| findKeyword(statement, "brother") >= 0)
		{
			response = "Tell me more about your family.";
		}
    else if (findKeyword(statement,"boyfriend") >= 0
        || findKeyword(statement,"girlfriend") >= 0)
    {
      response = "Tell me about your love life.";
    }
    else if (findKeyword(statement,"breakup")>=0
      || findKeyword(statement,"broke up")>= 0
      || findKeyword(statement,"split")>=0) {
        response = "Introspection is important. Was it you or them? How do you feel like the relationship went wrong?";
      }
    else if (findKeyword(statement,"anxiety") >= 0
        || findKeyword(statement,"worried") >= 0
        || findKeyword(statement,"anxious") >= 0
        || findKeyword(statement,"nervous") >= 0) {
      response = "Let's unpack and reset.. What's worrying you?";
    }
    else if (findKeyword(statement,"it's her") >= 0
        || findKeyword(statement,"was her fault") >= 0
        || findKeyword(statement,"her fault") >= 0
        || findKeyword(statement,"not me") >= 0
        || findKeyword(statement,"his fault") >= 0
        || findKeyword(statement,"it's him") >= 0){
      response = "I'm sorry that happened to you. There are plenty of fish in the sea.";
    }
    else if (findKeyword(statement,"test") >= 0
        || findKeyword(statement,"assignment") >= 0)

    {
      response = "Things proven to help with memorization: chewing gum, color-coding, and listening to classical music. Start working today and use active recall.";
    }
    else if (findKeyword(statement,"event") >= 0
        || findKeyword(statement,"wedding") >= 0
        || findKeyword(statement,"prepare") >= 0
        || findKeyword(statement,"trip") >= 0
        || findKeyword(statement,"pack") >= 0) {
      response = "Delineate your tasks! Put together a to-do list and ask people for help in things you'll need to bring and put together.";
    }

		// Responses which require transformations
    else if (findKeyword(statement,"meeting") >= 0
        || findKeyword(statement,"interview") >= 0
        || findKeyword(statement,"coordinate") >= 0
        || findKeyword(statement,"presentation") >= 0){
      response = "Practice in front of a mirror, make sure you're not anxious, and look your best Let the best of your character shine through.";
        }
    else if (findKeyword(statement,"thanks") >= 0
        || findKeyword(statement,"thank you") >= 0
        || findKeyword(statement, "thx") >= 0) {
      response = "You're very welcome.";
        }
    else if (findKeyword(statement, "I don't know") >= 0
            || findKeyword(statement, "I do not know") >= 0)
    {
      response = "That's okay, lets talk about something else then. Is there something else that has been worrying you?";
    }
    else if (findKeyword(statement, "Hello") >= 0
            || findKeyword(statement, "Hi") >= 0)
    {
      response = "How are you feeling?";
    }
    else if (findKeyword(statement, "fail") >= 0
            || findKeyword(statement, "failing") >= 0
            || findKeyword(statement, "failed") >= 0)
    {
      response = "It's okay, you cannot succeed at something without first failing. Keep trying, you will eventually succeed.";
    }
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else
		{
			// Look for a two word (you <something> me)
			// pattern
			int psn = findKeyword(statement, "you", 0);

			if (psn >= 0
					&& findKeyword(statement, "me", psn) >= 0)
			{
				response = transformYouMeStatement(statement);
			}
			else
			{
				response = getRandomResponse();
			}
		}
		return response;
	}
	
	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "What would it mean to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}

	
	
	/**
	 * Take a statement with "you <something> me" and transform it into 
	 * "What makes you think that I <something> you?"
	 * @param statement the user statement, assumed to contain "you" followed by "me"
	 * @return the transformed statement
	 */
	private String transformYouMeStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfYou = findKeyword (statement, "you", 0);
		int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
		
		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}
	
	

	
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @param startPos the character of the string to begin the search at
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal, int startPos)
	{
		String phrase = statement.trim();
		//  The only change to incorporate the startPos is in the line below
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
		
		//  Refinement--make sure the goal isn't part of a word 
		while (psn >= 0) 
		{
			//  Find the string of length 1 before and after the word
			String before = " ", after = " "; 
			if (psn > 0)
			{
				before = phrase.substring (psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}
			
			//  If before and after aren't letters, we've found the word
			if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
					&& ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
			{
				return psn;
			}
			
			//  The last position didn't work, so let's find the next, if there is one.
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
			
		}
		
		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		final int NUMBER_OF_RESPONSES = 5;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (whichResponse == 0)
		{
			response = "Interesting, tell me more.";
		}
		else if (whichResponse == 1)
		{
			response = "Hmmm.";
		}
		else if (whichResponse == 2)
		{
			response = "Do you really think so?";
		}
		else if (whichResponse == 3)
		{
			response = "You don't say.";
		}
    else if (whichResponse == 4)
		{
			response = "What do you mean by that?";
		}
		return response;
	}

}



