## 9. いろいろなModel：まとめの課題

### 手順1

`org.wicket_sapporo.handson.model_usage` パッケージに以下のファイルを作成する。

 ModelfulListViewPage.html(ListViewTablePage.html の内容をコピーする) 
 
 ModelfulListViewPage.java(空のファイル)
 
### 手順2
 
**練習： `org.wicket_sapporo.handson.listView` パッケージに作成した ListViewTablePage.java をベースとして、以下の条件を満たすように ModelfulListViewPage.java を作成しなさい。**

1. getUsers メソッドの呼び出しに LoadableDetachableModel を使う
2. ListView#populateItem() メソッドで各ユーザのデータを表示する部分に CompoundPropertyModel を使う

### 手順3

**練習： HomePage.htmlとHomePage.java を修正して、ModelfulListViewPageに移動できるLinkを追加しなさい。**

### 動作確認

􏰘􏰙􏰒􏰏􏰚􏰎􏰛􏰁􏰑􏰜􏰝􏰉􏰊􏰞􏰟􏰈􏰐􏰌􏰓􏰠􏰠􏰄􏰍􏰡􏰀アプリケーションを再起動して、ブラウザで [http://localhost:8080/](http://localhost:8080/)  からModelfulListViewPageに移動し、動作を確認する。

ListViewTablePage と同じ画面が表示されればよい。

---

[ハンズオン10へ](./HandsOn10.md)
