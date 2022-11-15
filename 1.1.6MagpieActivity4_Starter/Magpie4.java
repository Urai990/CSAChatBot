/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 * 		Uses advanced search for keywords 
 *</li><li>
 * 		Will transform statements as well as react to keywords
 *</li></ul>
 * @author Uttara Rai, Maya Poghosyan
 * @version Novemeber 2022
 *
 */
public class Magpie4
{
  public enum State {
    None, Random, HiHello, Howareyou, Talk, Ok, Thank, Morning, Night, No, Family, Love, Breakup, Anxiety, Excited, HimHer, Test, Event, Meeting, Thanks, Fail, Insecure, YouMe, Welcome, DoNotKnow, Want
  }
  
  private State state = State.None;

  private State parseInput(String statement)
  {
		if (statement.length() == 0) {
      return State.None;
    }
    
    if (findKeyword(statement, "Hello") >= 0 
        || findKeyword(statement, "Hi") >= 0){
          return State.HiHello;
    }
    if ( findKeyword(statement, "Good morning") >= 0) {
      return State.Morning;
    }
    if (findKeyword(statement, "Good night") >= 0) {
      return State.Night;
    }
      
    if (findKeyword(statement, "girlfreind") >= 0
      || findKeyword(statement, "boyfreind") >= 0) {
        return State.Love;
     }

    if (findKeyword(statement, "how are you") >= 0) {
      return State.Howareyou;
    }
    
    else if (findKeyword(statement, "okay") >= 0
            || findKeyword(statement, "ok") >= 0)
    {
      return State.Ok;
    }
    else if (findKeyword(statement, "thank you") >= 0
      || findKeyword(statement, "thanks") >= 0
      || findKeyword(statement, "thx") >= 0){
      return State.Thank;
    }
    else if (findKeyword(statement, "talk") >= 0
      || findKeyword(statement, "something") >= 0){
      return State.Talk;
      }
    else if (findKeyword(statement, "yourWelcome") >= 0){
      return State.Welcome;
    }
    else if (findKeyword(statement, "no") >= 0){
      return State.No;
	}
    else if (findKeyword(statement, "your welcome") >= 0){
      return State.Welcome;
	}
    else if (findKeyword(statement, "mother") >= 0
				|| findKeyword(statement, "father") >= 0
				|| findKeyword(statement, "sister") >= 0
				|| findKeyword(statement, "brother") >= 0)
		{
			return State.Family;
		}
    else if (findKeyword(statement,"breakup")>=0
      || findKeyword(statement,"broke up")>= 0
      || findKeyword(statement,"split")>=0)
    {
      return State.Breakup;
    }
    else if (findKeyword(statement,"anxiety") >= 0
        || findKeyword(statement,"worried") >= 0
        || findKeyword(statement,"anxious") >= 0
        || findKeyword(statement,"nervous") >= 0)
    {
      return State.Anxiety;
    }
    else if (findKeyword(statement,"excited") >= 0
        || findKeyword(statement,"happy") >= 0
        || findKeyword(statement,"enjoy") >= 0
        || findKeyword(statement,"great") >= 0)
    {
      return State.Excited;
    }
    else if (findKeyword(statement,"it's her") >= 0
        || findKeyword(statement,"was her fault") >= 0
        || findKeyword(statement,"her fault") >= 0
        || findKeyword(statement,"not me") >= 0
        || findKeyword(statement,"his fault") >= 0
        || findKeyword(statement,"it's him") >= 0){
      return State.HimHer;
    }
     else if (findKeyword(statement,"test") >= 0
        || findKeyword(statement,"assignment") >= 0)
    {
      return State.Test;
    }
     else if (findKeyword(statement,"event") >= 0
        || findKeyword(statement,"wedding") >= 0
        || findKeyword(statement,"prepare") >= 0
        || findKeyword(statement,"trip") >= 0
        || findKeyword(statement,"pack") >= 0)
    {
      return State.Event;
    }
    else if (findKeyword(statement,"meeting") >= 0
        || findKeyword(statement,"interview") >= 0
        || findKeyword(statement,"coordinate") >= 0
        || findKeyword(statement,"presentation") >= 0)
    {
      return State.Meeting;
    }
    else if (findKeyword(statement, "I don't know") >= 0
            || findKeyword(statement, "I do not know") >= 0)
    {
      return State.DoNotKnow;
    }
    else if (findKeyword(statement, "fail") >= 0
            || findKeyword(statement, "failing") >= 0
            || findKeyword(statement, "failed") >= 0)
    {
      return State.Fail;
    }
    else if (findKeyword(statement, "insecure") >= 0)
    {
      return State.Insecure;
    }
    else if (findKeyword(statement, "I want to", 0) >= 0)
		{
	    return State.Want;
		}
    else if (findKeyword(statement, "You", 0) >= 0
             || findKeyword(statement, "Me", 0) >= 0)
		{
	    return State.YouMe;
		}
    else {
    return State.Random;
    }
  }
  
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		state = State.None;
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
    State newState = parseInput(statement);

    switch (newState) 
    {
      case None:
        response = "Say something, please.";
        break;
      case Morning:
        response = "Good morning! How is your day going?";
        break;
      case Night:
        response = "Good night! How was your day?";
        break;
      case Howareyou:
        response = "I am good. Now lets talk about you.";
        break;
      case HiHello:
        response = "Hello, how are you?";
        break;
      case Ok:
        response = "Great!";
        break;
      case Thank:
        response = "You are very welcome!";
        break;
      case Talk:
        response = "Lets talk about you. How are you feeling today?";
        break;
      case Welcome:
        response = "Thank you!";
        break;
      case No:
        response = "Why so negative?";
        break;
      case Family:
        response = "Tell me more about your family.";
        break;
      case Breakup:
         response = "Introspection is important. Was it you or them? How do you feel like the relationship went wrong?";
        break;
      case Anxiety:
        response = "Let's unpack and reset.. What's worrying you?";
        break;
      case Excited:
        response = "That's awesome! I'm very happy for you! Lets talk more about that.";
       break;
      case HimHer:
        response = "I'm sorry that happened to you. There are plenty of fish in the sea.";
        break;
      case Test:
        response = "Things proven to help with memorization: chewing gum, color-coding, and listening to classical music. Start working today and use active recall.";
        break;
      case Event:
        response = "Delineate your tasks! Put together a to-do list and ask people for help in things you'll need to bring and put together.";
        break;
      case Meeting:
        response = "Practice in front of a mirror, make sure you're not anxious, and look your best Let the best of your character shine through.";
        break;
      case DoNotKnow:
        response = "That's okay, lets talk about something else then. Is there something else that has been worrying you?";
        break;
      case Fail:
        response = "It's okay, you cannot succeed at something without first failing. Keep trying, you will eventually succeed.";
        break;
      case Insecure:
        response = "Everyone has flaws. But everyone has strengths too. Recognize both of these in you. ";
      // Responses which require transformations
      case Want:
        response = transformIWantToStatement(statement);
        break;
      case YouMe:
        // Look for a two word (you <something> me)
		  	// pattern
			  int psn = findKeyword(statement, "you", 0);
  
	  		if (psn >= 0
		  			&& findKeyword(statement, "me", psn) >= 0)
			  {
				  response = transformYouMeStatement(statement);
			  }
      //Random Response
      default:
          response = getRandomResponse(statement);
        break;
    }
    state = newState;
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
  * Takes a sentence such as "I like space" to "What do you mean by space"
*/
  	private String transformForClarification(String statement)
	{
    String restOfStatement = "";
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
    if (findKeyword(statement, "I like") >= 0){
      int psn = findKeyword (statement, "I like", 0);
		  restOfStatement = statement.substring(psn + 6).trim();
      //System.out.println("ONE: " + restOfStatement);
    }
    else if (findKeyword(statement, "I don't like") >= 0){
      int psn = findKeyword (statement, "I don't like", 0);
		  restOfStatement = statement.substring(psn + 12).trim();
    }
    else if (findKeyword(statement, "How do you") >= 0)
    {
      int psn = findKeyword (statement, "How do you", 0);
		  restOfStatement = statement.substring(psn + 10).trim();
    }
		return "What do you mean by " + restOfStatement + "?";
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
	private String getRandomResponse(String statement)
	{
		final int NUMBER_OF_RESPONSES = 7;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (findKeyword(statement, "I like") >= 0 || findKeyword(statement, "I don't like") >= 0 || findKeyword(statement, "How do you") >= 0){
      response = transformForClarification(statement);
    }
    else if (whichResponse == 0)
		{
			response = "Interesting, tell me more.";
		}
		else if (whichResponse == 1)
		{
			response = "Hmmm.";
		}
		else if (whichResponse == 2)
		{
			response = "Do you really think so? Why do you feel that way?";
		}
		else if (whichResponse == 3)
		{
			response = "You don't say.";
		}
    else if (whichResponse == 4)
		{
			response = "What do you mean by that?";
		}
    else if (whichResponse == 5)
		{
			response = "How are you feeling";
		}
    else if (whichResponse == 6)
		{
			response = "How is your day going?";
		}
		return response;
	}
}
          




