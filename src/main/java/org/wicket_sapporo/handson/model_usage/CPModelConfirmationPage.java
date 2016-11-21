package org.wicket_sapporo.handson.model_usage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.wicket_sapporo.handson.beans.UserLunch;

public class CPModelConfirmationPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public CPModelConfirmationPage(IModel<UserLunch> model) {
		// setDefaultModel(IModel) メソッドで、Modelをページにセットする
		setDefaultModel(CompoundPropertyModel.of(model));
		add(new Label("name"));
		add(new Label("lunch"));
	}

}
