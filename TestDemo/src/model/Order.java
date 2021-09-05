package model;

import annotation.Column;
import annotation.SqlState;

/**
 * @author Cdu
 * @discription:
 * @create 2021-02-06 22:24
 */
@SqlState("t_order")
public class Order {

    @Column
    private Long id;//和表字段一致
    @Column("order_no")
    private String orderNo;
    @Column("shop_id")
    private int shopId;
    @Column("user_name")
    private String userName;
    @Column("user_id")
    private int userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}