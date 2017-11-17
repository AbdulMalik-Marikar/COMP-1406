/**
 * Created by abdul on 2017-02-13.
 * 101042166
 */
public class RefrigeratorItem extends PerishableItem {

    public RefrigeratorItem(){
        super();
    }
    public RefrigeratorItem(String a, float b,float c){
        super(a,b,c);
    }
    public String toString(){
        return super.toString()+" [Keep Refrigerated]";
    }
}
