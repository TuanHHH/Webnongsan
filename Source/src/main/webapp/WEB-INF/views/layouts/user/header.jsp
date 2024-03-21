<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layouts/user/common.jsp"%>

<!-- Page Preloder -->
<div id="preloder">
	<div class="loader"></div>
</div>

<!-- Humberger Begin -->
<div class="humberger__menu__overlay"></div>
<div class="humberger__menu__wrapper">
	<div class="humberger__menu__logo">
		<a href="<c:url value="/assets/user/#"/>"><img
			src="<c:url value="/assets/user/img/logo.png"/>" alt=""></a>
	</div>
	<div class="humberger__menu__cart">
		<ul>
			<li><a href="<c:url value="/assets/user/#"/>"><i
					class="fa fa-heart"></i> <span>
					<c:if test="${not empty userInfo }">
						${wishlist.size() }
					</c:if>
					</span></a></li>
			<li><a href="<c:url value="/assets/user/#"/>"><i
					class="fa fa-shopping-bag"></i> <span> <c:if
							test="${not empty userInfo }">
						${carts.size() }
					</c:if>
				</span></a></li>
		</ul>
		<div class="header__cart__price">
			total: <span> <c:if test="${not empty userInfo }">
						${totalPrice }
					</c:if>
			</span>
		</div>
	</div>
	<div class="humberger__menu__widget">
		<div class="header__top__right__language">
			<img src="<c:url value="/assets/user/img/language.png"/>" alt="">
			<div>English</div>
			<span class="arrow_carrot-down"></span>
			<ul>
				<li><a href="<c:url value="/assets/user/#"/>">Spanis</a></li>
				<li><a href="<c:url value="/assets/user/#"/>">English</a></li>
			</ul>
		</div>
		<div class="header__top__right__auth">
			<a href="<c:url value="/login.htm"/>"><i class="fa fa-user"></i>
				Login</a>
		</div>
	</div>
	<nav class="humberger__menu__nav mobile-menu">
		<ul>
			<li class="active"><a href="<c:url value="/index.htm"/>">Home</a></li>
			<li><a href="<c:url value="/product.htm"/>">Shop</a></li>
			<li><a href="<c:url value="/blog.htm"/>">Blog</a></li>
			<li><a href="<c:url value="/contact.htm"/>">Contact</a></li>
			<c:if test="${empty userInfo }">
				<li><a href="<c:url value="/login.htm"/>">Login</a></li>
			</c:if>
			<c:if test="${not empty userInfo }">
				<li><a href="<c:url value="/logout.htm"/>">Logout</a></li>
			</c:if>
		</ul>
	</nav>
	<div id="mobile-menu-wrap"></div>
</div>
<!-- Humberger End -->

<!-- Header Section Begin -->
<header class="header">
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="header__logo">
					<a href="<c:url value="/index.htm"/>"><img
						src="<c:url value="/assets/user/img/logo.png"/>" alt=""></a>
				</div>
			</div>
			<div class="col-lg-6">
				<nav class="header__menu">
					<ul>
						<li class="active"><a href="<c:url value="/index.htm"/>">Home</a></li>
						<li><a href="<c:url value="/product.htm"/>">Shop</a></li>
						<li><a href="<c:url value="/blog.htm"/>">Blog</a></li>
						<li><a href="<c:url value="/contact.htm"/>">Contact</a></li>
						<c:if test="${empty userInfo }">
							<li><a href="<c:url value="/login.htm"/>">Login</a></li>
						</c:if>
						<c:if test="${not empty userInfo }">
							<li><a href="<c:url value="/logout.htm"/>">Logout</a></li>
						</c:if>

					</ul>

				</nav>
			</div>
			<div class="col-lg-3">
				<div class="header__cart">
					<ul>

						<li style="margin-right: 5px"><a
							href="<c:url value="/login.htm"/>"
							style="color: #000000; line-height: 13px;"><i
								class="fa fa-user"></i> <c:if test="${empty userInfo }">
							Login
						</c:if> <c:if test="${not empty userInfo }">
							${userInfo.getName() }
						</c:if> </a></li>


						<li><a href="<c:url value="/wishlist.htm"/>"><i
								class="fa fa-heart"></i> <span>
									<c:if test="${not empty userInfo }">${wishlist.size() }</c:if>
								</span></a></li>
						<li><a href="<c:url value="/cart.htm"/>"><i
								class="fa fa-shopping-bag"></i> <span> <c:if
										test="${not empty userInfo }">${carts.size() }</c:if>
							</span></a></li>

					</ul>
					<div class="header__cart__price">
						total: <span><c:if test="${not empty userInfo }">${totalPrice }</c:if></span>
					</div>
				</div>
			</div>
		</div>
		<div class="humberger__open">
			<i class="fa fa-bars"></i>
		</div>
	</div>
</header>
<!-- Header Section End -->
