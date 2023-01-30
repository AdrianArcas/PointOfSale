package com.example.pointofsale.ejb;

import com.example.pointofsale.entities.Account;
import com.example.pointofsale.entities.Category;
import com.example.pointofsale.entities.UserGroup;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jdk.internal.net.http.common.Log;
import sun.jvm.hotspot.gc.shared.GCName;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class AccountBean {
    @Inject
    PasswordBean passwordBean;
    @PersistenceContext
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(AccountBean.class.getName());

    public void createUser(String username, String email, String password, String userGroup) {
        LOG.info("createUser");
        Account newAccount = new Account();
        newAccount.setUsername(username);
        newAccount.setEmail(email);
        newAccount.setPassword(passwordBean.convertToSha256(password));
        newAccount.setIs_active(false);
        entityManager.persist(newAccount);
        assignGroupToUser(username, userGroup);

    }

    private void assignGroupToUser(String username, String userGroup) {
        LOG.info("assignGroupToUser");
        UserGroup tempUserGroup = new UserGroup();
        tempUserGroup.setUsername(username);
        tempUserGroup.setUserGroup(userGroup);
        entityManager.persist(tempUserGroup);
    }


    public boolean isActiveByUsername(String username) {
        LOG.info("isActiveByUsername");
        List<Account> Accounts = entityManager.createQuery("SELECT a FROM Account a where a.username = :username", Account.class)
                .setParameter("username", username)
                .getResultList();
        return  Accounts.get(0).getIs_active();
    }
}
