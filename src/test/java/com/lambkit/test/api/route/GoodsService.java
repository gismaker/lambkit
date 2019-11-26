package com.lambkit.test.api.route;

import com.lambkit.core.api.route.ApiMapping;
import com.lambkit.core.api.route.ApiRequest;

/**
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.test.api.route
 */
public interface GoodsService {

	// 无缝集成
	@ApiMapping(value = "lambkit.api.goods.add", useLogin = true)
	public Goods addGoods(Goods goods, Integer id, ApiRequest apiRequest);

	@ApiMapping("lambkit.api.goods.get")
	public Goods getGodds(Integer id);
}
