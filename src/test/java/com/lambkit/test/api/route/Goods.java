package com.lambkit.test.api.route;

/**
 *
 * @版权 : Copyright (c) 2019-2021 爱特拉斯信息科技有限公司技术开发部
 * @author: queer
 * @E-mail: xuzhonghao0808@163.com
 * @创建日期: 2019年11月16日 下午4:43:15
 * @ClassName Goods
 * @类描述-Description: TODO
 */
public class Goods {
	private String goodsName;
	private String goodsId;

	public Goods() {

	}

	public Goods(String goodsName, String goodsId) {
		this.goodsName = goodsName;
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
}
