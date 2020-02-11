package util;

public enum ORDER_STATE {
	UNPAY("待支付", 1),
	WAITING("配票中", 2), 
	UNUSE("待使用", 3), 
	USED("已使用", 4), 
	CANCLE("已取消", 5),
	FAIL("配票失败", 6),
	INVALID("已失效", 7);
    
    
    private String name ;
    private int index ;
     
    private ORDER_STATE( String name , int index ){
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
