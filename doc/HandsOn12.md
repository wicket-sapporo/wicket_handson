## 12. Session(ブラウザごとの固有データ)

### 手順1

`org.wicket_sapporo.handson.session` パッケージに以下の 5 つのファイルを作成する

MySession.java

```java
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
	 * @param request {@link Request}.
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
```

SignInPage.html

```html
<!DOCTYPE html>
<html xmlns:wicket="http://wicket.apache.org">
<head>
  <meta charset="UTF-8">
  <title>LoginPage</title>
</head>
<body>
<form wicket:id="form">
  <div wicket:id="feedback"></div>
  <div>ユーザーID：<input type="text" wicket:id="userName"></div>
  <div>パスワード：<input type="password" wicket:id="passphrase"></div>
  <button type="submit">ログインして移動</button>
</form>
</body>
</html>
```

SignInPage.java

```java
package org.wicket_sapporo.handson.session;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class SignInPage extends WebPage {
	private static final long serialVersionUID = 1L;

	private IModel<String> userNameModel;
	private IModel<String> passphraseModel;

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
```

SecurePage.html

```html
<!DOCTYPE html>
<html xmlns:wicket="http://wicket.apache.org">
<head>
  <meta charset="UTF-8">
  <title>SecurePage</title>
</head>
<body>
<p><span wicket:id="userName"></span>さん、ようこそ！</p>
<p><a wicket:id="logout">ログアウト</a></p>
</body>
</html>
```

SecurePage.java

```java
package org.wicket_sapporo.handson.session;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

public class SecurePage extends WebPage {
	private static final long serialVersionUID = 1L;

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
		}
	}
}
```

### 手順2

WicketApplicationクラスのinitメソッドのオーバーライドの中に、MoutedMaperを追加する。

```java
@Override
public void init() {
  // -- 省略 --
  mount(new MountedMapper("/signin", SignInPage.class, new UrlPathPageParametersEncoder()));
}
```

WicketApplicationクラスのnewSessionメソッドをオーバーライドの中に、MｙSessionを利用するように設定する。

（メソッドが無い場合は作成する）

```java
@Override
public Session newSession(Request request, Response response) {
	// 独自に拡張したSessionの利用
	return new MySession(request);
}
```

### 手順3

HomePage.htmlに、以下のタグを追記する。（ddタグなどでラップしてもよい）

```html
<a wicket:id="toSecurePage">認証せずに直接SecurePageへ</a>
<a wicket:id="toSigninPage">SigninPageへ</a>
```

HomePage.javaに、リンクを追加する

```java
Link<Void> toSecurePageLink = new Link<Void>("toSecurePage") {
  private static final long serialVersionUID = 1L;

  @Override
  public void onClick() {
    setResponsePage(new SecurePage());
  }
};
add(toSecurePageLink);

// SignInPageはステートレスページにしているので、BookmarkablePageLinkで遷移させる
BookmarkablePageLink<Void> toSigninPageLink =
  new BookmarkablePageLink<>("toSigninPage", SignInPage.class);
add(toSigninPageLink);
```

### 動作確認1

􏰘􏰙􏰒􏰏􏰚􏰎􏰛􏰁􏰑􏰜􏰝􏰉􏰊􏰞􏰟􏰈􏰐􏰌􏰓􏰠􏰠􏰄􏰍􏰡􏰀アプリケーションを再起動して、ブラウザで [http://localhost:8080/](http://localhost:8080/)  から上記2つのリンクを使い、動作を確認する。

「認証せずに直接SecurePageへ」のリンクをクリックすると、403エラーの画面が表示される。

![fig12a](fig12a.png)

「SigninPageへ」のリンクをクリックすることで、SignInPageに移動する。ユーザ名は適当、パスワードは `1234` にすることでSecurePageにログインできる。  
また、ログイン後にログアウトできる。

![fig12b](fig12b.png)

ログアウトした後に、認証せずに直接SecurePageへ」のリンクをクリックすると、再び403エラーの画面が表示される。


### 手順4

SecurePageのonInitializerメソッドの内容を次のように書き換える。

```java
@Override
protected void onInitialize() {
  super.onInitialize();
  if (MySession.get().getUserName().equals("")) {
    // ログインしていなければ、403エラーを返す
    // throw new AbortWithHttpErrorCodeException(403, "Forbidden! You must be login!");
    // 強制的にページを転送するとき
    throw new RestartResponseException(SignInPage.class);
  }
}
```

### 動作確認2

􏰘􏰙􏰒􏰏􏰚􏰎􏰛􏰁􏰑􏰜􏰝􏰉􏰊􏰞􏰟􏰈􏰐􏰌􏰓􏰠􏰠􏰄􏰍􏰡􏰀アプリケーションを再起動して、ブラウザで [http://localhost:8080/](http://localhost:8080/)  から上記2つのリンクを使い、動作を確認する。

- ログインしていない状態で「認証せずに直接SecurePageへ」のリンクをクリックすると、SignInPageの画面が表示されるように変わっている。

----

[HandsOn13](HandsOn13.md)

