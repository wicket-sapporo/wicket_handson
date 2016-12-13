package org.wicket_sapporo.handson;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicket_sapporo.handson.ajax.VisibleChangePage;
import org.wicket_sapporo.handson.basic.FormPage;
import org.wicket_sapporo.handson.bookmarkable.ParamSendPage;
import org.wicket_sapporo.handson.listView.DataViewPage;
import org.wicket_sapporo.handson.listView.ListViewPage;
import org.wicket_sapporo.handson.listView.ListViewTablePage;
import org.wicket_sapporo.handson.listView.NestedListViewPage;
import org.wicket_sapporo.handson.model_usage.CPMFormPage;
import org.wicket_sapporo.handson.model_usage.ModelfulListViewPage;
import org.wicket_sapporo.handson.model_usage.ReadOnlyModelPage;
import org.wicket_sapporo.handson.session.SecurePage;
import org.wicket_sapporo.handson.session.SignInPage;
import org.wicket_sapporo.handson.validation.ValidationFormPage;

/**
 * アプリケーションの最初に表示されるページ.
 */
public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage() {
		String message = "こんにちは。今日は Wicket ハンズオンです。";

		IModel<String> label1Model = Model.of(message);

		Label label1 = new Label("label1", label1Model);

		add(label1);

		Link<Void> toFormPageLink = new Link<Void>("toFormPage") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new FormPage());
			}
		};
		add(toFormPageLink);

		Link<Void> toListViewPageLink = new Link<Void>("toListViewPage") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ListViewPage());
			}
		};
		add(toListViewPageLink);

		Link<Void> toListViewTablePageLink = new Link<Void>("toListViewTablePage") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ListViewTablePage());
			}
		};
		add(toListViewTablePageLink);

		Link<Void> toDataViewPageLink = new Link<Void>("toDataViewPage") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new DataViewPage());
			}
		};
		add(toDataViewPageLink);

		Link<Void> toNestedViewPageLink = new Link<Void>("toNestedListViewPage") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new NestedListViewPage());
			}
		};
		add(toNestedViewPageLink);

		Link<Void> toCPModelFormPageLink = new Link<Void>("toCPMFormPage") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new CPMFormPage());
			}
		};
		add(toCPModelFormPageLink);

		Link<Void> toROModelPageLink = new Link<Void>("toROModelPage") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ReadOnlyModelPage());
			}
		};
		add(toROModelPageLink);

		Link<Void> toModelFulListViewLink = new Link<Void>("toModelFulListView") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ModelfulListViewPage());
			}
		};
		add(toModelFulListViewLink);

		Link<Void> toValidationFormPageLink = new Link<Void>("toValidationFormPage") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ValidationFormPage());
			}
		};
		add(toValidationFormPageLink);

		Link<Void> toParamSendPageLink = new Link<Void>("toParamSendPage") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ParamSendPage());
			}
		};
		add(toParamSendPageLink);

		// SignInPageはステートレスページにしているので、BookmarkablePageLinkで遷移させる
		BookmarkablePageLink<Void> toSigninPageLink =
			new BookmarkablePageLink<>("toSigninPage", SignInPage.class);
		add(toSigninPageLink);

		Link<Void> toSecurePageLink = new Link<Void>("toSecurePage") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new SecurePage());
			}
		};
		add(toSecurePageLink);

		Link<Void> toVisibleChangePageLink = new Link<Void>("toVisibleChangePage") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new VisibleChangePage());
			}
		};
		add(toVisibleChangePageLink);
	}

}
