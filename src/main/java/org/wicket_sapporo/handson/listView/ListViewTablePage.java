package org.wicket_sapporo.handson.listView;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.wicket_sapporo.handson.beans.User;

/**
 * UsersのリストをTableタグの一覧で表示するページの例.
 */
public class ListViewTablePage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ListViewTablePage() {
		IModel<List<User>> usersModel = new ListModel<>(getUsers());

		ListView<User> usersView = new ListView<User>("users", usersModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<User> item) {
				Label nameLabel = new Label("name", new Model<>(item.getModelObject().getName()));
				item.add(nameLabel);

				Label ageLabel = new Label("age", new Model<>(item.getModelObject().getAge()));
				item.add(ageLabel);
			}
		};
		add(usersView);
	}

	// データベースなどから取得してきた体で
	public List<User> getUsers() {
		List<User> list = new ArrayList<>(4);
		list.add(new User("宮林　椋太", 20));
		list.add(new User("野中　茉莉花", 21));
		list.add(new User("川上　優月", 19));
		list.add(new User("稲岡　一馬", 22));
		return list;
	}

}
