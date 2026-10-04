package application.controller;

import application.domain.models.DigitalProduct;
import application.domain.models.PhysicalProduct;
import application.domain.models.ProductVariant;
import application.service.CatalogService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CatalogService catalogService;

    public ProductController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping("/physical")
    public PhysicalProduct registerPhysical(@RequestBody Map<String, Object> body) {
        return catalogService.registerPhysicalProduct(
                Long.valueOf(body.get("sellerId").toString()),
                body.get("name").toString(),
                new BigDecimal(body.get("price").toString()),
                Double.parseDouble(body.get("weight").toString()));
    }

    @PostMapping("/digital")
    public DigitalProduct registerDigital(@RequestBody Map<String, Object> body) {
        return catalogService.registerDigitalProduct(
                Long.valueOf(body.get("sellerId").toString()),
                body.get("name").toString(),
                new BigDecimal(body.get("price").toString()),
                body.get("fileFormat").toString());
    }

    @PostMapping("/{id}/variants")
    public void addVariant(@PathVariable Long id, @RequestBody Map<String, String> body) {
        catalogService.addVariant(id, new ProductVariant(body.get("name"), body.get("value"), body.get("sku")));
    }
}
