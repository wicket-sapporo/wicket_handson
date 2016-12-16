package org.wicket_sapporo.handson.session;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.flow.AbortWithHttpErrorCodeException;

/**
 * ログイン後のサンプルページ.
 */
public class SecurePage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SecurePage() {
		add(new Label("userName", Model.of(MySession.get().getUserName())));

		add(new Link<Void>("logout") {
			private static final long serialVersionUID = 237225927438109401L;

			@Override
			public void onClick() {
				MySession.get().invalidateNow();
				throw new RestartResponseException(SignInPage.class);
			}
		});
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		if (MySession.get().getUserName().equals("")) {
			// ログインしていなければ、403エラーを返す
			throw new AbortWithHttpErrorCodeException(403, "Forbidden! You must be login!");
			// 強制的にページを転送するとき
			// throw new RestartResponseException(SignInPage.class);
		}
	}

}
