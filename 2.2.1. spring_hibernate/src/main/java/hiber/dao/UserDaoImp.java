package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
    private SessionFactory session;

    @Override
    public void add(User user) {
        session.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = session.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUserByCar(String model, int series) {
        TypedQuery<User> query = session.getCurrentSession()
                .createQuery("from User as u where id = (select id from Car where model= :model and series = :series)");
        query.setParameter("model", model).setParameter("series", series);
        return query.getResultList();
    }


}
