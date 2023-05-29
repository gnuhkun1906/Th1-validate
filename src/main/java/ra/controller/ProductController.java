package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Product;
import ra.service.IProductService;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping(value = {"/", "/product"})
    public ModelAndView listProducts(@PageableDefault(sort = "name", size = 3) Pageable pageable) {
        Page<Product> productPage = productService.findAll(pageable);
        ModelAndView mav = new ModelAndView("/product/list");
        mav.addObject("pageProduct", productPage);
        return mav;
    }

    @GetMapping("/create/product")
    public ModelAndView formCreatePro() {
        ModelAndView mav = new ModelAndView("/product/add");
        mav.addObject("createProductForm", new Product());
        return mav;
    }

    @PostMapping("/create/product")
    public String createProduct(@Validated
                                      @ModelAttribute("createProductForm") Product product,
                                      BindingResult bindingResult
    ) {
        if (bindingResult.hasFieldErrors()) {
            return "/product/add";
        }
        productService.save(product);
        return "redirect:/product";
    }


}
