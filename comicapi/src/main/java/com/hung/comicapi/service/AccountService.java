package com.hung.comicapi.service;

import com.hung.comicapi.exception.AccountAlreadyExistsException;
import com.hung.comicapi.model.Account;
import com.hung.comicapi.model.CustomUserDetails;
import com.hung.comicapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service

public class AccountService implements UserDetailsService{
    @Autowired
    private AccountRepository accountrepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Account> byUsername = accountrepo.findByUsername(username);
        Account account = byUsername.stream().findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User detail not found for username = " + username));

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(account.getRoles()));

        // Tạo một custom UserDetails object với cả id
        CustomUserDetails userDetails = new CustomUserDetails(account.getId(), account.getUsername(), account.getPassword(), authorities);

        return userDetails;
    }


    public List<Account> getAllAccount(){
        try{
           return accountrepo.findAll();
        }
        catch(Exception e){
            throw new RuntimeException("Failed to get all accounts");
        }
    }
    public void registerUserAccount(Account account){
        try {
            if (account == null) {
                throw new UsernameNotFoundException("User cannot add because of empty username or password");
            } else {
                List<Account> foundAccounts = accountrepo.findByUsername(account.getUsername().trim());
                    if (foundAccounts.size() > 0) {
                        throw new AccountAlreadyExistsException(account.getUsername());
                    } else {
                        account.setPassword(passwordEncoder.encode(account.getPassword()));
                        // roles auto là user do app chỉ đơn giản là đọc truyện nên ko cần phân quyền
                        account.setRoles("user");
                        accountrepo.save(account);
                    }
            }
        }
        catch (AccountAlreadyExistsException e){
            throw e;
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public void updateUserAccount(String username, String oldPassword, String newPassword){
        try {
            Optional<Account> optionalAccount = accountrepo.findOneByUsername(username);
            Account account = optionalAccount.orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Kiểm tra xem mật khẩu cũ có khớp không
            if (!passwordEncoder.matches(oldPassword, account.getPassword())) {
                throw new BadCredentialsException("Mật khẩu cũ không đúng");
            }

            // Cập nhật mật khẩu mới và lưu vào cơ sở dữ liệu
            account.setPassword(passwordEncoder.encode(newPassword));
            accountrepo.save(account);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Account> accountOptional = accountrepo.findOneByUsername(username);
//        Account account = accountOptional.orElseThrow(()-> new UsernameNotFoundException("Account not found"));
//        return
//    }
    public List<Account> authenticate(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            Account account = new Account();
            account.setId(((CustomUserDetails) userDetails).getId());
            account.setUsername(userDetails.getUsername());
            account.setPassword(userDetails.getPassword());
            // Mật khẩu khớp, có thể xử lý thêm logic tại đây nếu cần
            return Collections.singletonList(account);
        } else {
            throw new BadCredentialsException("Sai mật khẩu hoặc tài khoản");
        }
    }
}
