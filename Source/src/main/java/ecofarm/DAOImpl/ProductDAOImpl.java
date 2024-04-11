package ecofarm.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ecofarm.DAO.IProductDAO;
import ecofarm.entity.Category;
import ecofarm.entity.Product;
import ecofarm.utility.HibernateUtil;

public class ProductDAOImpl implements IProductDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction tr = session.beginTransaction();
			String hql = "FROM Product";
			Query query = session.createQuery(hql);
			tr.commit();
			list = query.list();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsByCategoryID(int categoryID) {
		List<Product> list = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction tr = session.beginTransaction();
			if (categoryID == 0) {
				String hql = "FROM Product";
				list = session.createQuery(hql).list();
			} else {
				String hql = "FROM Product WHERE CategoryID = :categoryID";
				Query query = session.createQuery(hql);
				query.setParameter("categoryID", categoryID);
				list = query.list();
			}
			tr.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Product getProductByID(int productID) {
		List<Product> products = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction tr = session.beginTransaction();
			String hql = "FROM Product WHERE ProductID = :productID";
			Query query = session.createQuery(hql);
			query.setParameter("productID", productID);
			products = query.list();
			tr.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}

		if (products.size() > 0) {
			return products.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getLatestProductsByCaID(int categoryID) {
		List<Product> list = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction tr = session.beginTransaction();
			if (categoryID == 0) {
				String hql = "FROM Product ORDER BY PostingDate DESC";
				list = session.createQuery(hql).list();
			} else {
				String hql = "FROM Product WHERE CategoryID = :categoryID ORDER BY PostingDate DESC";
				Query query = session.createQuery(hql);
				query.setParameter("categoryID", categoryID);
				list = query.list();
			}
			tr.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getLatestProduct() {
		List<Product> list = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction tr = session.beginTransaction();
			String hql = "FROM Product ORDER BY PostingDate DESC";
			Query query = session.createQuery(hql);
			list = query.list();
			tr.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getReviewProduct() {
		List<Product> list = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction tr = session.beginTransaction();
			String hql = "FROM Product ORDER BY Reviews DESC";
			list = session.createQuery(hql).list();
			tr.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getRatedProduct() {
		List<Product> list = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction tr = session.beginTransaction();
			String hql = "FROM Product ORDER BY RatingStar DESC";
			list = session.createQuery(hql).list();
			tr.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Product> searchProducts(String likeName) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Transaction t = ss.beginTransaction();
		likeName = (likeName == null) ? "%" : "%" + likeName + "%";
		String hql = "FROM Product WHERE productName LIKE :name";
		Query query = ss.createQuery(hql);
		query.setParameter("name", likeName);
		@SuppressWarnings("unchecked")
		List<Product> list = query.list();
		t.commit();
		ss.close();
		return list;
	}
	
	@Override
	public boolean insertProduct(Product product) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(product);
			t.commit();
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			t.rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteProduct(Product product) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(product);
			t.commit();
			return true;
		} catch (Exception ex) {
			System.out.println("Delete product failed" + ex.getMessage());
			t.rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(product);
			t.commit();
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			t.rollback();
		} finally {
			session.close();
		}
		return false;
	}
}