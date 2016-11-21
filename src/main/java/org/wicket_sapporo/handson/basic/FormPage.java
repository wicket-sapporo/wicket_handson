package org.wicket_sapporo.handson.basic;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import java.util.Arrays;
import java.util.List;

/**
 * 入力フォームのページの例.
 */
public class FormPage extends WebPage {
	private static final long serialVersionUID = 1L;

	// name の値を格納するModel
	private IModel<String> nameModel;
	// lunche の値を格納するModel
	private IModel<String> lunchModel;

	/**
	 * コンストラクタ.
	 */
	public FormPage() {
		nameModel = Model.of("");
		lunchModel = Model.of("");

		// Formタグ用の Form コンポーネント
		Form<Void> form = new Form<Void>("form") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				// submit ボタンがクリックされた時の処理
				super.onSubmit();
				System.out.println("name : " + nameModel.getObject());
				System.out.println("launch : " + lunchModel.getObject());
				setResponsePage(new ConfirmationPage(nameModel, lunchModel));
			}
		};
		add(form);

		// name を入力する input tyepe="text" 用のコンポーネント
		TextField<String> nameField = new TextField<>("name", nameModel);
		form.add(nameField);

		// ラジオボタンの選択肢の準備
		List<String> lunches = Arrays.asList("鶏唐揚げ定食", "鳥かつ定食", "鳥ガーリック定食");
		// 選択肢である lunches を格納するModel. List オブジェクト用には ListModel を使う.
		IModel<List<String>> lunchesModel = new ListModel<>(lunches);

		// lunches から一つを選択する radio ボタン用のコンポーネント
		RadioChoice<String> radioChoice = new RadioChoice<>("lunch", lunchModel, lunchesModel);
		form.add(radioChoice);
	}

}