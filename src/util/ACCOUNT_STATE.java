package util;

public enum ACCOUNT_STATE {
	CAN_USE("可用", 1),
	WAIT_APPROVAL("待审核", 2), 
	WAIT_CHECK("待验证", 3),
	STOP("停用", 4),
	NOT_PASS("未通过", 5);
    
    private String name ;
    private int index ;
     
    private ACCOUNT_STATE( String name , int index ){
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
