/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee.exception;

/**
 *
 * @author huydao
 */
public class DuplicateEmailException extends Exception{
    public DuplicateEmailException (String message) {
        super(message);
    }
}
