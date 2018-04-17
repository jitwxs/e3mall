package jit.wxs.cart.service.impl;

import jit.wxs.cart.service.CartService;
import jit.wxs.common.jedis.JedisClient;
import jit.wxs.common.utils.JsonUtils;
import jit.wxs.manager.mapper.TbItemMapper;
import jit.wxs.manager.pojo.TbItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jitwxs
 * @date 2018/4/17 14:02
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private TbItemMapper itemMapper;

    @Value("${redis.cart.pre}")
    private String CART_PRE;

    @Override
    public boolean addCart(Long userId, Long itemId, Integer num) {
        String redisKey = CART_PRE + ":" + userId;

        // 判断Redis中书否存在该商品
        Boolean hasExist = jedisClient.hexists(redisKey, itemId + "");
        // 如果存在，则数量相加
        if (hasExist) {
            String json = jedisClient.hget(redisKey, itemId + "");
            TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
            item.setNum(item.getNum() + num);

            // 回写Redis
            jedisClient.hset(redisKey, itemId + "", JsonUtils.objectToJson(item));

            return true;
        }

        // 如果不存在，将该商品写入Redis
        TbItem item = itemMapper.selectById(itemId);
        // 将商品Num属性修改为购物车的数量
        item.setNum(num);
        // 将商品Image属性修改为一张图片
        if (StringUtils.isNotBlank(item.getImage())) {
            item.setImage(item.getImage().split(",")[0]);
        }
        // 回写Redis
        jedisClient.hset(redisKey, itemId + "", JsonUtils.objectToJson(item));

        return true;
    }

    @Override
    public boolean mergeCart(Long userId, List<TbItem> itemList) {
        for (TbItem item : itemList) {
            addCart(userId, item.getId(), item.getNum());
        }

        return true;
    }

    /**
     * 根据id查询到购物车列表
     */
    @Override
    public List<TbItem> getCartList(Long userId) {
        List<String> jsonList = jedisClient.hvals(CART_PRE + ":" + userId);
        List<TbItem> items = new ArrayList<>();

        for (String json : jsonList) {
            TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
            items.add(item);
        }

        return items;
    }

    @Override
    public boolean updateCartNum(Long userId, Long itemId, Integer num) {
        String redisKey = CART_PRE + ":" + userId;
        String json = jedisClient.hget(redisKey, itemId + "");
        TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
        item.setNum(num);

        // 回写Redis
        jedisClient.hset(redisKey, itemId + "", JsonUtils.objectToJson(item));

        return true;
    }

    @Override
    public boolean deleteCartItem(Long userId, Long itemId) {
        jedisClient.hdel(CART_PRE + ":" + userId, itemId + "");

        return true;
    }

    @Override
    public boolean clearCart(Long userId) {
        jedisClient.del(CART_PRE + ":" + userId);
        return true;
    }
}
