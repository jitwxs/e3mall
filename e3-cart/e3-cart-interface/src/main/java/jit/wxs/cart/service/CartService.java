package jit.wxs.cart.service;

import jit.wxs.manager.pojo.TbItem;

import java.util.List;

/**
 * 购物车Service
 * @author jitwxs
 * @date 2018/4/17 13:53
 */
public interface CartService {
    boolean addCart(Long userId, Long itemId, Integer num);

    boolean mergeCart(Long userId, List<TbItem> itemList);

    List<TbItem> getCartList(Long userId);

    boolean updateCartNum(Long userId, Long itemId, Integer num);

    boolean deleteCartItem(Long userId, Long itemId);

    boolean clearCart(Long userId);
}