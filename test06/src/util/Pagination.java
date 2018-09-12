package util;
public class Pagination {
private int ye;
private int maxYe;
private int beginYe;
private int endYe;
private int begin;
//numInPage 一页显示多少条数据 numOfPage 一页显示多少个页码
public  Pagination(int ye,int count,int numInPage,int numOfPage) {
	this.ye=ye;
	if(this.ye<=1) {
		this.ye=1;
	}
//	int maxYe=0;
//	if(count % size==0) {
//		maxYe= count/size;
//	}else {
//		maxYe= count/size+1;
//	}
	//三目运算符 A?B:C 语法糖

	 maxYe=count%numInPage==0? count /numInPage :count/numInPage+1;
	if(this.ye>=maxYe) {
		this.ye=maxYe;
	}
	
	 beginYe=this.ye-numOfPage/2;
	if(beginYe<=1) {
		beginYe=1;
	}
	 endYe=beginYe+numOfPage-1;
	if(endYe>=maxYe) {
		endYe=maxYe;
		beginYe =endYe - numOfPage +1;
	}
	if(beginYe<=1) {
		beginYe=1;
	}
	 begin=(this.ye-1)*numInPage;
}

public int getYe() {
	return ye;
}
public int getMaxYe() {
	return maxYe;
}
public int getBeginYe() {
	return beginYe;
}
public int getEndYe() {
	return endYe;
}
public int getBegin() {
	return begin;
}

}
