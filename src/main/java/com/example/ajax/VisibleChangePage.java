package com.example.ajax;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;

/**
 * Ajaxでコンポーネントの表示・非表示を設定するページの例.
 */
public class VisibleChangePage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public VisibleChangePage() {
		// 匿名クラスの内部から参照される変数はfinal宣言が必要
		final WebMarkupContainer green = new WebMarkupContainer("visible") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onInitialize() {
				super.onInitialize();
				// Ajaxの更新対象となるコンポーネントには setOutputMarkupId(true) を実行する.
				// これにより、JavaScriptがフックするための id がタグに加えられる.
				setOutputMarkupId(true);
				// 非表示（visible = false）状態から表示（visible = true）になる可能性があるタグには、
				// setOutputMarkupPlaceholderTag(true) を実行する。これにより、表示上は消えても、JavaScriptが
				// フックできるタグが残る.
				setOutputMarkupPlaceholderTag(true);
				setVisible(false);
			}

			@Override
			protected void onConfigure() {
				super.onConfigure();
				// コンポーネントがAjaxなどで更新される度に実行されるメソッド.
				// ここでは、コンポーネントの表示/非表示状態を切り替えている.
				setVisible(!isVisibleInHierarchy());
			}
		};
		add(green);

		add(new AjaxLink<Void>("link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				// AjaxLinkがクリックされた時の処理
				target.add(green);
			}

		});
	}

}
