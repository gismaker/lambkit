package com.lambkit.test.api.route;

import com.lambkit.core.api.route.ApiRequest;

public class GoodsServiceImpl implements GoodsService {

	// 无缝集成
	public Goods addGoods(Goods goods, Integer id, ApiRequest apiRequest) {
		return goods;
	}

	public Goods getGodds(Integer id) {
		return new Goods("vvv", id.toString());
	}

}
