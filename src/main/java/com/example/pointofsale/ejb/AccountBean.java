package com.example.pointofsale.ejb;

import com.example.pointofsale.common.AccountDto;
import com.example.pointofsale.common.AccountPhotoDto;
import com.example.pointofsale.common.ProductPhotoDto;
import com.example.pointofsale.entities.*;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
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
    public List<AccountDto> findAllAccounts(){
        LOG.info("findAllAccounts");
        try{
            TypedQuery<Account> typedQuery = entityManager.createQuery("SELECT a FROM Account a", Account.class);
            List<Account> accounts = typedQuery.getResultList();
            return copyAccountsToDto(accounts);
        }catch(Exception ex){
            throw new EJBException(ex);
        }
    }

    private List<AccountDto> copyAccountsToDto(List<Account> accounts) {
        List<AccountDto> accountDtoList = new ArrayList<>();

        for(Account a : accounts){
            accountDtoList.add(new AccountDto(a.getAccount_id(), a.getUsername(), a.getEmail(), a.getPassword(), a.getIs_active()));
        }
        return accountDtoList;
    }
    public AccountDto findAccountById(Long account_id) {
        LOG.info("findAccountById");

        Account account = entityManager.find(Account.class, account_id);

        AccountDto accountDto = new AccountDto(account.getAccount_id(),account.getUsername(),account.getEmail(),account.getPassword(),account.getIs_active());

        return accountDto;
    }
    public void updateAccount(Long account_id, String username, String email, String password, String userGroup, Boolean is_active) {
        LOG.info("updateAccount");
        Account account = entityManager.find(Account.class, account_id);
        account.setUsername(username);
        account.setEmail(email);
        account.setPassword(passwordBean.convertToSha256(password));
        account.setIs_active(is_active);
        assignGroupToUser(username, userGroup);
    }
    public void deleteAccountById(Long account_id) {
        LOG.info("deleteAccountById");
        Account account = entityManager.find(Account.class, account_id);
        entityManager.remove(account);
    }
    public void addPhotoToAccount(Long accountId, String filename, String fileType, byte[] fileContent) {

        LOG.info("addPhotoToAccount");
        AccountPhoto photo = new AccountPhoto();
        photo.setFilename(filename);
        photo.setFileType(fileType);
        photo.setFileContent(fileContent);
        Account account = entityManager.find(Account.class, accountId);
        if (account.getPhoto() != null) {
            entityManager.remove(account.getPhoto());
        }
        account.setPhoto(photo);
        photo.setAccount(account);
        entityManager.persist(photo);
    }
    public AccountPhotoDto findPhotoByAccountId(Integer accountId) {
        List<AccountPhoto> photos = entityManager
                .createQuery("SELECT p FROM AccountPhoto p where p.account.account_id = :id", AccountPhoto.class)
                .setParameter("id", accountId)
                .getResultList();
        if (photos.isEmpty()) {
            return null;
        }
        AccountPhoto photo = photos.get(0); // the first element
        return new AccountPhotoDto(photo.getId(), photo.getFilename(), photo.getFileType(),
                photo.getFileContent());
    }

    public Long getAccountIdByUsername(String username1) {
        List<Account> Accounts = entityManager.createQuery("SELECT a FROM Account a where a.username = :username" , Account.class)
                .setParameter("username", username1)
                .getResultList();
        return  Accounts.get(0).getAccount_id();
    }

    public String getAccountUsernameByID(Long id) {
        LOG.info("getAccountUsernameByID");
        List<Account> Accounts = entityManager.createQuery("SELECT a FROM Account a where a.account_id = :id", Account.class)
                .setParameter("id", id)
                .getResultList();
        return  Accounts.get(0).getUsername();
    }
}
