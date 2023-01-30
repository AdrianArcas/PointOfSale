package com.example.pointofsale.ejb;

import com.example.pointofsale.common.NotificationDto;
import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.entities.Account;
import com.example.pointofsale.entities.NotificationAccountsDirector;
import com.example.pointofsale.entities.Product;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class NotificationBean {

    @Inject
    AccountBean accountBean;

    @PersistenceContext
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(NotificationBean.class.getName());

    public void createNotification(Long whoAddedOrDeletedAccID , Long AddedOrDeletedAccID, String action) {
        LOG.info("createNotification");
        NotificationAccountsDirector newNotificatioon=new NotificationAccountsDirector();
        newNotificatioon.setModified_or_added_account_id(whoAddedOrDeletedAccID);
        newNotificatioon.setModified_or_added_account_id(AddedOrDeletedAccID);
        newNotificatioon.setAction(action);
        entityManager.persist(newNotificatioon);
    }
    public List<NotificationDto> findAllNotifications(){
        LOG.info("findAllNotofications");
        try{
            TypedQuery<NotificationAccountsDirector> typedQuery = entityManager.createQuery("SELECT n FROM NotificationAccountsDirector n", NotificationAccountsDirector.class);
            List<NotificationAccountsDirector> notifications = typedQuery.getResultList();
            return copyNotificationsToDto(notifications);

        }catch(Exception ex){
            throw new EJBException(ex);
        }
    }
    private List<NotificationDto> copyNotificationsToDto(List<NotificationAccountsDirector> notifications) {
        List<NotificationDto> notificationDtoList = new ArrayList<>();

        for(NotificationAccountsDirector n : notifications){
            notificationDtoList.add(new NotificationDto(n.getNotification_id(), accountBean.getAccountUsernameByID(n.getModified_or_added_account_id()), accountBean.getAccountUsernameByID(n.getWho_modified_or_added_id()),n.getAction()));

        }
        return notificationDtoList;
    }
}
