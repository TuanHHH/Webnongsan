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
import ecofarm.DAOImpl.WishlistDAOImpl;
import ecofarm.entity.Account;
import ecofarm.entity.Cart;
import ecofarm.entity.Wishlist;

@Controller
public class WishlilstController {
	private AccountDAOImpl accountDAO = new AccountDAOImpl();
	private WishlistDAOImpl wishlistDAO = new WishlistDAOImpl();

	@RequestMapping("/wishlist")
	public String Index(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "userEmail", defaultValue = "", required = false) String userEmail) {
		try {
			if (userEmail != "") {
				Account account = accountDAO.getAccountByEmail(userEmail);
				List<Wishlist> list = wishlistDAO.getWishlistByAccountID(account.getAccountId());
				session.setAttribute("wishlist", list);
				return "user/wishlist";
			}else {
				return "redirect:/login.htm";
			}
		} catch (Exception e) {
			return "redirect:/login.htm";
		}
	}

	@RequestMapping("/AddWishlist")
	public String AddToWishlist(@RequestParam(value = "productId", required = true) int productId,
			@CookieValue(value = "userEmail", defaultValue = "", required = false) String userEmail,
			HttpSession session, HttpServletRequest request) {
		if (userEmail.equals("")) {
			request.setAttribute("user", new Account());
			return "user/login";
		}
		Account account = accountDAO.getAccountByEmail(userEmail);
		wishlistDAO.addToWishlist(productId, account.getAccountId());
		
		//Thay đổi thông tin số lượng của wishlist mỗi khi tương tác với wishlist
		List<Wishlist> list = wishlistDAO.getWishlistByAccountID(account.getAccountId());
		session.setAttribute("wishlist", list);
		
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping("/DeleteWishlist")
	public String DeleteFromWishlist(@RequestParam(value = "productId", required = true) int productId,
			@CookieValue(value = "userEmail", defaultValue = "", required = false) String userEmail,
			HttpSession session, HttpServletRequest request) {
		Account account = accountDAO.getAccountByEmail(userEmail);
		wishlistDAO.deleteFromWishlist(productId, account.getAccountId());
		
		//Tự động trừ đi số lượng trong wishlist vì redirect qua wishlist
		return "redirect:" + request.getHeader("Referer");
	}
}
