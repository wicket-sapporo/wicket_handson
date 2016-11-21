package org.wicket_sapporo.handson.model_usage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.wicket_sapporo.handson.beans.User;

import java.util.ArrayList;
import java.util.List;

/**
 * LoadableDetachableModel と CompoundPropertyModel を使って書き換えた ListViewTablePage.
 */
public class ModelfulListViewPage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ModelfulListViewPage() {
		IModel<List<User>> usersModel = new LoadableDetachableModel<List<User>>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<User> load() {
				return getUsers();
			}
		};

		ListView<User> usersView = new ListView<User>("users", usersModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<User> item) {
				// Modelを CompoundPropertyModel で再セット.
				item.setModel(new CompoundPropertyModel<>(item.getModel()));
				item.add(new Label("name"));
				item.add(new Label("age"));
			}
		};
		add(usersView);

	}

	// データベースなどから取得してきた体で
	public List<User> getUsers() {
		List<User> list = new ArrayList<>(5);
		list.add(new User("宮林　椋太", 20));
		list.add(new User("野中　茉莉花", 21));
		list.add(new User("川上　優月", 19));
		list.add(new User("稲岡　一馬", 22));
		list.add(new User("大江　菜摘", 33));
		return list;
	}

}
