package org.wicket_sapporo.handson.bookmarkable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * クエリパラメータ付きのリンクのページの例.
 */
public class ParamSendPage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ParamSendPage() {
		BookmarkablePageLink<Void> withoutParamLink =
			new BookmarkablePageLink<>("withoutParamLink", ParamReceiptPage.class);
		add(withoutParamLink);

		// 送信するパラメータを準備.
		PageParameters pageParameters = new PageParameters();
		pageParameters.add("param1", "1000");
		pageParameters.add("param2", "2000");

		BookmarkablePageLink<Void> withinParamLink =
			new BookmarkablePageLink<>("withinParamLink", ParamReceiptPage.class, pageParameters);
		add(withinParamLink);
	}

}
