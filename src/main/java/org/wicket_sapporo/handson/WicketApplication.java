package org.wicket_sapporo.handson;

import org.apache.wicket.Session;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.mapper.parameter.UrlPathPageParametersEncoder;
import org.wicket_sapporo.handson.bookmarkable.ParamReceiptPage;
import org.wicket_sapporo.handson.session.MySession;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 *
 * @see jp.ac.shinshu_u.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		// サーバ・クライアント間のリクエスト・レスポンスの文字エンコード
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
		// Wicketに取り込まれるHTMLファイルのエンコード
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
		// URLのマウント
//		mount(new MountedMapper("/param_receipt", ParamReceiptPage.class));
		mount(new MountedMapper("/param_receipt", ParamReceiptPage.class, new UrlPathPageParametersEncoder()));
	}

	@Override
	public Session newSession(Request request, Response response) {
		// return super.newSession(request, response);
		// 独自に拡張したSessionの利用
		return new MySession(request);
	}

}
