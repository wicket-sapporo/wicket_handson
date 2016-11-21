package org.wicket_sapporo.handson.bookmarkable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * クエリパラメータを受け取るページの例.
 */
public class ParamReceiptPage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ParamReceiptPage() {
		this(null);
	}

	/**
	 * コンストラクタ.
	 *
	 * @param params クエリパラメータ.
	 */
	public ParamReceiptPage(PageParameters params) {
		IModel<String> param1Model = Model.of("パラメータが送信されていません");
		IModel<String> param2Model = Model.of("パラメータが送信されていません");
		if (params != null) {
			// URLクエリパラメータを取得する。toStringメソッドの引数はパラメータの値が無いときの初期値.
			// BookmaekablePageLinkでアクセスされた場合のURLやパラメータ文字列形式の設定は WebApplication クラスのサブクラスで行います.
			param1Model.setObject(params.get("param1").toString("パラメータがありません"));
			param2Model.setObject(params.get("param2").toString("パラメータがありません"));
		}
		add(new Label("param1", param1Model));
		add(new Label("param2", param2Model));
	}

}
