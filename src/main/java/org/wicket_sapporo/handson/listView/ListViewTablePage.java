package org.wicket_sapporo.handson.listView;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicket_sapporo.handson.beans.User;

import java.util.ArrayList;
import java.util.List;

/**
 * UsersのリストをTableタグの一覧で表示するページの例.
 */
public class ListViewTablePage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ListViewTablePage() {
		IModel<List<User>> usersModel = Model.ofList(getUsers());

		ListView<User> usersView = new ListView<User>("users", usersModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<User> item) {
				User user = item.getModelObject();

				Label nameLabel = new Label("name", Model.of(user.getName()));
				item.add(nameLabel);

				Label ageLabel = new Label("age", Model.of(user.getAge()));
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
