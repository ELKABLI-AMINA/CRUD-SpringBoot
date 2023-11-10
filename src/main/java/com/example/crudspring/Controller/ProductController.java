package com.example.crudspring.Controller;

import com.example.crudspring.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productServiceImp;

    @GetMapping("/showCreate")
    public String creatProduct(){
        return "showCreate";
    }

}
