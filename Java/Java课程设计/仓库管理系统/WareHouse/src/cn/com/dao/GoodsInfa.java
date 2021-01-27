package cn.com.dao;

import java.util.List;

import cn.com.VO.GoodsVO;

public interface GoodsInfa {
	boolean goodsInsert(GoodsVO gvo);
	boolean updateShipment(String date,GoodsVO gvo);
	List<GoodsVO> selectShipmentByGoodsID(GoodsVO gvo);
	List<GoodsVO> selectShipmentByGoodsName(GoodsVO gvo);
	List<GoodsVO> selectAll();
	List<GoodsVO> selectByGoodsID(String goodsID);
	List<GoodsVO> selectByGoodsName(String goodsName);
	List<GoodsVO> selectByGoodsClass(String goodsClass);
	List<GoodsVO> selectByAmount(int amount);
	List<GoodsVO> selectByAreaID(String areaID);
	List<GoodsVO> selectByStockPrice(float stockPrice);
	List<GoodsVO> selectByStockTime(String stockTime);
	List<GoodsVO> selectByShipmentTime(String shipmentTime);
}
