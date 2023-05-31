package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

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
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> UserWithCar(String model, int series) {
        return sessionFactory.openSession().createQuery("from User WHERE car.model = :model and car.series = :series")
                                .setParameter("model",model).setParameter("series",series).list();
    }

    //("from User user inner join user.car WHERE user.car.model = :model and user.car.series = :series")
    //                .setParameter("model",model).setParameter("series",series)


}
