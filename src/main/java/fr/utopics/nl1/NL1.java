package fr.utopics.nl1;

import fr.utopics.nl1.account.AccountManager;
import fr.utopics.nl1.auth.Validator;

public class NL1 
{
	public static AccountManager getAccountManager() 
	{
		return AccountManager.getInstance();
	}
	
	public static Validator getValidator() {
		return Validator.getInstance();
	}
}
