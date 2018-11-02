package com.example.listView;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Arrays;
import java.util.List;

/**
 * リストのデータを一覧で表示するページの例.
 */
public class ListViewPage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ListViewPage() {
		// 一覧で表示したいデータをListで用意する
		List<String> prefectures = Arrays.asList("北海道", "青森", "岩手", "秋田", "宮城", "福島");

		// リスト用のモデルは、ListModelを使う。
		// ListModelは、Model.ofList(List) や、new ListModel(List) でインスタンス化できる。
		IModel<List<String>> prefecturesModel = Model.ofList(prefectures);

		// Listを一覧で表示する ListView コンポーネントを生成する
		ListView<String> prefecturesView = new ListView<String>("prefectures", prefecturesModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<String> item) {
				// populateItemメソッドには、Listの要素一つ一つに実行する命令を記載する.
				// Listや配列の要素を処理するfor文みたいな役割をしている。

				// ひとつひとつの要素からModelを取り出して、Labelに利用する
				Label prefectureLabel = new Label("prefecture", item.getModel());
				// ListViewの子要素としてコンポーネントを追加するときは、itemを親と考えてaddメソッドを使うことに注意.
				item.add(prefectureLabel);
			}
		};
		add(prefecturesView);

	}

}
