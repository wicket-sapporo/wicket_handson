package com.example.model_usage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.Random;

/**
 * AbstractReadOnlyModel, LoadableDetachableModel の動作ページの例.
 */
public class ROMPage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ROMPage() {

		IModel<Integer> aroModel = new AbstractReadOnlyModel<Integer>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject() {
				// getObjectされるたびに、異なるオブジェクトを返す
				return getRandomInt();
			}
		};

		Label ro1Label = new Label("ro1", aroModel);
		add(ro1Label);
		Label ro2Label = new Label("ro2", aroModel);
		add(ro2Label);
		Label ro3Label = new Label("ro3", aroModel);
		add(ro3Label);

		IModel<Integer> ldModel = new LoadableDetachableModel<Integer>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected Integer load() {
				// リクエストからレスポンスの間（もしくはdetachされるまで）は、同じオブジェクトを返す
				return getRandomInt();
			}
		};

		Label ld1Label = new Label("ld1", ldModel);
		add(ld1Label);
		Label ld2Label = new Label("ld2", ldModel);
		add(ld2Label);
		Label ld3Label = new Label("ld3", ldModel);
		add(ld3Label);

	}

	public int getRandomInt() {
		return new Random().nextInt(1000);
	}

}
