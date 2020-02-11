package util;

public enum APP_TYPE {
	REG_VENUE("场馆注册", 1),
	MOD_VENUE("场馆修改", 2), 
	ADD_HALL("展厅增加", 3),
	DELETE_HALL("展厅删除", 4);
    
    
    private String name ;
    private int index ;
     
    private APP_TYPE( String name , int index ){
        this.name = name ;
        this.index = index ;
    }
     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
