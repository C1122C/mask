package util;

public enum RANK {
	ORDINARY("普通会员", 1), 
	SILVER("白银会员", 2), //>=500
	GOLD("黄金会员", 3), //>=1000
	DIAMOND("钻石会员", 4), //>=2000
	CROWN("皇冠会员", 5);//>=5000
    
    
    private String name ;
    private int index ;
     
    private RANK( String name , int index ){
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
