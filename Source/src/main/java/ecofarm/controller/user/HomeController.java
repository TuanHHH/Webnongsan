package ecofarm.controller.user;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecofarm.DAOImpl.AccountDAOImpl;
import ecofarm.DAOImpl.CartDAOImpl;
import ecofarm.DAOImpl.CategoryDAOImpl;
import ecofarm.DAOImpl.ProductDAOImpl;
import ecofarm.entity.Account;
import ecofarm.entity.Cart;
import ecofarm.entity.Wishlist;
import ecofarm.DAOImpl.WishlistDAOImpl;

@Controller
public class HomeController {

	private CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
	private ProductDAOImpl productDAO = new ProductDAOImpl();
	private AccountDAOImpl accountDAO = new AccountDAOImpl();
	
	//Lấy số lượng cho cart, và wishlist khi có thông tin đăng nhập
	private WishlistDAOImpl wishlistDAO = new WishlistDAOImpl();
	private CartDAOImpl cartDAO = new CartDAOImpl();
	
	@RequestMapping(value={"/index"},method=RequestMethod.GET)
	public String Index(@CookieValue(value = "userEmail",defaultValue = "",required = false) String userEmail, 
			HttpServletRequest request,HttpSession session) {
		if(userEmail != "") {
			session.setAttribute("userInfo", accountDAO.getAccountByEmail(userEmail));
			try {
//			Dùng cookie để đăng nhập vào tài khoản email
			Account account = accountDAO.getAccountByEmail(userEmail);
//			Lấy số lượng wishlist khi có thông tin đăng nhập
			List<Wishlist> wishlist = wishlistDAO.getWishlistByAccountID(account.getAccountId());
			session.setAttribute("wishlist", wishlist);
			
//			Lấy số lượng cart khi có thông tin đăng nhập
			List<Cart> cart = cartDAO.getCartByAccountID(account.getAccountId());
			session.setAttribute("carts", cart);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		request.setAttribute("categories", categoryDAO.getAllCategories());
		request.setAttribute("latestProducts",productDAO.getLatestProduct());
		request.setAttribute("products",productDAO.getAllProducts());
		request.setAttribute("reviewProducts", productDAO.getReviewProduct());
		request.setAttribute("ratedProducts", productDAO.getRatedProduct());
		return "user/index";
	}
}
