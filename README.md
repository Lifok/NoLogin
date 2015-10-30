![NoLogin Logo](http://image.noelshack.com/fichiers/2015/44/1446068416-nologin.png)

This Library is an extension of TheShark34's OpenAuth Library. 
It allow to retrieve minecraft Accounts logged and keep the session valid.

It is really simple use, first you need to retrieve accounts, then validate them.

```java
NoLogin noLogin = new NoLogin();
List<Account> accounts = noLogin.getAccountManager().getAccounts();
for(Account acc : accounts) 
	{
		if(noLogin.getValidator().validate(acc))
		{
		yourAccountList.add(acc);
  		}
	}
```

Accounts contains all the variables you need for a proper launcher use.

###Bonus : Get the Player skin for each Account and draw Heads.

First, you need to retrieve player skins.

```java
	private Image[] playerSkins;

	private void loadPlayersSkins() 
	{
		playerSkins = new Image[frame.getAccounts().size()];
		for(int i = 0; i < frame.getAccounts().size(); i++) {
			try 
			{
				URL url = new URL("http://skins.minecraft.net/MinecraftSkins/" + frame.getAccounts().get(i).getDisplayName() + ".png");
				playerSkins[i] = ImageIO.read(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
```

Then, you draw them (using Graphics for exemple) :

```java
		for(int i =0; i < playerSkins.length; i++) {
			g.drawImage(playerSkins[i], x + i * size, y, (x + size) + i * size, y + size, 8, 8, 16, 16, this);
		}
```

The head's quad on the skin is [8, 8, 16, 16]
