package fr.utopics.nologin.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

import fr.theshark34.openauth.AuthPoints;
import fr.theshark34.openauth.AuthenticationException;
import fr.theshark34.openauth.Authenticator;
import fr.theshark34.openauth.model.response.RefreshResponse;
import fr.utopics.nologin.account.Account;
import fr.utopics.nologin.util.Utilities;

public class Validator {

	private static String clientToken;
	private static Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);

	public Validator() 
	{
		super();
	}

	/**
	 * Call this method to validate the AccessToken of an account, it will automatically try to refresh if validation fail.
	 * @param acc
	 * @return true is the validation or the refresh success. 
	 */
	public boolean validateAccount(Account acc) 
	{
		try 
		{
			authenticator.validate(acc.getAccessToken());
			return true;
		} 
		catch (AuthenticationException e) 
		{
			e.printStackTrace();
			return refreshToken(acc);
		}
	}

	private boolean refreshToken(Account acc)
	{
		try 
		{
			RefreshResponse response = authenticator.refresh(acc.getAccessToken(), getClientToken());
			acc.setAccessToken(response.getAccessToken());
			updateMcFile(acc, response);
			return true;
		}
		catch (AuthenticationException e) 
		{
			System.out.println(e.getErrorModel().getErrorMessage());
			return false;
		}
	}

	/**
	 * Used to rewrite the launcher_profiles file
	 * @return
	 */
	private void updateMcFile(Account acc, RefreshResponse response)
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
			JSONObject profileObj = profilesObj.getJSONObject("authenticationDatabase").getJSONObject(response.getSelectedProfile().getId());
			profileObj.remove("accessToken");
			profileObj.put("accessToken", response.getAccessToken());
			FileWriter writer = new FileWriter(profiles);
			writer.write(profilesObj.toString(4));
			writer.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Used to retrieve the ClientToken from the profiles file
	 * @return
	 */
	public String getClientToken() 
	{
		if(clientToken == null) 
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
				clientToken = profilesObj.getString("clientToken");
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return clientToken;
	}
}
