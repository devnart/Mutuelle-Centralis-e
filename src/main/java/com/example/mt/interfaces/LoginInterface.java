package com.example.mt.interfaces;

import java.io.IOException;

public interface LoginInterface {

    public void checkLogin() throws IOException;

    public Boolean userLogin(String email, String password);
}
