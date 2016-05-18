![NoLogin Logo](http://image.noelshack.com/fichiers/2015/44/1446068416-nologin.png)

This Library is an extension of TheShark34's OpenAuth Library. 
It allow to retrieve minecraft Accounts logged and keep the session valid.

It is really simple use, first you to init the NoLogin, it will return a UserNotLoggedException if the user is not connected on the official launcher.

```java
NoLogin noLogin = new NoLogin();
try {
	noLogin.init();
} catch (UserNotLoggedException) {
	//doStuff, but do not ask for credentials, it is not allowed by mojang
}
```
Then, you need to validate the accounts, depend on if you want multiple accounts gestion or single account gestion, here is the way :

```java
List<Account> accounts = noLogin.getAccountManager().getAccounts();
for(Account acc : accounts) 
	{
		if(noLogin.getValidator().validateAccount(acc))
		{
		yourAccountList.add(acc);
  		}
	}
```

Accounts contains all the variables you need for a proper launcher use.

###Bonus : Get the Player head for each Account and draw them.

You can directly access minecraft skins, but there is an easier way.
Get a look here -> https://minotar.net/
