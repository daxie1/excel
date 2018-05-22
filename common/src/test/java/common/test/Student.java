package common.test;

import java.util.Date;

import common.util.Excel;

public class Student
{
	@Excel(name="学号",order=1)
	private String identify;
	@Excel(name="姓名",order=2)
	private String name;
	@Excel(name="年龄",order=3)
	private Integer age;
	@Excel(name="入学日期",order=4)
	private Date startDate;
	public String getIdentify()
	{
		return identify;
	}
	public void setIdentify(String identify)
	{
		this.identify = identify;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public Date getStartDate()
	{
		return startDate;
	}
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
}
