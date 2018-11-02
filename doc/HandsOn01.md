# Apache Wicket 7.x Hands-On

## 1.Wicket でWebページを作ってみる

### 手順

`com.example` パッケージに、以下のファイルを作る

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
package com.example;
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

### 動作確認

アプリケーションを起動して、ブラウザで [http://localhost:8080/](http://localhost:8080/)  を表示し、動作を確認する。

「こんにちは。今日は Wicket ハンズオンです。」というメッセージが表示されればOK。

![fig01](./fig01.png)

----

[ハンズオン2へ](./HandsOn02.md)