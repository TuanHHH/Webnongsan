package ecofarm.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ecofarm.DAOImpl.CategoryDAOImpl;
import ecofarm.DAOImpl.PaginateDAOImpl;
import ecofarm.DAOImpl.ProductDAOImpl;
import ecofarm.entity.Product;

@Controller
@RequestMapping("/product")
public class ProductController {
	private ProductDAOImpl productDAO = new ProductDAOImpl();
	private CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
	private PaginateDAOImpl paginateDAO = new PaginateDAOImpl();

	@RequestMapping()
	public ModelAndView Product(@RequestParam(value = "categoryId", required = false, defaultValue = "0") int id,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {
		ModelAndView mv = new ModelAndView();
		List<Product> products = productDAO.getProductsByCategoryID(id);
		mv.addObject("categories", categoryDAO.getAllCategories());
		mv.addObject("productsByCategory", products);
		mv.addObject("latestProducts", productDAO.getLatestProductsByCaID(id));
		mv.addObject("categoryID", id);
		mv.addObject("paginateInfo", paginateDAO.getInfoPaginate(products.size(), 6, currentPage));
		mv.setViewName("user/product");
		return mv;
	}

}