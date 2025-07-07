/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.common.util.validation;

/**
 * Generic interface for validators.
 *
 * @param <T> the type of object to validate
 */
@FunctionalInterface
public interface Validator<T> {

    /**
     * Validates the provided object.
     *
     * @param value the object to validate
     * @throws IllegalArgumentException if the object is invalid
     */
    void validate(T value) throws IllegalArgumentException;
}