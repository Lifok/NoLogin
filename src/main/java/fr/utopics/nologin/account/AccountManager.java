package fr.utopics.nologin.account;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import fr.utopics.nologin.util.Utilities;

public class AccountManager 
{
	private List<Account> accounts;

	public AccountManager()
	{
		super();
		accounts = new ArrayList<Account>();
		retrieveAccounts();
	}

	private void retrieveAccounts() 
	{
		File profiles = new File(Utilities.getMinecraftDirectory(), "launcher_profiles.json");
		try 
		{
			FileInputStream fis = new FileInputStream(profiles);
			byte[] data = new byte[(int) fis.available()];
			fis.read(data);
			fis.close();
			String jsonProfiles = new String(data, "UTF-8");
			JSONObject profilesObj = new JSONObject(jsonProfiles);
			JSONObject authDb = profilesObj.getJSONObject("authenticationDatabase");
			for(String accountName : authDb.keySet())
			{
				JSONObject obj = authDb.getJSONObject(accountName);
				accounts.add(new Account(obj.getString("uuid"), obj.getString("displayName"), obj.getString("accessToken"), obj.getString("userid"), obj.getString("username")));
			}
		} 
		catch (Exception e)
		{ 
			e.printStackTrace(); 
		}
	}
	
	public List<Account> getAccounts() 
	{
		return accounts;
	}

}
