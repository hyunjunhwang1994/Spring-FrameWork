아래의 방법처럼 사용하는지는 모르겠으나 
@RequestMapping URL주소에 대해서 삽질중에 정리하고자 글을 씀.

```java

// 정상적인 HOME URL 주소로 Request가 올때.
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
// /** 를 사용할 경우, /...  ,  /.../...  , /.../.../.. 등 모두 적용됨!
// 이상한 주소로 Request가 온 경우 에러 페이ㅏ지
	@RequestMapping("/**")
	public String error() {
		
		return "errorPage";
	}
	
// 위처럼 설정해놓아도, 아래처럼 직접적으로 명시한 RequestMapping이 우선적으로 동작하므로
// /member/signIn 으로 Request시 아래의 Handler가 동작함!
	@RequestMapping("/member/signIn")
	public ModelAndView signIn(String id, String pw) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("id", id);
		mv.addObject("pw", pw);
		
		mv.setViewName("member/signIn");
		
		return mv;
	}
```
