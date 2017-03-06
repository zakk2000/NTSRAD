package com.nts.rad.templatecallback;


public interface LineCallback<T> {

	T doSomethingWithLine(String line, T value);

}
