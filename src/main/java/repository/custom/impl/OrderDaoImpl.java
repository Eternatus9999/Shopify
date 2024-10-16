package repository.custom.impl;

import entity.OrderDetailsEntity;
import entity.OrderEntity;
import org.hibernate.Session;
import repository.custom.OrderDao;
import util.HibernateUtil;

import java.util.List;


public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean save(OrderEntity orderEntity) {
        Session session = HibernateUtil.getOrderSession();
        session.beginTransaction();
        session.persist(orderEntity);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getOrderSession();
        session.beginTransaction();
        session.remove(searchdetails(id));
        session.getTransaction().commit();
        return false;
    }

    @Override
    public List<OrderEntity> getAll() {
        Session session = HibernateUtil.getOrderSession();
        return session.createQuery("from OrderEntity",OrderEntity.class).list();
    }

    @Override
    public boolean update(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public OrderEntity search(String id) {
        Session session = HibernateUtil.getOrderSession();
        return session.get(OrderEntity.class,id);
    }


    public OrderEntity searchdetails(String id) {
        Session session = HibernateUtil.getOrderSession();
        return session.get(OrderEntity.class,id);
    }

    public boolean savedetails(OrderDetailsEntity entity){
        Session session = HibernateUtil.getOrderDetailsSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        return true;
    }

    public List<OrderDetailsEntity> deletedetails(String id){
        Session session = HibernateUtil.getOrderDetailsSession();
        session.beginTransaction();
        List<OrderDetailsEntity> list = session.createQuery("from OrderDetailsEntity Where orid = \""+id+"\"",OrderDetailsEntity.class).list();
        list.forEach(entity -> {
            session.remove(entity);
        });
        session.getTransaction().commit();
        return list;
    }


}
