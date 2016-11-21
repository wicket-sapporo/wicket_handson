# Apache Wicket 7.x Hands-On

## 1.Wicket でWebページを作ってみる

`org.wicket_sapporo.handson` パッケージに、以下のファイルを作る

HomePage.html

```html
<!DOCTYPE html>
<html xmlns:wicket="http://wicket.apache.org">
<head>
  <meta charset="UTF-8"/>
  <title>HomePage</title>
</head>
<body>
  <h1>このページはWicketで動作しています</h1>
  <p wicket:id="label1">message is here.</p>
</body>
</html>
```

HomePage.java

```java
package org.wicket_sapporo.handson;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
 
public class HomePage extends WebPage {
  private static final long serialVersionUID = 1L;

  public HomePage() {
    String message = "こんにちは。今日はWicketハンズオンです。";
    IModel<String> label1Model = Model.of(message);
    Label label1 = new Label("label1", label1Model);
    add(label1);
  }
}
```

アプリケーションを起動して、ブラウザでhttp://localhost:8080/ を表示し、動作を確認する。
「こんにちは。今日は Wicket ハンズオンです。」というメッセージが表示されればOK。

![fig01](./fig01.png)

## 2. Formを作る

`org.wicket_sapporo.handson.basic` パッケージに以下の2つのファイルを作る。

FormPage.html

```html
<!DOCTYPE html>
<html xmlns:wicket="http://wicket.apache.org">
<head>
  <meta charset="UTF-8">
  <title>FormPage</title>
</head>
<body>
<h2>入力フォームを作る</h2>
<form wicket:id="form">
  氏名：<input type="text" wicket:id="name">
  <button type="submit">データ送信</button>
</form>
</body>
</html>
```


FormPage.java

```java
public class FormPage extends WebPage {
	private static final long serialVersionUID = 1L;

	// name の値を格納するModel
	private IModel<String> nameModel;
	
	/**
	 * コンストラクタ.
	 */
	public FormPage() {
		nameModel = Model.of("");

		// Formタグ用の Form コンポーネント
		Form<Void> form = new Form<Void>("form") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				// submit ボタンがクリックされた時の処理
				super.onSubmit();
				System.out.println("name : " + nameModel.getObject());
				setResponsePage(new ConfirmationPage(nameModel));
			}
		};
		add(form);

		// name を入力する input tyepe="text" 用のコンポーネント
		TextField<String> nameField = new TextField<>("name", nameModel);
		form.add(nameField);
	}
}		
```

HomePage.htmlの body タグ内に以下を追加する。

```html
<dl>
  <dt>基本編</dt>
  <dd><a wicket:id="toFormPage">FormPageへ</a></dd>
</dl>
```

HomePage.java のコンストラクタに以下を追加する。

```java
Link<Void> toFormPageLink = new Link<Void>("toFormPage") {
private static final long serialVersionUID = 1L;

  @Override
  public void onClick() {
    setResponsePage(new FormPage());
  }
};
add(toFormPageLink);
```

ブラウザでhttp://localhost:8080/ から FormPage に移動して動作を確認する。
フォームに入力して送信した文字列がコンソールに表示されればOK。

![fig02](./fig02.png)