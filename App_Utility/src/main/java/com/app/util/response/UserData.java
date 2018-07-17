package com.app.util.response;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.app.util.constant.CommonConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UserData {
	
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@Past
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = CommonConstants.MY_TIME_ZONE)
	@Temporal(TemporalType.DATE)
	private Date dob;

	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * public static void main(String args[]) throws NoSuchFieldException,
	 * SecurityException, IllegalArgumentException, IllegalAccessException {
	 * UserData user = new UserData(); user.test(); }
	 * 
	 * 
	 * public void test() throws NoSuchFieldException, SecurityException,
	 * IllegalArgumentException, IllegalAccessException { Field fieldDOB =
	 * UserData.class.getDeclaredField("dob");
	 * 
	 * //Method method = Class.class.getDeclaredMethod(ANNOTATION_METHOD, null);
	 * //method.setAccessible(true); //Field annotations =
	 * annotationData.getClass().getDeclaredField(ANNOTATIONS);
	 * //field.setAccessible(true); //Map<Class<? extends Annotation>, Annotation>
	 * map = field.get(annotationData); //map.put(targetAnnotation, targetValue);
	 * 
	 * final JsonFormat ann = fieldDOB.getAnnotation(JsonFormat.class);
	 * System.out.println("before = " + ann.timezone());
	 * 
	 * Annotation newAnnotation = new JsonFormat() {
	 * 
	 * @Override public Class<? extends Annotation> annotationType() { return
	 * ann.annotationType(); }
	 * 
	 * @Override public String pattern() { return ann.pattern(); }
	 * 
	 * @Override public Shape shape() { return ann.shape(); }
	 * 
	 * @Override public String locale() { return ann.locale(); }
	 * 
	 * @Override public String timezone() { return "Asia/Kolkata"; }
	 * 
	 * @Override public OptBoolean lenient() { return ann.lenient(); }
	 * 
	 * @Override public Feature[] with() { return ann.with(); }
	 * 
	 * @Override public Feature[] without() { return ann.without(); } };
	 * 
	 * Field field = Class.class.getDeclaredField("annotations");
	 * field.setAccessible(true); Map<Class<? extends Annotation>, Annotation>
	 * annotations = (Map<Class<? extends Annotation>, Annotation>)
	 * field.get(Annotation.class); annotations.put(JsonFormat.class,
	 * newAnnotation);
	 * 
	 * JsonFormat modifiedAnnotation = fieldDOB.getAnnotation(JsonFormat.class);
	 * System.out.println("modifiedAnnotation = " + modifiedAnnotation.timezone());
	 * }
	 */

}
