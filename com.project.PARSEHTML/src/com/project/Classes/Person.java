package com.project.Classes;

public class Person {
	private String Name = null;
	private String Gender = null;
	private String Occupation = null;
	private String Nationality = null;
	private String NativePlace = null;
	private String DateOfBorn = null;
	private boolean Marriage=false;
	private String Works = null;
	private boolean DeathOrNot=true;

	public Person() {
	}

	public String getName() {
		return Name;
	}

	public String getNativePlace() {
		return NativePlace;
	}

	public void setNativePlace(String nativePlace) {
		NativePlace = nativePlace;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getOccupation() {
		return Occupation;
	}

	public void setOccupation(String occupation) {
		Occupation = occupation;
	}

	public String getNationality() {
		return Nationality;
	}

	public void setNationality(String nationality) {
		Nationality = nationality;
	}

	public String getDateOfBorn() {
		return DateOfBorn;
	}

	public void setDateOfBorn(String dateOfBorn) {
		DateOfBorn = dateOfBorn;
	}

	public boolean isMarriage() {
		return Marriage;
	}

	public void setMarriage(boolean marriage) {
		Marriage = marriage;
	}

	public String getWorks() {
		return Works;
	}

	public void setWorks(String works) {
		Works = works;
	}

	public boolean isDeathOrNot() {
		return DeathOrNot;
	}

	public void setDeathOrNot(boolean deathOrNot) {
		DeathOrNot = deathOrNot;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
