package org.csu.petstore.service;

import org.csu.petstore.Vo.CategoryVO;
import org.csu.petstore.Vo.ItemVO;
import org.csu.petstore.Vo.ProductVO;

public interface CatalogService {

    CategoryVO getCategory(String categoryId);

    ProductVO getProduct(String productId);

    ItemVO getItem(String itemId);
}
