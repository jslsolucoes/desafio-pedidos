package br.com.bluesoft.desafio.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Responses {

    private Responses() {
	throw new IllegalStateException("You should not call this constructor");
    }

    public static <T> ResponseEntity<T> notFound() {
	return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
    }

    public static <T> ResponseEntity<T> ok(T value) {
	return new ResponseEntity<T>(value, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> ok() {
	return new ResponseEntity<T>(HttpStatus.OK);
    }
}
