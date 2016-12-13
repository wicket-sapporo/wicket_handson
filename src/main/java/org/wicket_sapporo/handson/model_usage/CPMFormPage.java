package org.wicket_sapporo.handson.model_usage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.wicket_sapporo.handson.beans.UserLunch;

import java.util.Arrays;
import java.util.List;

/**
 * CompoundPropertyModelを使って書き換えた FormPage.
 */
public class CPMFormPage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CPMFormPage() {
		// wicket:idとUserLunchのフィールド変数を関連づける CompoundPropertyModel を準備
		IModel<UserLunch> userLunchModel = new CompoundPropertyModel<>(new UserLunch());

		// Form以下のコンポーネントにuserLunchModelの能力を適用する
		Form<UserLunch> form = new Form<UserLunch>("form", userLunchModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				super.onSubmit();
				System.out.println("name : " + getModelObject().getName());
				System.out.println("launch : " + getModelObject().getLunch());
				setResponsePage(new CPMConfirmationPage(getModel()));
			}
		};
		add(form);

		// 入出力データは userLunch の name を参照する
		TextField<String> nameField = new TextField<>("name");
		form.add(nameField);

		List<String> lunches = Arrays.asList("鶏唐揚げ定食", "鳥かつ定食", "鳥ガーリック定食");
		IModel<List<String>> lunchesModel = new ListModel<>(lunches);

		// 入出力データは userLunch の lunch を参照する
		RadioChoice<String> radioChoice = new RadioChoice<>("lunch", lunchesModel);
		form.add(radioChoice);

	}

}
