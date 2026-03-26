package org.csu.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.petstore.Vo.CategoryVO;
import org.csu.petstore.Vo.ItemVO;
import org.csu.petstore.Vo.ProductVO;
import org.csu.petstore.entity.Category;
import org.csu.petstore.entity.Item;
import org.csu.petstore.entity.ItemQuantity;
import org.csu.petstore.entity.Product;
import org.csu.petstore.persistence.CategoryMapper;
import org.csu.petstore.persistence.ItemMapper;
import org.csu.petstore.persistence.ItemQuantityMapper;
import org.csu.petstore.persistence.ProductMapper;
import org.csu.petstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemQuantityMapper itemQuantityMapper;

    @Override
    public CategoryVO getCategory(String categoryId) {
        CategoryVO categoryVO=new CategoryVO();
        QueryWrapper<Category> categoryQueryWrapper=new QueryWrapper<>();
        categoryQueryWrapper.eq("catid",categoryId);
        Category category=categoryMapper.selectOne(categoryQueryWrapper);

        QueryWrapper<Product> productQueryWrapper=new QueryWrapper<>();
        productQueryWrapper.eq("category",categoryId);
        List<Product> productList=productMapper.selectList(productQueryWrapper);

        categoryVO.setCategoryId(categoryId);
        categoryVO.setCategoryName(category.getName());
        categoryVO.setProductList(productList);
        return categoryVO;
    }

    @Override
    public ProductVO getProduct(String productId) {
        ProductVO productVO=new ProductVO();
        QueryWrapper<Product> productQueryWrapper=new QueryWrapper<>();
        productQueryWrapper.eq("productid",productId);
        Product product=productMapper.selectOne(productQueryWrapper);

        QueryWrapper<Item> itemQueryWrapper=new QueryWrapper<>();
        itemQueryWrapper.eq("productid",productId);
        List<Item> itemList=itemMapper.selectList(itemQueryWrapper);

        productVO.setProductId(productId);
        productVO.setCategoryId(product.getCategoryId());
        productVO.setProductName(product.getName());
        productVO.setItemList(itemList);
        return productVO;
    }

    @Override
    public ItemVO getItem(String itemId) {
        ItemVO itemVO=new ItemVO();
        QueryWrapper<Item> itemQueryWrapper=new QueryWrapper<>();
        itemQueryWrapper.eq("itemid",itemId);
        Item item=itemMapper.selectOne(itemQueryWrapper);

        QueryWrapper<Product> productQueryWrapper=new QueryWrapper<>();
        productQueryWrapper.eq("productid",item.getProductId());
        Product product=productMapper.selectOne(productQueryWrapper);

        QueryWrapper<ItemQuantity> itemQuantityQueryWrapper=new QueryWrapper<>();
        itemQuantityQueryWrapper.eq("itemid",itemId);
        ItemQuantity itemQuantity=itemQuantityMapper.selectOne(itemQuantityQueryWrapper);

        itemVO.setItemId(itemId);
        itemVO.setListPrice(item.getListPrice());
        itemVO.setAttributes(item.getAttribute1());
        itemVO.setProductId(product.getProductId());
        itemVO.setProductName(product.getName());
//        itemVO.setDescription(product.getDescription());
        String[] temp=product.getDescription().split("\"");
        itemVO.setDescriptionImage(temp[1]);
        itemVO.setDescriptionText(temp[2].substring(1));
        itemVO.setQuantity(itemQuantity.getQuantity());
        return itemVO;
    }
}
