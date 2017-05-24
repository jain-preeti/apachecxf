/*package org.madbit.soap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header;
import org.apache.cxf.headers.Header.Direction;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.WSSecUsernameToken;
import org.w3c.dom.Document;

public class SoapHandler extends AbstractPhaseInterceptor<Message> implements SOAPHandler<SOAPMessageContext> {

	public SoapHandler() {
		super(Phase.PRE_PROTOCOL);
		// TODO Auto-generated constructor stub
	}

	public void close(MessageContext messageContext) {
		// TODO Auto-generated method stub
		 boolean outInd = (Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		    if (outInd) {
		        try {
		            WSSecUsernameToken builder = new WSSecUsernameToken();
		            builder.setPasswordType(WSConstants.PASSWORD_TEXT);
		            builder.setUserInfo("preeti", "123");
		            builder.addNonce();
		            builder.addCreated();
		            Document doc = messageContext.getMessage().getSOAPPart().getEnvelope().getOwnerDocument();
		            WSSecHeader secHeader = new WSSecHeader();
		            secHeader.insertSecurityHeader(doc);
		           // builder.build(doc, secHeader);
		        } catch (Exception e) {
		            return ;
		        }
		    }
		    return ;

		
	}

	public boolean handleFault(SOAPMessageContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean handleMessage(SOAPMessageContext messageContext) {
		 boolean outInd = (Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		   // if (outInd) {
		        try {
		            WSSecUsernameToken builder = new WSSecUsernameToken();
		            builder.setPasswordType(WSConstants.PASSWORD_TEXT);
		            builder.setUserInfo("preeti", "123");
		            builder.addNonce();
		            builder.addCreated();

		            Document doc = messageContext.getMessage().getSOAPPart().getEnvelope().getOwnerDocument();
		            WSSecHeader secHeader = new WSSecHeader();
		            secHeader.insertSecurityHeader(doc);
		            builder.build(doc, secHeader);
		        } catch (Exception e) {
		            return false;
		        }
		   // }
		    return true;

	}

	 public Set<QName> getHeaders() { 
	        QName securityHeader = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", 
	                "Security"); 
	        HashSet<QName> headers = new HashSet<QName>(); 
	        headers.add(securityHeader);         
	        return headers; 
	    }

	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void handleMessage(Message arg0) throws Fault {
	
	     ServiceAuthHeader auth = new ServiceAuthHeader();
	     auth.setUserName("username");
	     auth.setPassword("password");
	     SoapHeader newHeader = new SoapHeader(new QName("namespace",
	"name"),auth, new JAXBDataBinding(ServiceAuthHeader.class));
	     newHeader.setDirection(Direction.DIRECTION_IN);
	     list.add(newHeader);
	}	
	}

}
*/