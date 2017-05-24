/*package org.madbit.soap;

import java.util.Set;

import javax.mail.MessageContext;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.ws.security.WSConstants;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.WSSecUsernameToken;

import com.sun.xml.txw2.Document;

public final class SecurityHandler implements SOAPHandler<SOAPMessageContext> {


@Override
public boolean handleMessage(SOAPMessageContext messageContext) {
    boolean outInd = (Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
    if (outInd) {
        try {
            WSSecUsernameToken builder = new WSSecUsernameToken();
            builder.setPasswordType(WSConstants.PASSWORD_TEXT);
            builder.setUserInfo(_username, _password);
            builder.addNonce();
            builder.addCreated();

            Document doc = messageContext.getMessage().getSOAPPart().getEnvelope().getOwnerDocument();
            WSSecHeader secHeader = new WSSecHeader();
            secHeader.insertSecurityHeader(doc);
            builder.build(doc, secHeader);
        } catch (Exception e) {
            LOGGER.error("Unable to handle SOAP message", e);
            return false;
        }
    }
    return true;
}

public void close(javax.xml.ws.handler.MessageContext arg0) {
	// TODO Auto-generated method stub
	
}

public boolean handleFault(SOAPMessageContext arg0) {
	// TODO Auto-generated method stub
	return false;
}

public Set<QName> getHeaders() {
	// TODO Auto-generated method stub
	return null;
}


}*/