### Spring Dependency Injection

객체를 만드는데 있어서
1. new로 생성
2. 외부에서 만들어서 주입
하는 방식이 있습니다.

프로젝트부터 생성하겠습니다.
( 초반이니 상세한 사진을 달아놓겠습니다. )

```
프로젝트명 : STS02_DI1_bean
인터페이스 : MessageBean
```
<br>
![image1](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/1%233.png?raw=true)<br>
![image2](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/2%233.png?raw=true)<br>



혹시이과정에서 저처럼 MANIFEST만 보이시는분들은
만드신 프로젝트지우시고 아래처럼 MVC 프로젝트로 만들어 주세요<br>
![image3](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/3%233.png?raw=true)<br>

![image4](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/4%233.png?raw=true)<br>

![image5](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/5%233.png?raw=true)<br>
![image6](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/6%233.png?raw=true)<br>

그러면 아래처럼 여러폴더들이 잘 load 될것입니다.<br>
![image7](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/7%233.png?raw=true)<br>

![image8](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/8%233.png?raw=true)<br>
```java
package com.lec.spring;

public interface MessageBean {

	public abstract void sayHello();

}
```


![image10](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/10%233.png?raw=true)<br>
![image11](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/11%233.png?raw=true)<br>
![image12](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/12%233.png?raw=true)<br>
![image13](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/13%233.png?raw=true)<br>

```java
package com.lec.spring;

public class MessageKor implements MessageBean {
	String msgKor = "안녕하세요";


	public MessageKor() {
		System.out.println("MessageKor() 생성됨");
	}

	@Override
	public void sayHello() {
		System.out.println(msgKor);
	}

}

```










![image14](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/14%233.png?raw=true)<br>
```java
package com.lec.spring.di01;

import com.lec.spring.MessageBean;
import com.lec.spring.MessageEng;
import com.lec.spring.MessageKor;

public class DIMain01 {

	public static void main(String[] args) {
		System.out.println("Main 시작");

		// 기존 new로 객체생성 방식
		MessageBean msg = null;
		msg = new MessageKor(); 	// 다형성 때문에가능!
		msg.sayHello();


		// DIMain01에서 MessageBean객체가 실제로 존재하지 않는다면,
		// 사용불가능 하므로 DIMain01은 MessageBean객체를 의존하고 있다고 말한다.( 의존관계 )

		// 그런데 만약,
		// 객체의 설계가 변경된다면?
		// msg에 담을 객체가 변경되야한다.
		// 즉 main이 "의존"하는 MessageBean 객체가 변경되어야 한다면?
		// 아래처럼 해야하고, 결국 Main을 다시 컴파일해야  함.
		// ( main을 손댈수 밖에없음.. )
		msg = new MessageEng();
		msg.sayHello();

		System.out.println("Main 종료");
	}
}

```
![image15](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/15%233.png?raw=true)<br>
```java
package com.lec.spring;

public class MessageEng implements MessageBean {

	// 설계가 변경된 영어 버전
	String msgEng = "Good Morning";

	// 생성자: 언제 생성되는지 예의주시
	public MessageEng() {System.out.println("MessageEng() 생성됨");}

	@Override
	public void sayHello() {
		System.out.println(msgEng);
	}

}

```
![image16](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/16%233.png?raw=true)<br>
![image17](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/17%233.png?raw=true)<br>
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="messageBean" class="com.lec.spring.MessageKor"/>
	<!-- com.lec.spring.MessageKor messageBean = new com.lec.spring.MessageKor(); 과 같은결과 -->

</beans>

```
![image18](https://github.com/hyunjunhwang1994/hyunjunhwang1994.github.io/blob/master/_posts/spring/image/18%233.png?raw=true)<br>



```java
package com.lec.spring.di02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.lec.spring.MessageBean;

public class DIMain02 {

	public static void main(String[] args) {
		System.out.println("Main 시작");

		// 외부에서 객체를 만들어 주입하는 방식

		// 스프링 설정파일 (appCtx.xml) resource 명시
		String confingLocation = "classpath:appCtx.xml";

		// 객체가 생성될 외부 컨테이너를 생성한다. ( 이때 Bean(객체)가 생성된다. )
		AbstractApplicationContext ctx =
				new GenericXmlApplicationContext(confingLocation);
		System.out.println("Application Context 컨테이너 생성 완료");
		System.out.println();

		// 외부(컨테이너)에서 만든 객체를 주입(injection)함.
		MessageBean msg = ctx.getBean("messageBean", MessageBean.class);  
			                       // ↑bean의 ID    ,  ↑bean의 타입   
		msg.sayHello();


		ctx.close(); // 컨테이너는 사용후 자원반납을 꼭 해줘야한다!


		System.out.println("Main 종료");
	}

}

```
```xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<!-- 설계 변경시 아래처럼 이름만 바꿔주면된다!! -->
	<bean id="messageBean" class="com.lec.spring.MessageEng"/>




</beans>
```
