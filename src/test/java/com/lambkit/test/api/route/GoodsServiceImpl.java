package com.lambkit.test.api.route;

import com.lambkit.core.api.route.ApiRequest;

public class GoodsServiceImpl implements GoodsService {

	// 无缝集成
	public Goods addGoods(Goods goods, Integer id, ApiRequest apiRequest) {
		return goods;
	}

	public Goods getGodds(Integer id) {
		if(id==0) {
			return null;
		} else {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new Goods("vvv", id.toString());
		}
	}

}
