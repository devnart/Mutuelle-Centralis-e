package com.example.m2.interfaces;

import java.io.IOException;

public interface LoginInterface {

    public void checkLogin() throws IOException;

    public Boolean userLogin(String email, String password);
}
