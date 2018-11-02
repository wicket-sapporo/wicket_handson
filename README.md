Wicket Handson
==============

このプロジェクトは [Apache Wicket](http://wicket.apache.org/) の使い方を学習するためのハンズオン用のプロジェクトです。

このプロジェクトを実行するためには、Java8およびMavenが動作するIDE(IntelliJ, NetBeans, Eclipse...)が必要です。

## はじめに

Mavenのインストールが必要かどうかを確認します。下のコマンドをターミナル（コマンドプロンプト）で実行してください。

```sh
mvn -version
```

```sh
# 実行結果の例
Apache Maven 3.5.4 (1edded0938998edf8bf061f1ceb3cfdeccf443fe; 2018-06-18T03:33:14+09:00)
Maven home: /usr/local/Cellar/maven/3.5.4/libexec
(以下略）
```

このように、Mavenのバージョンやインストール先が表示されれば成功ですので、「ハンズオンの準備」に進んでください。

こういった情報が表示されない場合は、先にMavenのインストールが必要です。（参考：[Maven Download](https://maven.apache.org/download.cgi), [Maven Install](https://maven.apache.org/install.html)）

Macの場合は、[Homebrew](https://brew.sh/index_ja) を使うのがおそらく一番早いです。

### ハンズオンの準備
  
Apache WicketのQuick Startページから、Mavenプロジェクトをダウンロードします。  
[Create a Wicket Quickstart](https://wicket.apache.org/start/quickstart.html)に移動し、フォームを以下のように変更してください。

- **Group ID** `com.example`
- **Artifact ID** `wicket_handson`
- **Wicket Version** `7.10.0`

**generated command line** の欄に生成されたコマンドをコピーして、ハンズオンを進めるPCのターミナルもしくはコマンドプロンプトに貼り付けてください。

```sh
# コマンドの例
mvn archetype:generate -DarchetypeGroupId=org.apache.wicket -DarchetypeArtifactId=wicket-archetype-quickstart -DarchetypeVersion=7.10.0 -DgroupId=com.mycompany -DartifactId=myproject -DarchetypeRepository=https://repository.apache.org/ -DinteractiveMode=false
```

```sh
# 実行結果の例
[INFO] Scanning for projects...
（中略）
[INFO] Project created from Archetype in dir: /***/***/wicket_handson
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 48.966 s
[INFO] Finished at: 2018-11-02T15:36:24+09:00
[INFO] ------------------------------------------------------------------------
```

コマンドを実行したフォルダにwicket_handsonフォルダが作成されるので、これをIntelliJなどのIDEで読み込んでください。

## 動作確認方法

1. IDEから、 `src/test/Start.java` を実行してください。
1. ブラウザから [http://localhost:8080/](http://localhost:8080/) にアクセスし、Wicketの動作画面が表示されることを確認してください。
1. 動作確認ができたら、 `Start.java` を停止してください。

## ハンズオンの進め方

1. [HandsOn01.md](./doc/HandsOn01.md) から、指示に従いながらプログラミングと動作確認を進めて下さい。
1. 模範解答は、[srcディレクトリ](./src/main/java)にコミットしていますので、適宜参考にしてください。
