package com.atguigu.senior.dao;

/**
 * @author LiHongFei
 * @since 2024/6/10
 */
public interface BankDao {

    public int addMoney(Integer id, Integer money);

    public int subMoney(Integer id, Integer money);
}
