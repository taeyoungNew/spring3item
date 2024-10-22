package com.taeyeong.spring3item.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// 例外をハンドラーするためのクラス
// アクセス側にRESとして例外を返す
@ControllerAdvice
public class ItemNotFoundExceptionControllerAdvice {
	
	@ResponseBody // responseとして返す
	@ExceptionHandler(ItemNotFoundException.class)	//　ハンドリングの対象
	@ResponseStatus(HttpStatus.NOT_FOUND) // 返す時httpの状態
	public String itemNotFoundHandler(ItemNotFoundException ex) {
		return ex.getMessage();
	}
}
