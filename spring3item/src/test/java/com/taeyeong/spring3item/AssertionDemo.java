package com.taeyeong.spring3item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class Person {
	private String firstName;
	private String lastName;
	
	Person() {
		
	}
	
	Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}

class AssertionDemo {



	// 下記のように一つずつ実施すると
	// エラーが複数発生してもい一番最初に発生したエラーしか
	// 表示されない
	// 二つの値が同一なのか検証
//	@Test
//	void standardAssertions() {
//		assertEquals(2, 2);
//		assertEquals(4, 3, "The optional assertion message is now the last parameter.");
//	
//		assertTrue('a' > 'b', () -> "Assertion messages can be lazily evaluated -- "
//                + "to avoid constructing complex messages unnecessarily.");
//	}
	
	// 複数のアサーションを一括実行
//	@Test
//	void groupedAssertions() {
//		// In a grouped assertion all assertions are executed, and any
//        // failures will be reported together.
//		
//		Person person = new Person("John", "Doe");
//		
//		
//		assertAll("person", 
//				() -> assertEquals("John", person.getFirstName()),
//				() -> assertEquals("Doe", person.getLastname())
//				
//		);
//		
//	}
	
	// アサーション内に
	@Test
	void dependentAssertions() {
		Person person = new Person("ミン", "ミン");
		// In a grouped assertion all assertions are executed, and any
        // failures will be reported together.
		
		// 二重でテストを行う場合
		// assertAllの二番目の引数にアロー関数の中にassertメソッドを作成
		// assertAllの中にassertAllを作成可能
		assertAll("properties", 
				() -> {
					String firstName = person.getFirstName();
					assertNotNull(firstName);
					
					// Executed only if the previous assertion is valid
					assertAll("first name", 
							() -> assertTrue(firstName.startsWith("J")),
							() -> assertTrue(firstName.endsWith("n"))
					);
				},
				
				() -> {
					String lastName = person.getLastName();
					
					assertNotNull(lastName);
					
					assertAll("last name", 
							() -> assertTrue(lastName.startsWith("ミ")),
							() -> assertTrue(lastName.endsWith("ン"))
					);
					
				}
		);
	}
	
	@Test
	void exceptionTesting() {
		// Throwable = Java言語のすべてのエラーと例外のスーパー・クラス
		// assertThrows =  例外がスローされるかどうかを検証
		// IllegalArgumentException =  引数が不正であった場合にスローされます。
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			throw new IllegalArgumentException("messages");
		});
		
		// IllegalArgumentExceptionに定義されている引数の内容が"messages"なので
		// エラーが起きる
		assertEquals("message content", exception.getMessage());
	}
	
	

}