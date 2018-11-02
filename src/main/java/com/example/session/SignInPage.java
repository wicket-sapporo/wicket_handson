package com.example.session;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * ログインページの例. 通常のForm, Linkでも構築できるが、ステートフル扱いになりSession Timeoutエラーなどが
 * 生じる可能性があるので、StatelessFormとStatelessLinkを使ってステートレスなページにしている.
 */
public class SignInPage extends WebPage {
	private static final long serialVersionUID = 1L;

	private IModel<String> userNameModel;
	private IModel<String> passphraseModel;

	/**
	 * コンストラクタ.
	 */
	public SignInPage() {
		userNameModel = Model.of("");
		passphraseModel = Model.of("");

		StatelessForm<Void> form = new StatelessForm<Void>("form") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				super.onSubmit();
				// セッションにユーザ名を格納
				if (MySession.get().signIn(userNameModel.getObject(), passphraseModel.getObject())) {
					setResponsePage(new SecurePage());
				} else {
					error("IDもしくはパスワードが間違っています");
				}
			}
		};
		add(form);

		form.add(new FeedbackPanel("feedback"));

		form.add(new TextField<>("userName", userNameModel));
		// パスワード欄専用のテキストフィールド.
		form.add(new PasswordTextField("passphrase", passphraseModel));
	}

}
