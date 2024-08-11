package com.spring.aop.demo;

public class Account {

    public Account() {

    }

    public Account(String name, String level) {
        this.name = name;
        this.level = level;
    }

	 private String name;
	    private String level;
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		   @Override
		    public String toString() {
		        return "Account{" +
		                "name='" + name + '\'' +
		                ", level='" + level + '\'' +
		                '}';
		    }
}
