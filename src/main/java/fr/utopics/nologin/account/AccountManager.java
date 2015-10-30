/*
 * Copyright 2015 Lifok
 *
 * This file is part of NoLogin.

 * NoLogin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NoLogin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with NoLogin.  If not, see <http://www.gnu.org/licenses/>.
 */
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
