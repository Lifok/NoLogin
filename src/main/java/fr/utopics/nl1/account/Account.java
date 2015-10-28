package fr.utopics.nl1.account;

public class Account
{
    private String uuid, displayName, accessToken, userId, username;
    
    public Account(String uuid, String displayName, String accessToken, String userId, String username)
    {
    	this.uuid = uuid;
    	this.displayName = displayName;
    	this.accessToken = accessToken;
    	this.userId = userId;
    	this.username = username;
    }
    
    public String getUUID()
    {
    	return uuid;
    }
    
    public String getDisplayName() 
    {
    	return displayName;
    }
    
    public String getAccessToken() 
    {
    	return accessToken;
    }
    
    public String getUserId()
    {
    	return userId;
    }
    
    public String getUsername()
    {
    	return username;
    }

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}