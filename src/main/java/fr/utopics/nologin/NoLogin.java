package fr.utopics.nologin;

import fr.utopics.nologin.account.AccountManager;
import fr.utopics.nologin.auth.Validator;

public class NoLogin 
{
	private AccountManager accountManager;
	private Validator validator;

	public NoLogin() {
		accountManager = new AccountManager();
		validator = new Validator();
	}
	
	public AccountManager getAccountManager() 
	{
		return accountManager;
	}
	
	public Validator getValidator() 
	{
		return validator;
	}
}
