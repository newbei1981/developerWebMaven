package com.newbei.developerInfoWeb.repository;

import com.newbei.developerInfoWeb.model.Account;
import com.newbei.developerInfoWeb.model.AccountStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateAccountRepositoryImpl implements AccountRepository{

    private static SessionFactory sessionFactory = null;
    private Session session = null;
    private Transaction transaction = null;

    private void initHibernate(){
        sessionFactory =   new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }
    private void closeHibernate(){
        if (transaction!=null) transaction.commit();
        if (session!=null) session.close();
        if (sessionFactory!=null) sessionFactory.close();
    }

    @Override
    public Account save(Account account) {
        initHibernate();
            Long accountId = (long)session.save(account);
            Account saveAccount = session.get(Account.class, accountId);
        closeHibernate();
        return saveAccount;
    }

    @Override
    public Account update(Account account) {
        initHibernate();
           session.update(account);
        closeHibernate();
        return account;
    }

    @Override
    public List<Account> getAll() {
        initHibernate();
            List accounts = session.createQuery("FROM Account").list();
        closeHibernate();
        return accounts;
    }

    @Override
    public Account getById(Long aLong) {
        initHibernate();
            Account account = session.get(Account.class, aLong);
        closeHibernate();
        return account;
    }

    @Override
    public Account deleteById(Long aLong) {
        initHibernate();
            Account account = session.get(Account.class, aLong);
        //  If, according to the logic of the program, you need to permanently delete entity
        //  session.delete(account);
            account.setLogin("321");
            account.setPassword("987");
            account.setAccountStatus(AccountStatus.DELETED);
           session.update(account);
        closeHibernate();
        return account;
    }
}