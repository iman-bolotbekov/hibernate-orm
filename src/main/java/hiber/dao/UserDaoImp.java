package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public Optional<User> getUserByCarModelAndCarSeries(String model, Integer series) {
      Session session = sessionFactory.getCurrentSession();
      Query<User> query = session.createQuery("select u from User u where u.car.series = :series and u.car.model = :model", User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      List<User> users = query.getResultList();
      return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
   }

}
