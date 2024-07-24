package bean;

public class Teacher {
	private String id;
	private String password;
	private String name;
	private String school_cd;
	private School school;

	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getSchool_cd(){
		return school_cd;
	}
	public void setSchool_cd(String school_cd){
		this.school_cd = school_cd;
	}
	public School getSchool(){
		return school;
	}
	public void setSchool(School school){
		this.school=school;
	}
    public boolean isAuthenticated() {
        // シンプルに常に true を返す実装（後で必要に応じてロジックを追加）
        return true;
    }
}

