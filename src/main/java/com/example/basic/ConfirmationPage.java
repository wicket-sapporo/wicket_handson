package com.example.basic;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * 入力フォームの値を表示するページの例.
 */
public class ConfirmationPage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 *
	 * @param nameModel  入力された名前を格納したModel.
	 * @param lunchModel 選択された昼食メニューを格納したModel.
	 */
	public ConfirmationPage(IModel<String> nameModel, IModel<String> lunchModel) {

		Label nameLabel = new Label("name", nameModel);

		add(nameLabel);

		Label lunchLabel = new Label("lunch", lunchModel);

		add(lunchLabel);

	}

}
