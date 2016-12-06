package jason.emeric.app_bar.service;


import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import jason.emeric.app_bar.service.model.UserDto;




@Singleton
@Startup
public class LDAPService implements ILDAPService {

	private DirContext ctx;

	@PostConstruct
	private void post() {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldaps://rldap-ul.cri.uhp-nancy.fr:636");

		// Authenticate as S. User and password "mysecret"
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=lecteur-applibar-esstin,dc=sys");
		env.put(Context.SECURITY_CREDENTIALS, "A5p4liEsstinUHP");

		// Create the initial context
		try {
			ctx = new InitialDirContext(env);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			ctx = null;
			e.printStackTrace();
		}
	}

	@Override
	public UserDto getInfo(String login) {
		try {
			if (ctx == null){
				return null;
			}
			SearchControls searchControls = new SearchControls();
			searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			NamingEnumeration<SearchResult> enumeration = ctx
					.search("dc=univ-lorraine,dc=fr", "(uid="+login+")",
							searchControls);
			if (!enumeration.hasMore())
				return null;
			SearchResult result = enumeration.next();
			Attributes attrs = result.getAttributes();
			UserDto c = new UserDto();
			c.setFirstname((String) attrs.get("givenName").get(0));
			c.setName((String) attrs.get("sn").get(0));
			c.setLogin(login);
			c.setMail((String) attrs.get("mail").get(0));
			return c;
		} catch (Exception e) {
			post();
			e.printStackTrace();
			return null;
		}
	}

}
