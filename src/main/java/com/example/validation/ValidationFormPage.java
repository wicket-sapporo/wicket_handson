package com.example.validation;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.validation.validator.StringValidator;
import com.example.beans.UserLunch;

import java.util.Arrays;
import java.util.List;

/**
 * 入力チェックを加えた CPMFormPage.
 */
public class ValidationFormPage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ValidationFormPage() {
		IModel<UserLunch> userLunchModel = new CompoundPropertyModel<>(new UserLunch());

		Form<UserLunch> form = new Form<UserLunch>("form", userLunchModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				super.onSubmit();
				System.out.println("name : " + getModelObject().getName());
				System.out.println("launch : " + getModelObject().getLunch());
				if (getModelObject().getLunch().equals("鳥ガーリック定食")) {
					error(getModelObject().getName() + "さん、鳥ガーリック定食は売り切れです...");
				} else {
					info(getModelObject().getName() + "さんの注文が完了しました！");
				}
			}
		};
		add(form);

		// エラーメッセージを表示するためのFeedbackPanelを用意
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		form.add(feedbackPanel);

		TextField<String> nameField = new TextField<String>("name") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onInitialize() {
				super.onInitialize();
				// テキストフィールドを必須にして、最小3文字最大8文字に設定する。
				setRequired(true);
				add(StringValidator.minimumLength(3));
				add(StringValidator.maximumLength(8));
			}
		};
		form.add(nameField);

		List<String> lunches = Arrays.asList("鶏唐揚げ定食", "鳥かつ定食", "鳥ガーリック定食");
		IModel<List<String>> lunchesModel = new ListModel<>(lunches);

		RadioChoice<String> radioChoice = new RadioChoice<String>("lunch", lunchesModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onInitialize() {
				super.onInitialize();
				// ラジオボタンの選択を必須にする。
				setRequired(true);
			}
		};
		form.add(radioChoice);

	}

}
