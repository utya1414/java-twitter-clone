package model.entity;

public class UserBean {
    /**
     * フィールド
     */
    private String user_id;
    private String password;
    private String user_name;

    /**
     * コンストラクタ
     */
    public UserBean(){
    }

    /**
     * メソッド
     */
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

}
