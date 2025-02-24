package com.Dao;

import com.helper.FactoryProvider;
import com.model.Reminder;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class ReminderDAOImpl implements ReminderDAO {

    @Override
    public void saveReminder(Reminder reminder) {
        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(reminder);
        tx.commit();
        session.close();
    }

    @Override
    public List<Reminder> getRemindersForUser(String content_title) {
        Session session = FactoryProvider.getFactory().openSession();
        List<Reminder> reminders = session.createQuery("FROM Reminder WHERE content_title = :content_title", Reminder.class)
                                           .setParameter("content_title", content_title)
                                           .list();
        session.close();
        return reminders;
    }

    public List<Reminder> getDueReminders(String email) {
        Session session = FactoryProvider.getFactory().openSession();
            String hql = "FROM Reminder r WHERE r.email = :email AND r.reminderDateTime <= :currentDate";
            return session.createQuery(hql, Reminder.class)
                    .setParameter("email", email)
                    .setParameter("currentDate", new Date())
                    .getResultList();

    }

    public List<Reminder> getFutureReminders(String email) {
        Session session = FactoryProvider.getFactory().openSession();
            String hql = "FROM Reminder r WHERE r.email = :email AND r.reminderDateTime > :currentDate";
            return session.createQuery(hql, Reminder.class)
                    .setParameter("email", email)
                    .setParameter("currentDate", new Date())
                    .getResultList();

    }
}