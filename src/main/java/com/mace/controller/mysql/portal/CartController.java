package com.mace.controller.mysql.portal;

import com.mace.common.Constant;
import com.mace.common.ResponseMessage;
import com.mace.domain.Cart;
import com.mace.domain.User;
import com.mace.fastjson.util.FastJsonUtil;
import com.mace.mongodb.util.MongoOpsUtil;
import com.mace.rabbitmq.service.IRabbitMQService;
import com.mace.redis.service.IRedisService;
import com.mace.service.mysql.ICartService;
import com.mace.util.CookieUtil;
import com.mace.util.StringHelper;
import com.mace.vo.CartProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * description:
 * <br />
 * Created by mace on 17:08 2018/6/8.
 */
@RestController
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private ICartService iCartService;
    @Autowired
    private IRedisService iRedisService;
    @Autowired
    private IRabbitMQService iRabbitMQService;

    @RequestMapping("add.do")
    public ResponseMessage add(HttpSession session,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               CartProductVo cartProductVo){

        //1.判断登录状态
        User user = (User)session.getAttribute(Constant.CURRENT_USER);

        if(user == null){
            // 没有登录
            // 查询当前浏览器是否存在购物车 cookie: off_line_shopping_cart_id
            // 如果存在则 重置 cookie 生命周期 返回 outerKey
            String outerKey = CookieUtil.resetCookie(request,
                    Constant.Cookie.OFF_LINE_SHOPPING_CART_ID, Constant.Cookie.ONE_MONTH);

            if(outerKey == null){
                // 不存在 cookie: off_line_shopping_cart_id
                // 生成一个唯一的outerKey，保存到cookie中 作为redis map数据类型的 外部键 内部建为 店铺ID
                // cartMap:ObjectId
                outerKey = Constant.Redis.KEY_USER_CART_PREFIX + MongoOpsUtil.ObjectId2Str();
                CookieUtil.addCookie(response, Constant.Cookie.OFF_LINE_SHOPPING_CART_ID,
                        outerKey, Constant.Cookie.ONE_MONTH);
            }

            // 存在或者已经新增 cookie
            // 去 redis 添加商品信息到离线购物车 outerKey 过期时间
            // 考虑到真实电商环境：同一商品，但因品种或者规格不一致 可能价格也是不一样的。所以应当分而存之
            // redis outerKey innerKey：(店铺 ID) innerValue: (cartProductVo json序列串)
            // 仅当 hashKey 不存在时才设置散列 hashKey 的值
            // cartMap:ObjectId:shopId
            String innerKey = outerKey + ":" + cartProductVo.getShopId();
            boolean putIfAbsent = iRedisService.putIfAbsent(outerKey,
                    innerKey,FastJsonUtil.toJSONString(cartProductVo));

            // 返回 false 已存在 innerKey：(product_id)
            /*if( !putIfAbsent ){
                // 重新设值 innerValue: cartProductVo
                // 考虑 productType、count
                String value = (String) iRedisService.getHashKey(outerKey,
                                              String.valueOf(cartProductVo.getProductId()));
                // 如果 productType 一样则 只需 update count
                // productType 不一样则 拼接新的 productType 作为新的 productType 用 "|" 作为分隔符
                // 格式：多个 productType 时：productType1:count1|productType2:count2
                // 可以重写 equal() 实现 只比较 productType
                CartProductVo cartProductVo_redis = FastJsonUtil.toBean(value, CartProductVo.class);

                if(cartProductVo_redis.equals(cartProductVo)){
                    // update cartProductVo_redis.count

                }

            }*/

            // 离线购物车成功

            return null;

        }else{
            // 登录状态
            // 1.查询当前浏览器是否存在购物车 cookie: off_line_shopping_cart_id
            //   如果存在则去redis同步离线购物车数据
            String outerKey = CookieUtil.resetCookie(request,
                    Constant.Cookie.OFF_LINE_SHOPPING_CART_ID, Constant.Cookie.ONE_MONTH);

            if(outerKey != null){
                // 使用消息队列异步同步离线购物车数据至mysql数据库
                // a. 获取离线购物车数据
                Map<Object, Object> off_line_shopping_cart = iRedisService.getHashEntries(outerKey);
                // b. rabbitmq 发送数据到 redis_off_line_cart 队列
                // 当监听到队列消息时，批量入库。
                iRabbitMQService.syncOfflineShoppingCart(off_line_shopping_cart, user.getId());
            }

            // 2.不存在离线购物车 直接插入数据库。
            Cart cart = assembleCartByCartProductVo(cartProductVo, user.getId());

            // mybatis



        }

        return null;
    }


    public static Cart assembleCartByCartProductVo(CartProductVo cartProductVo, Integer userId){

        Cart cart = new Cart();

        cart.setProduct_id(cartProductVo.getProductId());
        cart.setChecked(cartProductVo.getProductChecked());
        cart.setQuantity(cartProductVo.getCount());
        cart.setUser_id(userId);

        return cart;
    }
}
