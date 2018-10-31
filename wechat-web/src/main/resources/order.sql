SELECT
	d.id as '订单id',
	d.`name` AS '姓名',
	d.phone AS '电话',
	d.title AS '商品类型',
	d.size as '尺寸',
	d.price AS '价格',
	d.province AS '省份',
	d.city AS '城市',
	d.county AS '区域',
	d.address AS '详细地址',
	d.num AS '数量',
  d.create_time as '下单时间'
FROM
	`wy_order` d;