package com.mace.service.mysql.impl;

import com.mace.mapper_mysql.CartMapper;
import com.mace.mapper_mysql.ProductMapper;
import com.mace.service.mysql.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 * <br />
 * Created by mace on 17:12 2018/6/8.
 */
@Service("iCartService")
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

}
