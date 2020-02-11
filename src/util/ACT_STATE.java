package util;

public enum ACT_STATE {
	UNDERWAY("进行中", 1),
	OVER("已结束", 2), 
	CLOSED("已结算", 3);
    
    
    private String name ;
    private int index ;
     
    private ACT_STATE( String name , int index ){
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
