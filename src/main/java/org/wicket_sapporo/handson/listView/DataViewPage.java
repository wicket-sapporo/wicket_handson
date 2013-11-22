package org.wicket_sapporo.handson.listView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.GridView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicket_sapporo.handson.beans.User;

/**
 * いろいろな形式でリストを表示する（ページングして表示、列数を指定して表示）ページの例.
 */
public class DataViewPage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DataViewPage() {
		/* ------------ ここから DataView ------------ */

		// 表示に必要な件数だけを持ってくるデータプロパイダ
		IDataProvider<User> myProvider = new IDataProvider<User>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void detach() {
				// modelメソッドで特殊なModelを利用しない場合は、特に何もしない
			}

			@Override
			public Iterator<? extends User> iterator(long first, long count) {
				System.out.println(first + "," + count);
				// DBから指定されたデータ件数だけを返す体で
				return getUsers((int) first, (int) (first + count)).iterator();
			}

			@Override
			public long size() {
				// DBからデータの総数を返す体で
				return getUsers(0, 4).size();
			}

			@Override
			public IModel<User> model(User object) {
				return new Model<>(object);
			}

		};

		// リストデータを一度に何件表示するかを設定できる DataView コンポーネント
		DataView<User> usersDataView = new DataView<User>("usersDateView", myProvider, 3L) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<User> item) {
				Label nameLabel = new Label("name", new Model<>(item.getModelObject().getName()));
				item.add(nameLabel);

				Label ageLabel = new Label("age", new Model<>(item.getModelObject().getAge()));
				item.add(ageLabel);
			}
		};
		add(usersDataView);
		add(new PagingNavigator("pgNav", usersDataView));

		/* ------------ ここから GridView ------------ */

		// 渡されたListを単にProviderとして扱う ListDataProvider
		IDataProvider<User> provider = new ListDataProvider<>(getUsers(0, 4));

		// 一覧の列数を指定して表示できる GridView コンポーネント。HTMLに "col" という wicket:id が必要なので注意
		GridView<User> usersGridView = new GridView<User>("usersGridView", provider) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onInitialize() {
				// OnInitializeメソッドは、コンポーネントの初期設定を行うメソッド
				super.onInitialize();
				setColumns(3);
			}

			@Override
			protected void populateEmptyItem(Item<User> item) {
				// 空になってしまうセルの表示処理
				Label nameLabel = new Label("name", new Model<>("---"));
				item.add(nameLabel);
			}

			@Override
			protected void populateItem(Item<User> item) {
				// 通常のセルの表示処理
				Label nameLabel = new Label("name", new Model<>(item.getModelObject().getName()));
				item.add(nameLabel);
			}

		};
		add(usersGridView);

	}

	// データベースから取得してきた体で
	public List<User> getUsers(int offset, int limit) {
		List<User> list = new ArrayList<>(4);
		list.add(new User("宮林　椋太", 20));
		list.add(new User("野中　茉莉花", 21));
		list.add(new User("川上　優月", 19));
		list.add(new User("稲岡　一馬", 22));
		list = new ArrayList<User>(list.subList(offset, limit));
		return list;
	}

}
