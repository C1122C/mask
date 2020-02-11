package util;

public enum INTEREST {
	
	CONCERT("演唱会", 1), 
	MUSICALE("音乐会", 2), 
	DRAMA("话剧舞台剧", 3), 
	BALLET("舞蹈芭蕾", 4), 
	PARENT_CHILD("儿童亲子", 5), 
	OPERA("戏曲综艺", 6), 
	ENTERTAINMENT("休闲娱乐", 7), 
	SPORTS("体育赛事", 8);
    
    
    private String name ;
    private int index ;
     
    private INTEREST( String name , int index ){
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
