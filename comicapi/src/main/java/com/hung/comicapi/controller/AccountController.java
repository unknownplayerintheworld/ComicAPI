package com.hung.comicapi.controller;

import com.hung.comicapi.exception.AccountAlreadyExistsException;
import com.hung.comicapi.model.Account;
import com.hung.comicapi.model.DataJSON;
import com.hung.comicapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/Accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/insert")
    ResponseEntity<DataJSON> registerAccount(@RequestBody Account account){
        try{
            accountService.registerUserAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Register Account successfully",account.getUsername())
            );
        }
        catch (AccountAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new DataJSON(false,"Account already exists",account.getUsername())
            );
        }
        catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @PostMapping("/update")
    ResponseEntity<DataJSON> updateAccount(@RequestBody HashMap<String,String> hashMap){
        try{
            accountService.updateUserAccount(hashMap.get("username"),hashMap.get("oldpassword"),hashMap.get("newpassword"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Update Account successfully",hashMap.get("username"))
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(false,e.getMessage(),"")
            );
        }
    }
    @GetMapping("")
    ResponseEntity<DataJSON> getAllAccounts(){
        try {
            List<Account> account = accountService.getAllAccount();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"get all Accounts successfully",account)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new DataJSON(false,"Failed to get all Accounts","")
            );
        }
    }
    @PostMapping("/login")
    ResponseEntity<DataJSON> login(@RequestBody Account account){
        try{
            List<Account> accounts = accountService.authenticate(account.getUsername(), account.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataJSON(true,"Login Successful",accounts)
            );
        }catch (UsernameNotFoundException e) {
            // Xử lý khi không tìm thấy tài khoản
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DataJSON(false, "User not found", Collections.emptyList()));
        } catch (BadCredentialsException e) {
            // Xử lý khi sai thông tin đăng nhập
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new DataJSON(false, "Invalid credentials", Collections.emptyList()));
        } catch (Exception e) {
            // Xử lý các lỗi khác
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new DataJSON(false, e.getMessage(), Collections.emptyList()));
        }
    }
}
