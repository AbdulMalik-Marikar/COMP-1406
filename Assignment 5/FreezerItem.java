/**
 * Created by abdul on 2017-02-13.
 * 101042166
 */
public class FreezerItem extends PerishableItem {
    public FreezerItem(){
        super();
    }

    public FreezerItem(String a,float b,float c){
        super(a,b,c);
    }
    public String toString(){
        return super.toString()+" [keep frozen]";
    }
}
