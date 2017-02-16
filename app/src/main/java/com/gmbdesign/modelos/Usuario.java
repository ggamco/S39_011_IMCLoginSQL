package com.gmbdesign.modelos;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by ggamboa on 14/2/17.
 */

public class Usuario {

    private int idUsuario;
    private String email, password;

    public Usuario(String email, String password) {
        String passEncrypted = new String(Hex.encodeHex(DigestUtils.sha1(password)));

        this.email = email;
        this.password = passEncrypted;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new String(Hex.encodeHex(DigestUtils.sha1(password)));
    }
}
