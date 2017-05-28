package org.madbit.soap;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class ServerPasswordCallback implements CallbackHandler {
	private static final String BUNDLE_LOCATION = "auth";
	private static final String PASSWORD_PROPERTY_NAME = "auth.manager.password";

    private static String password;
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
WSPasswordCallback pc=	(WSPasswordCallback)callbacks[0];;
//pc.getIdentifer(); for 2.2.3
if(pc.getIdentifier().equals("preeti"))
{
	System.out.println(pc.getPassword());
	pc.setPassword("abc");
/*if(!pc.getPassword().equals("123"))
{
	throw new IOException("wrong password");
}*/
}
	}

	
}