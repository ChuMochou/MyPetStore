package org.csu.petstore.service;

import org.csu.petstore.Vo.CategoryVO;
import org.csu.petstore.Vo.ItemVO;
import org.csu.petstore.Vo.ProductVO;

public interface CatalogService {

    public CategoryVO getCategory(String categoryId);

    public ProductVO getProduct(String productId);

    public ItemVO getItem(String itemId);
}
