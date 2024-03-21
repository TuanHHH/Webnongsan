package ecofarm.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ecofarm.DAOImpl.AccountDAOImpl;
import ecofarm.DAOImpl.CartDAOImpl;
import ecofarm.entity.Account;
import ecofarm.entity.Cart;
import ecofarm.entity.Wishlist;

@Controller
public class CartController {
	private AccountDAOImpl accountDAO = new AccountDAOImpl();
	private CartDAOImpl cartDAO = new CartDAOImpl();
	@RequestMapping("cart")
	public String Index(HttpServletRequest request,HttpSession session,
			@CookieValue(value = "userEmail",defaultValue = "",required = false) String userEmail) {
		try {
			if (userEmail != "") {
				Account account = accountDAO.getAccountByEmail(userEmail);
				List<Cart> list = cartDAO.getCartByAccountID(account.getAccountId());
				session.setAttribute("carts", list);
				session.setAttribute("totalPrice", cartDAO.getTotalPrice(list));
				return "user/cart";
			}else {
				return "redirect:/login.htm";
			}
		}catch (Exception e) {
			// TODO: handle exception
			return "redirect:/login.htm";
		}
	}
	
	
	@RequestMapping("/AddCart")
	public String AddToCart(@RequestParam(value = "productId",required = true) int productId,
			@CookieValue(value = "userEmail",defaultValue = "",required = false) String userEmail,
			HttpSession session, HttpServletRequest request) {
		if (userEmail.equals("")) {
			request.setAttribute("user", new Account());
			return "user/login";
		}	
		Account account = accountDAO.getAccountByEmail(userEmail);
		cartDAO.addToCart(productId,account.getAccountId());
		
		//Thay đổi số lượng của cart mỗi khi tương tác với addCart
		List<Cart> list = cartDAO.getCartByAccountID(account.getAccountId());
		session.setAttribute("carts", list);
		return "redirect:"+request.getHeader("Referer");
	}
	
	@RequestMapping("/DeleteCart")
	public String DeleteFromCart(@RequestParam(value = "productId",required = true) int productId,
			@CookieValue(value = "userEmail",defaultValue = "",required = false) String userEmail,
			HttpSession session, HttpServletRequest request) {
		Account account = accountDAO.getAccountByEmail(userEmail);
		cartDAO.deleteCart(productId, account.getAccountId());
		//Tự động trừ đi số lượng của cart mỗi khi tương tác với delete vì redirect to cart
		return "redirect:"+request.getHeader("Referer");
	}
	@RequestMapping("/EditCart")
	public String EditCartQnt(@RequestParam(value = "productId",required = true) int productId,
			@RequestParam(value="qty",required = true) int quantity,
			@CookieValue(value = "userEmail",defaultValue = "",required = false) String userEmail,
			HttpSession session, HttpServletRequest request) {
		
		Account account = accountDAO.getAccountByEmail(userEmail);
		cartDAO.editCart(productId, account.getAccountId(), quantity);
		return "redirect:"+request.getHeader("Referer");
	}
}
