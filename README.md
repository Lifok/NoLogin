![NoLogin Logo](http://image.noelshack.com/fichiers/2015/44/1446068416-nologin.png)

This Library is an extension of TheShark's OpenAuth Library. 
It allow to retrieve minecraft Accounts logged and keep the session valid.

It is really simple use, first you need to retrieve accounts, then validate them.

```java
List<Account> accounts = NL1.getAccountManager().getAccounts();
for(Account acc : accounts) 
	{
		if(NL1.getValidator().validate(acc))
		{
		yourAccountList.add(acc);
  		}
	}
```

Accounts contains all the variables you need for a proper launcher use.

###Bonus : Get the Player head for each Account

```java
	private Image[] playerHeads;

	private void loadPlayersHeads() 
	{
		playerHeads = new Image[frame.getAccounts().size()];
		for(int i = 0; i < frame.getAccounts().size(); i++) {
			try 
			{
				URL url = new URL("http://skins.minecraft.net/MinecraftSkins/" + frame.getAccounts().get(i).getDisplayName() + ".png");
				playerHeads[i] = ImageIO.read(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
```

