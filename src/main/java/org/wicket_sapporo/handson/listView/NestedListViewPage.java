package org.wicket_sapporo.handson.listView;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.wicket_sapporo.handson.beans.Header;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ListViewの入れ子のページの例.
 */
public class NestedListViewPage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public NestedListViewPage() {
		IModel<List<Header>> headersModel = Model.ofList(getHeaders());

		ListView<Header> headersView = new ListView<Header>("headers", headersModel) {
			private static final long serialVersionUID = -247097411283771277L;

			@Override
			protected void populateItem(ListItem<Header> item) {
				Label headerNameLabel = new Label("headerName",
					Model.of(item.getModelObject().getHeaderName()));
				item.add(headerNameLabel);

				// ListView の中に ListView を作成し、入れ子状にして使う
				ListView<String> subHeadersView = new ListView<String>("subHeaders",
					new ListModel<>(item.getModelObject().getSubHeaders())) {
					private static final long serialVersionUID = 3203465500000911089L;

					@Override
					protected void populateItem(ListItem<String> item) {
						Label headerNameLabel = new Label("headerName", item.getModel());
						item.add(headerNameLabel);
					}
				};
				item.add(subHeadersView);

			}
		};
		add(headersView);

	}

	// データベースなどから取得してきた体で
	public List<Header> getHeaders() {
		List<Header> headers = new ArrayList<>();
		headers.add(new Header("1章", Arrays.asList("1.1", "1.2", "1.3", "1.4")));
		headers.add(new Header("2章", Arrays.asList("2.1", "2.2", "2.3")));
		return headers;
	}

}
