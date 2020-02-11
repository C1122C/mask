package util;

public enum SEAT_STATE {
	NONE("无座位", 1), 
	SOLD("不可选", 2), 
	AVAILABLE("可选", 3);
    
    
    private String name ;
    private int index ;
     
    private SEAT_STATE( String name , int index ){
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
