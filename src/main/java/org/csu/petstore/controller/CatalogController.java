package org.csu.petstore.controller;

import org.csu.petstore.Vo.CategoryVO;
import org.csu.petstore.Vo.ItemVO;
import org.csu.petstore.Vo.ProductVO;
import org.csu.petstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("index")
    public String index() {
        return "catalog/main";
    }

    @GetMapping("viewCategory")
    public String viewCategory(String categoryId, Model model) {
        CategoryVO catalogVO=catalogService.getCategory(categoryId);
        model.addAttribute("category",catalogVO);
        return "catalog/category";//文件路径
    }

    @GetMapping("viewProduct")
    public String viewProduct(String productId,Model model) {
        ProductVO productVO=catalogService.getProduct(productId);
        model.addAttribute("product",productVO);
        return "catalog/product";
    }

    @GetMapping("viewItem")
    public String viewItem(String itemId,Model model) {
        ItemVO itemVO=catalogService.getItem(itemId);
        model.addAttribute("item",itemVO);
        return "catalog/item";
    }
}
