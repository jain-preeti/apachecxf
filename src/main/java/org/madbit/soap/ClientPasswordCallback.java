package org.madbit.soap;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class ClientPasswordCallback implements CallbackHandler {
	private static final String BUNDLE_LOCATION = "auth2";
    private static final String PASSWORD_PROPERTY_NAME = "auth.manager.password";
   private static  String password="abc";
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		// TODO Auto-generated method stub
		WSPasswordCallback pc=	(WSPasswordCallback)callbacks[0];
		pc.setPassword("abc");
	}
	
	

}
