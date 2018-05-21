package learn.web.controller;


import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learn.web.service.IStudentService;

@RestController
@RequestMapping
public class Home
{
	private static Logger logger=LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	@Resource(name="studentService")
	private IStudentService studentService;
	@RequestMapping("/")
	public String index()
	{
		
		logger.info("测试成功");
		return "hello world";
	}
}
