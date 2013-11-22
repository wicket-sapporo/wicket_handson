package org.wicket_sapporo.handson.listView;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;

/**
 * リストのデータを一覧で表示するページの例.
 */
public class ListViewPage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ListViewPage() {
		// 一覧で表示したいデータをListで用意し、ListModelへ格納
		List<String> prefectures = Arrays.asList("北海道", "青森", "岩手", "秋田", "宮城", "福島");
		IModel<List<String>> prefecturesModel = new ListModel<>(prefectures);

		// Listを一覧で表示する ListView コンポーネント
		ListView<String> prefecturesView = new ListView<String>("prefectures", prefecturesModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<String> item) {
				// Listの要素一つ一つに実行する命令を記載する.
				Label prefectureLabel = new Label("prefecture", item.getModel());
				// listの要素をコンポーネントとして追加するときは、itemのaddメソッドを使うことに注意.
				item.add(prefectureLabel);
			}
		};
		add(prefecturesView);

	}

}
