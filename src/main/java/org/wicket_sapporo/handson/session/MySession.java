package org.wicket_sapporo.handson.session;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/**
 * アプリケーション用の独自のsession.
 */
public class MySession extends WebSession {
	private static final long serialVersionUID = 1L;

	private String userId;

	/**
	 * コンストラクタ.
	 * 
	 * @param request
	 *          {@link Request}.
	 */
	public MySession(Request request) {
		super(request);
		this.userId = "";
		// Sessionに変更があったことをアプリケーションに通知
		dirty();
	}

	public boolean signIn(String userName, String passphrase) {
		if (passphrase.equals("1234")) {
			// ユーザ認証処理の体で
			this.userId = userName;
			// Session Fixation対策
			replaceSession();
			dirty();
			return true;
		}
		return false;
	}

	public static MySession get() {
		return (MySession) Session.get();
	}

	public String getUserName() {
		return userId;
	}

}
