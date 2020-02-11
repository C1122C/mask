package util;

public enum APP_STATE {
	WITHOUT_APPROVAL("待审批", 1),
	PASS("已通过", 2), 
	DOWN("已驳回", 3);
    
    
    private String name ;
    private int index ;
     
    private APP_STATE( String name , int index ){
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
